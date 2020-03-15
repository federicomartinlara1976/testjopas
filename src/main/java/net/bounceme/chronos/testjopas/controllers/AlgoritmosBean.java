package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.controllers.converters.FileSelectItemConverter;
import net.bounceme.chronos.testjopas.dto.AlgoritmoDtwDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.AlgoritmoDtwService;
import net.bounceme.chronos.testjopas.services.FilesService;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.calc.dto.MatrixDTO;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;
import net.bounceme.chronos.velazquez.aspect.VelazquezTimeTrackAspect;
import net.bounceme.chronos.velazquez.services.ComparadorFirmas;

/**
 * @author Federico Martín Lara
 *
 */
@ManagedBean(name = AlgoritmosBean.NAME)
@ViewScoped
public class AlgoritmosBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "algoritmosBean";

	/** The logger. */
	private transient Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@Autowired
	private transient AlgoritmoDtwService algoritmoDtwService;
	
	@Autowired
	private transient FilesService filesService;

	@Autowired
	private transient ComparadorFirmas comparadorFirmas;
	
	@Autowired
	@Qualifier("timeTrack")
	private transient VelazquezTimeTrackAspect velazquezTimeTrackAspect;

	private AlgoritmoDtwDTO algoritmoDtwDTO;

	private List<SelectItem> archivos;

	private transient FileSelectItemConverter fileSelectItemConverter;

	private List<String[]> parametrosFirma1;

	private List<String[]> parametrosFirma2;

	private BigDecimal distancia;
	
	private Long lastExecution;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(AlgoritmosBean.class, "LOG4J");

			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");
			
			initializePaths(paths);
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	private void initializePaths(TestJopasConstantes.Paths paths) throws ServiceException {
		Utilidades utilidades = new Utilidades();
		
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.funciones.value()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.value()));

		reset();
	}

	/**
	 * @throws ServiceException
	 */
	public void reset() throws ServiceException {
		fileSelectItemConverter = new FileSelectItemConverter();
		archivos = (List<SelectItem>) fileSelectItemConverter.assemble(filesService.getArchivosParametros());

		algoritmoDtwDTO = new AlgoritmoDtwDTO();
		if (CollectionUtils.isNotEmpty(parametrosFirma1)) {
			parametrosFirma1.clear();
		}

		if (CollectionUtils.isNotEmpty(parametrosFirma2)) {
			parametrosFirma2.clear();
		}

		distancia = BigDecimal.ZERO;
	}

	/**
	 * 
	 */
	public void cambiarFichero1() {
		try {
			parametrosFirma1 = filesService.getFileParameters(algoritmoDtwDTO.getFichero1());
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * 
	 */
	public void cambiarFichero2() {
		try {
			parametrosFirma2 = filesService.getFileParameters(algoritmoDtwDTO.getFichero2());
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * 
	 */
	public void calcular() {
		BigDecimal[][] matrizFirma1 = algoritmoDtwService.getParametersMatrix(parametrosFirma1);
		BigDecimal[][] matrizFirma2 = algoritmoDtwService.getParametersMatrix(parametrosFirma2);

		MatrixDTO signatureData1 = algoritmoDtwService.getAsMatrix("signatureData1", matrizFirma1);
		MatrixDTO signatureData2 = algoritmoDtwService.getAsMatrix("signatureData2", matrizFirma2);

		Double dist = comparadorFirmas.verDistancia(signatureData1, signatureData2);
		distancia = BigDecimal.valueOf(dist);
		lastExecution = velazquezTimeTrackAspect.getLastExecutionTime();
	}

	/**
	 * @return the distancia
	 */
	public BigDecimal getDistancia() {
		return distancia;
	}

	/**
	 * @return the lastExecution
	 */
	public Long getLastExecution() {
		return lastExecution;
	}

	/**
	 * @return the appBean
	 */
	public AppBean getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean the appBean to set
	 */
	public void setAppBean(AppBean appBean) {
		this.appBean = appBean;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the algoritmoDtwDTO
	 */
	public AlgoritmoDtwDTO getAlgoritmoDtwDTO() {
		return algoritmoDtwDTO;
	}

	/**
	 * @param algoritmoDtwDTO the algoritmoDtwDTO to set
	 */
	public void setAlgoritmoDtwDTO(AlgoritmoDtwDTO algoritmoDtwDTO) {
		this.algoritmoDtwDTO = algoritmoDtwDTO;
	}

	/**
	 * @return the archivos
	 */
	public List<SelectItem> getArchivos() {
		return archivos;
	}
}
