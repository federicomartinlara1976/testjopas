package net.bounceme.chronos.testjopas.services.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class IterableFiles implements Iterable<File> {
	
	private List<File> list;

	public IterableFiles(List<File> list) {
		this.list = list;
	}

	@Override
	public Iterator<File> iterator() {
		return list.iterator();
	}

}
