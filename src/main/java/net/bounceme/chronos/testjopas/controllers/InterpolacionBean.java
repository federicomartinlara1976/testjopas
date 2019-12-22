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
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = InterpolacionBean.NAME)
@ViewScoped
public class InterpolacionBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "interpolacionBean";

	/** The logger. */
	private Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	private InterpolacionDTO interpolacionDTO;
	
	private BigDecimal sp;
	
	private BigDecimal[][] dd;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(InterpolacionBean.class, "LOG4J");

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

	public void calcular() {
		try {
			if ("funcion".equals(sessionBean.getOpcion())) {
				appBean.getCalcService().passVariable("puntoInterpolar", interpolacionDTO.getPuntoInterpolar());
				
				BigDecimal[] valores = toArrayPuntos(interpolacionDTO);
				appBean.getCalcService().passVariable("x", valores);
				
				String cmd = "[Y,DD,SP]=interpoladorFuncion(puntoInterpolar, x)";
				appBean.getCalcService().execute(cmd);
				
				sp = appBean.getCalcService().getScalar("SP");
				logger.debug("SP: %.6f", sp);
				
				dd = appBean.getCalcService().getMatrix("DD");
				logger.debug("DD: %s", dd.toString());
			}
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	private BigDecimal[] toArrayPuntos(InterpolacionDTO interpolacionDTO) {
		BigDecimal[] valores = new BigDecimal[interpolacionDTO.getNumeroPuntos()];
		for (int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			valores[i] = interpolacionDTO.getPuntos()[i].getPunto();
		}
		return valores;
	}

	public void reset() {
		interpolacionDTO = new InterpolacionDTO();
		sp = null;
	}

	public void cambiarPuntos() {
		interpolacionDTO.setPuntos(new PuntoDTO[interpolacionDTO.getNumeroPuntos()]);

		// inicializa puntos
		for (int i = 0; i < interpolacionDTO.getNumeroPuntos(); i++) {
			interpolacionDTO.getPuntos()[i] = new PuntoDTO();
		}
	}

	public void calcularValor(Integer index) {
		try {
			if ("funcion".equals(sessionBean.getOpcion())) {
				BigDecimal punto = interpolacionDTO.getPuntos()[index].getPunto();
				appBean.getCalcService().passVariable("x", punto);

				String cmd = "y=f(x)";
				appBean.getCalcService().execute(cmd);

				BigDecimal y = appBean.getCalcService().getScalar("y");
				if (y != null) {
					interpolacionDTO.getPuntos()[index].setValor(y);
				}
			}
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
	 * @return the interpolacionDTO
	 */
	public InterpolacionDTO getInterpolacionDTO() {
		return interpolacionDTO;
	}

	/**
	 * @param interpolacionDTO the interpolacionDTO to set
	 */
	public void setInterpolacionDTO(InterpolacionDTO interpolacionDTO) {
		this.interpolacionDTO = interpolacionDTO;
	}
	
	/**
	 * @return the sp
	 */
	public BigDecimal getSp() {
		return sp;
	}

	/**
	 * @return the dd
	 */
	public BigDecimal[][] getDd() {
		return dd;
	}
}
