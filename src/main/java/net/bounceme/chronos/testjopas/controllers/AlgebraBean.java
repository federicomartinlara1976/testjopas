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
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;
import net.bounceme.chronos.testjopas.services.AlgebraService;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class AlgebraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	@Autowired
	private AlgebraService algebraService;
	
	@Autowired
	private SessionBean sessionBean;

	@Getter
	@Setter
	private AlgebraDTO algebraDTO;
	
	private BigDecimal[] c;
	
	@Getter
	private Tabla tablaSoluciones;
	
	@Getter
	private List<ColumnModel> columns;
	
	@Getter
	private String codigo;

	@PostConstruct
	public void initialize() {
		reset();
	}

	public void calcular() {
		try {
			algebraService.calcular(algebraDTO, sessionBean.getOpcion());
			c = algebraService.getC();
			buildTabla();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
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
	
	private void buildTabla() {

		tablaSoluciones = new Tabla();
		tablaSoluciones.setFilas(new ArrayList<>());

		// Determinar el máximo número de columnas, con la primera fila
		tablaSoluciones.setMaxColumnas(1);
		
		// Usando Arrays.stream()
		for (BigDecimal solucion : c) {
		    Fila filaObj = new Fila();
		    Map<String, BigDecimal> valoresPorColumna = new LinkedHashMap<>();
		    
		    valoresPorColumna.put("col0", solucion);  // Mantener como BigDecimal
		    
		    filaObj.setValoresPorColumna(valoresPorColumna);
		    tablaSoluciones.getFilas().add(filaObj);
		}
		
		// Construir columnas
	    columns = new ArrayList<>();
	    columns.add(new ColumnModel("x", "index"));
	    columns.add(new ColumnModel("valor", "col0"));
	}
	
	public void obtenerCodigo() {
		codigo = algebraService.obtenerCodigo(sessionBean.getOpcion());
	}
}
