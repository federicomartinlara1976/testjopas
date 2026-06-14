package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.controllers.models.FilaDiferencias;
import net.bounceme.chronos.testjopas.controllers.models.TablaDiferencias;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.services.InterpolacionService;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class InterpolacionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	@Autowired
	private InterpolacionService interpolacionService;

	/** The app bean. */
	@Autowired
	private SessionBean sessionBean;

	@Getter
	@Setter
	private InterpolacionDTO interpolacionDTO;

	@Getter
	private BigDecimal sp;

	@Getter
	private BigDecimal[][] dd;

	@Getter
	private BigDecimal[] y;

	@Getter
	private TablaDiferencias tablaDiferencias;
	
	@Getter
	private List<Double> encabezados;

	@PostConstruct
	public void initialize() {
		reset();
	}

	public void calcular() {
		try {
			interpolacionService.calcular(interpolacionDTO, sessionBean.getOpcion());

			sp = interpolacionService.getSp();
			y = interpolacionService.getY();
			dd = interpolacionService.getDd();

			buildTablaDiferencias();
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}
	}

	private void buildTablaDiferencias() {

		tablaDiferencias = new TablaDiferencias();
		tablaDiferencias.setFilas(new ArrayList<>());

		// Determinar el máximo número de columnas
		int maxCols = 0;
		for (BigDecimal[] fila : dd) {
			maxCols = Math.max(maxCols, fila.length);
			FilaDiferencias filaObj = new FilaDiferencias();
			filaObj.setValores(Stream.of(fila)
                    .collect(Collectors.toList()));
			tablaDiferencias.getFilas().add(filaObj);
		}
		tablaDiferencias.setMaxColumnas(maxCols);

		// Crear encabezados (opcional)
		encabezados = new ArrayList<>();
		for (int i = 0; i < maxCols; i++) {
			if (i == 0) {
				encabezados.add(0.0); // f(x)
			} else if (i == 1) {
				encabezados.add(1.0); // Primera diferencia
			} else {
				encabezados.add((double) i); // O como quieras nombrarlos
			}
		}
	}
	
	public BigDecimal obtenerValorCelda(FilaDiferencias fila, int columnaIndex) {
        if (fila.getValores() != null && columnaIndex < fila.getValores().size()) {
            return fila.getValores().get(columnaIndex);
        }
        
        return null;
	}

	public void reset() {
		interpolacionDTO = new InterpolacionDTO();
		sp = null;
	}

	public void calcularValor(Integer index) {
		try {
			BigDecimal punto = interpolacionDTO.getPuntos()[index].getPunto();

			BigDecimal scalarY = interpolacionService.calcularValor(punto);
			if (scalarY != null) {
				interpolacionDTO.getPuntos()[index].setValor(scalarY);
			}
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}
	}

	public void cambiarPuntos() {
		interpolacionDTO.setPuntos(new PuntoDTO[interpolacionDTO.getNumeroPuntos()]);

		// inicializa puntos
		for (int i = 0; i < interpolacionDTO.getNumeroPuntos(); i++) {
			interpolacionDTO.getPuntos()[i] = new PuntoDTO();
		}
	}
}
