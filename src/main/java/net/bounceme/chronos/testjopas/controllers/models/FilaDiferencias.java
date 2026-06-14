package net.bounceme.chronos.testjopas.controllers.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class FilaDiferencias implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
    private List<BigDecimal> valores;
    
    public FilaDiferencias() {
        this.valores = new ArrayList<>();
    }
    
    public FilaDiferencias(List<BigDecimal> valores) {
        this.valores = valores;
    }
}
