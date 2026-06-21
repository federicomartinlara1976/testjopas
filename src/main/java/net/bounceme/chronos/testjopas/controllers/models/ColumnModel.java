package net.bounceme.chronos.testjopas.controllers.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ColumnModel implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String header;
    
	@Getter
	@Setter
	private String property;
}
