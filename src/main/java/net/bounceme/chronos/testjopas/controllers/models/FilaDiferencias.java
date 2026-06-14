package net.bounceme.chronos.testjopas.controllers.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class FilaDiferencias implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Map<String, BigDecimal> valoresPorColumna = new LinkedHashMap<>();
    
    public BigDecimal getValor(String columnaKey) {
        return valoresPorColumna.get(columnaKey);
    }
}
