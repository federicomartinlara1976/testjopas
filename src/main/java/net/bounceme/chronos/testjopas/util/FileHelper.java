package net.bounceme.chronos.testjopas.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileHelper {
	
	@SneakyThrows(IOException.class)
	public String leerFichero(String ruta) {
	    Path path = Paths.get(ruta);
	    return Files.readString(path);
	}
}
