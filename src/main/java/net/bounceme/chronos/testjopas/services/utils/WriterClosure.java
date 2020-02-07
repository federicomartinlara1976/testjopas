package net.bounceme.chronos.testjopas.services.utils;

import java.io.PrintWriter;

import org.apache.commons.collections4.Closure;
import org.apache.commons.lang3.StringUtils;

public class WriterClosure implements Closure<String[]> {
	private PrintWriter pw;
	private String separator;

	public WriterClosure(PrintWriter pw, String separator) {
		this.pw = pw;
		this.separator = separator;
	}

	@Override
	public void execute(String[] input) {
		String line = StringUtils.EMPTY;

		for (String item : input) {
			line += item + separator;
		}

		line = line.substring(0, line.length() - 1);
		pw.println(line);
	}
}
