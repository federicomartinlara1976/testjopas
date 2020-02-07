package net.bounceme.chronos.testjopas.services.utils;

import java.io.File;

import org.apache.commons.collections4.Equator;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;

public class FileEquator implements Equator<File> {
	
	/** The logger. */
	private Log logger;

	public FileEquator() {
		logger = LogFactory.getInstance().getLogger(FileEquator.class, "LOG4J");
	}

	@Override
	public boolean equate(File o1, File o2) {
		logger.debug("o1 = %s, o2 = %s", o1.getName(), o2.getName());
		return o1.getName().equals(o2.getName());
	}

	@Override
	public int hash(File o) {
		return o.hashCode();
	}

}
