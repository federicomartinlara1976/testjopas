package net.bounceme.chronos.testjopas.services.utils;

import java.io.File;
import java.net.URL;

public class Utilidades {
	
	public String getPathFromResource(String path) {
		URL url = this.getClass().getClassLoader().getResource(path);
	    File file = null;
	    
	    try {
	        file = new File(url.toURI());
	    } catch (Exception e) {
	        file = new File(url.getPath());
	    } 
	    
	    return file.getAbsolutePath();
	}
}
