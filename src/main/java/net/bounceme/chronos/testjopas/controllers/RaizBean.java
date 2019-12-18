package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.RaizDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jopas.Vector;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = RaizBean.NAME)
@ViewScoped
public class RaizBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "raizBean";
	
	/** The logger. */
	private Log logger;

	/** The appBean bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The sessionBean bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	private RaizDTO raizDTO;
	
	private BigDecimal sol;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(RaizBean.class, "LOG4J");
			
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(Paths.funciones.value());
			appBean.getCalcService().addPath(paths.value());

			raizDTO = new RaizDTO();
			sol = null;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("puntoInicial", raizDTO.getPuntoInicial());
			appBean.getCalcService().passVariable("tolerancia", raizDTO.getTolerancia());
			appBean.getCalcService().passVariable("iteraciones", raizDTO.getIteraciones());
	
			if ("secante".equals(sessionBean.getOpcion())) {
				appBean.getCalcService().passVariable("primeraAproximacion", raizDTO.getPrimeraAproximacion());
			}
			
			// Ejecuta el comando
			String cmd;
			if ("newton".equals(sessionBean.getOpcion())) {
				cmd = "[x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)";
			}
			else {
				cmd = "[x,sol,ni,error]=secante(puntoInicial, primeraAproximacion, tolerancia, iteraciones)";
			}
			
			appBean.getCalcService().execute(cmd);
			
			//String error = sessionBean.getJopasInterpreter().getString("error");
			Integer ni;
			Vector iteraciones;
			
			sol = appBean.getCalcService().get("sol");
			
//			try {
//				ni = sessionBean.getJopasInterpreter().getInteger("ni");
//				logger.info("Iteraciones realizadas: %d", ni);
//			} catch (Exception e) {
//				logger.error("ERROR:", e);
//			}
//			
//			try {
//				iteraciones = sessionBean.getJopasInterpreter().getVector("x");
//				logger.info("iteraciones: %s", iteraciones.toString());
//			} catch (Exception e) {
//				logger.error("ERROR:", e);
//			}
//			
//			try {
//				sol = sessionBean.getJopasInterpreter().getReal("sol");
//				logger.info("Solución: %f", sol);
//			} catch (Exception e) {
//				logger.error("ERROR:", e);
//			}
		} catch (Exception e) {
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
	 * @return the raizDTO
	 */
	public RaizDTO getRaizDTO() {
		return raizDTO;
	}

	/**
	 * @param raizDTO the raizDTO to set
	 */
	public void setRaizDTO(RaizDTO raizDTO) {
		this.raizDTO = raizDTO;
	}

	/**
	 * @return the sol
	 */
	public BigDecimal getSol() {
		return sol;
	}
	
	
}
