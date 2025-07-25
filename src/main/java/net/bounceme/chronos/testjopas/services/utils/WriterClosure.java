package net.bounceme.chronos.testjopas.services.utils;

import java.io.PrintWriter;
import java.util.function.Consumer;

public class WriterClosure implements Consumer<String[]> {
	private PrintWriter pw;
	private String separator;

	public WriterClosure(PrintWriter pw, String separator) {
		this.pw = pw;
		this.separator = separator;
	}

	@Override
	public void accept(String[] input) {
		if (input == null || input.length == 0) {
	        pw.println();
	        return;
	    }
	    
	    pw.println(String.join(separator, input));
	}
}
