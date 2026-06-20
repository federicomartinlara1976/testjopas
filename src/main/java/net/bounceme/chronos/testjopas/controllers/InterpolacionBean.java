package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.controllers.models.ColumnModel;
import net.bounceme.chronos.testjopas.controllers.models.Fila;
import net.bounceme.chronos.testjopas.controllers.models.Tabla;
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
	private Tabla tablaDiferencias;
	
	@Getter
	private List<Double> encabezados;
	
	@Getter
	private List<ColumnModel> columns;
	
	@Getter
	private String funcion;

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

		tablaDiferencias = new Tabla();
		tablaDiferencias.setFilas(new ArrayList<>());

		// Determinar el máximo número de columnas, con la primera fila
		Integer maxCols = dd[0].length;
		tablaDiferencias.setMaxColumnas(maxCols);
		
		// Usando Arrays.stream()
		for (BigDecimal[] fila : dd) {
		    maxCols = Math.max(maxCols, fila.length);
		    Fila filaObj = new Fila();
		    Map<String, BigDecimal> valoresPorColumna = new LinkedHashMap<>();
		    
		    for (int i = 0; i < fila.length; i++) {
		        valoresPorColumna.put("col" + i, fila[i]);  // Mantener como BigDecimal
		    }
		    
		    filaObj.setValoresPorColumna(valoresPorColumna);
		    tablaDiferencias.getFilas().add(filaObj);
		}

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
		
		// Construir columnas
	    columns = new ArrayList<>();
	    columns.add(new ColumnModel("#", "index"));
	    
	    for (int i = 0; i < encabezados.size(); i++) {
	    	String key = "col" + i;
	    	columns.add(new ColumnModel(Integer.valueOf(i+1).toString(), key));
	    }
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
	
	public void obtenerFuncion() {
		// TODO - Obtenerla del fichero .m
		funcion = "function y = f(x) \r\n"
				+ "	y = x + log(x);\r\n"
				+ "endfunction";
	}
}
