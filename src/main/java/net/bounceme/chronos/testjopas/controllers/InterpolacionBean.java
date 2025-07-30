package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ViewScoped
@Slf4j
public class InterpolacionBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private Utilidades utilidades;

	@Getter
	@Setter
	private InterpolacionDTO interpolacionDTO;
	
	@Getter
	private BigDecimal sp;
	
	@Getter
	private BigDecimal[][] dd;
	
	@Getter
	private BigDecimal[] y;

	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			initializePaths(paths);
		} catch (ServiceException e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	private void initializePaths(TestJopasConstantes.Paths paths) throws ServiceException {
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.FUNCIONES.getPath()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.getPath()));

		reset();
	}

	public void calcular() {
		try {
			String cmd = StringUtils.EMPTY;
			appBean.getCalcService().passVariable("puntoInterpolar", interpolacionDTO.getPuntoInterpolar());
			
			BigDecimal[] puntos = toArrayPuntos(interpolacionDTO);
			appBean.getCalcService().passVariable("x", puntos);
			
			if ("funcion".equals(sessionBean.getOpcion())) {
				cmd = "[Y,DD,SP]=interpoladorFuncion(puntoInterpolar, x)";
			}
			
			if ("tabla".equals(sessionBean.getOpcion())) {
				BigDecimal[] valores = toArrayValores(interpolacionDTO);
				appBean.getCalcService().passVariable("y", valores);
				cmd = "[Y,DD,SP]=interpoladorTablaValores(puntoInterpolar, x, y)";
			}
			
			appBean.getCalcService().execute(cmd);
				
			sp = appBean.getCalcService().getScalar("SP");
			log.debug("SP: {}", sp);
				
			y = appBean.getCalcService().getArray("Y");
			
			dd = appBean.getCalcService().getMatrix("DD");
		
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	private BigDecimal[] toArrayPuntos(InterpolacionDTO interpolacionDTO) {
		BigDecimal[] puntos = new BigDecimal[interpolacionDTO.getNumeroPuntos()];
		for (int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			puntos[i] = interpolacionDTO.getPuntos()[i].getPunto();
		}
		return puntos;
	}
	
	private BigDecimal[] toArrayValores(InterpolacionDTO interpolacionDTO) {
		BigDecimal[] valores = new BigDecimal[interpolacionDTO.getNumeroPuntos()];
		for (int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			valores[i] = interpolacionDTO.getPuntos()[i].getValor();
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
			BigDecimal punto = interpolacionDTO.getPuntos()[index].getPunto();
			appBean.getCalcService().passVariable("x", punto);

			String cmd = "y=f(x)";
			appBean.getCalcService().execute(cmd);

			BigDecimal scalarY = appBean.getCalcService().getScalar("y");
			if (scalarY != null) {
				interpolacionDTO.getPuntos()[index].setValor(scalarY);
			}
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
}
