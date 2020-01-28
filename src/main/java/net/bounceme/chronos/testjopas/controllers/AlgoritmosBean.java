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

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.controllers.converters.FileSelectItemConverter;
import net.bounceme.chronos.testjopas.dto.AlgoritmoDtwDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.AlgoritmoDtwService;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

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

	private AlgoritmoDtwDTO algoritmoDtwDTO;

	private List<SelectItem> archivos;

	private transient FileSelectItemConverter fileSelectItemConverter;

	private List<String[]> parametrosFirma1;

	private List<String[]> parametrosFirma2;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(AlgoritmosBean.class, "LOG4J");

			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(Paths.funciones.value());
			appBean.getCalcService().addPath(paths.value());

			reset();
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * @throws ServiceException
	 */
	public void reset() throws ServiceException {
		fileSelectItemConverter = new FileSelectItemConverter();
		archivos = (List<SelectItem>) fileSelectItemConverter.assemble(algoritmoDtwService.getArchivosParametros());

		algoritmoDtwDTO = new AlgoritmoDtwDTO();
		if (CollectionUtils.isNotEmpty(parametrosFirma1)) {
			parametrosFirma1.clear();
		}

		if (CollectionUtils.isNotEmpty(parametrosFirma2)) {
			parametrosFirma2.clear();
		}
	}

	/**
	 * 
	 */
	public void cambiarFichero1() {
		try {
			parametrosFirma1 = algoritmoDtwService.getFileParameters(algoritmoDtwDTO.getFichero1());
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
			parametrosFirma2 = algoritmoDtwService.getFileParameters(algoritmoDtwDTO.getFichero2());
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * 
	 */
	public void calcular() {
		try {
			BigDecimal[][] matrizFirma1 = algoritmoDtwService.getParametersMatrix(parametrosFirma1);
			BigDecimal[][] matrizFirma2 = algoritmoDtwService.getParametersMatrix(parametrosFirma2);

			appBean.getCalcService().passVariable("firma1", matrizFirma1);
			appBean.getCalcService().passVariable("firma2", matrizFirma2);
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
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
