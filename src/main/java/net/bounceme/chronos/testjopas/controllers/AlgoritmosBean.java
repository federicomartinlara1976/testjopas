package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
 * The Class SessionBean.
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
	private Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private AlgoritmoDtwService algoritmoDtwService;

	private AlgoritmoDtwDTO algoritmoDtwDTO; 
	
	private List<SelectItem> archivos;
	
	private FileSelectItemConverter fileSelectItemConverter;

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

	public void reset() throws ServiceException {
		fileSelectItemConverter = new FileSelectItemConverter();
		archivos = (List<SelectItem>) fileSelectItemConverter.assemble(algoritmoDtwService.getArchivosParametros());

		algoritmoDtwDTO = new AlgoritmoDtwDTO();
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
