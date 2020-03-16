package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;
import net.bounceme.chronos.testjopas.dto.RaizDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = IntegracionBean.NAME)
@ViewScoped
public class IntegracionBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "integracionBean";
	
	/** The logger. */
	private Log logger;

	/** The appBean bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The sessionBean bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private Utilidades utilidades;

	private IntegracionDTO integracionDTO;
	
	private BigDecimal valor;
	
	private BigDecimal[] valores;
	
	private String error;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(IntegracionBean.class, "LOG4J");
			
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			initializePaths(paths);
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	private void initializePaths(TestJopasConstantes.Paths paths) throws ServiceException {
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.funciones.value()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.value()));

		reset();
	}

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("a", integracionDTO.getA());
			appBean.getCalcService().passVariable("b", integracionDTO.getB());
			appBean.getCalcService().passVariable("tolerancia", integracionDTO.getTolerancia());
			
			String cmd = "[valor,int,error]=integracion(a, b, tolerancia)";
			
			appBean.getCalcService().execute(cmd);
			
			// Las variables de salida son las que están definidas entre [] en el comando
			error = appBean.getCalcService().getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			else {
				valor = appBean.getCalcService().getScalar("valor");
				valores = appBean.getCalcService().getArray("int");
			}

		} catch (Exception e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	public void reset() {
		integracionDTO = new IntegracionDTO();
		
		valor = null;
		valores = new BigDecimal[0];
		error = StringUtils.EMPTY;
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

	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the integracionDTO
	 */
	public IntegracionDTO getIntegracionDTO() {
		return integracionDTO;
	}

	/**
	 * @param integracionDTO the integracionDTO to set
	 */
	public void setIntegracionDTO(IntegracionDTO integracionDTO) {
		this.integracionDTO = integracionDTO;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @return the valores
	 */
	public BigDecimal[] getValores() {
		return valores;
	}
}
