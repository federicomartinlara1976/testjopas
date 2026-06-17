package net.bounceme.chronos.testjopas.controllers.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Tabla implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private List<Fila> filas;
    
	@Getter
	@Setter
	private int maxColumnas;
}


