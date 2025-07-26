package net.bounceme.chronos.testjopas.services.utils;

import java.io.PrintWriter;

import org.apache.commons.collections4.Closure;

@SuppressWarnings("deprecation")
public class WriterClosure implements Closure<String[]> {
	
	private PrintWriter pw;
	private String separator;

	public WriterClosure(PrintWriter pw, String separator) {
		this.pw = pw;
		this.separator = separator;
	}

	@Override
	public void execute(String[] input) {
		if (input == null || input.length == 0) {
	        pw.println();
	        return;
	    }
	    
	    pw.println(String.join(separator, input));
	}
}
