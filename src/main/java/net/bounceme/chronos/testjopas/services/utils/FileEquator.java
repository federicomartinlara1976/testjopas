package net.bounceme.chronos.testjopas.services.utils;

import java.io.File;

import org.apache.commons.collections4.Equator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileEquator implements Equator<File> {

	@Override
	public boolean equate(File o1, File o2) {
		log.debug("o1 = %s, o2 = %s", o1.getName(), o2.getName());
		return o1.getName().equals(o2.getName());
	}

	@Override
	public int hash(File o) {
		return o.hashCode();
	}

}
