package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = AlgebraBean.NAME)
@ViewScoped
public class AlgebraBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "algebraBean";

	/** The logger. */
	private Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	private AlgebraDTO algebraDTO;
	
	private BigDecimal[] c;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(AlgebraBean.class, "LOG4J");

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

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("A", algebraDTO.getMatrizCoeficientes());
			appBean.getCalcService().passVariable("b", algebraDTO.getTerminos());
			
			String cmd = "c=solve(A, b)";
			appBean.getCalcService().execute(cmd);
				
			c = appBean.getCalcService().getArray("c");
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	public void reset() {
		algebraDTO = new AlgebraDTO();
		c = null;
	}
	
	public void cambiarCoeficientes() {
		Integer n = algebraDTO.getNumeroCoeficientes();
		algebraDTO = new AlgebraDTO(n);
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
	 * @return the algebraDTO
	 */
	public AlgebraDTO getAlgebraDTO() {
		return algebraDTO;
	}

	/**
	 * @param algebraDTO the algebraDTO to set
	 */
	public void setAlgebraDTO(AlgebraDTO algebraDTO) {
		this.algebraDTO = algebraDTO;
	}

	/**
	 * @return the c
	 */
	public BigDecimal[] getC() {
		return c;
	}
}
