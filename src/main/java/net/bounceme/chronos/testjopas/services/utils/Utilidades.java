package net.bounceme.chronos.testjopas.services.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {
	
	@Value("#{myProps['testjopas.carpetaScripts']}")
	private String carpetaScripts;
	
	public String getPathFromResource(String path) {
		
	    
	    return carpetaScripts + "/" + path;
	}
}
