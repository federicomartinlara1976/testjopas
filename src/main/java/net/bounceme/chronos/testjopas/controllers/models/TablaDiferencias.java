package net.bounceme.chronos.testjopas.controllers.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TablaDiferencias implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private List<FilaDiferencias> filas;
    
	@Getter
	@Setter
	private int maxColumnas;
}


