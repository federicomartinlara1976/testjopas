package net.bounceme.chronos.testjopas.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.FileEquator;
import net.bounceme.chronos.testjopas.services.utils.IterableFiles;
import net.bounceme.chronos.testjopas.services.utils.WriterClosure;
import net.bounceme.chronos.utils.exceptions.FileManagerException;
import net.bounceme.chronos.utils.filemanager.DirManager;
import net.bounceme.chronos.utils.filemanager.impl.system.SystemDirManager;

@Service
@Slf4j
public class FilesService {

	private static final String ERROR = "ERROR";
	private static final String SEPARATOR_MUESTRAS = "\\s+";
	private static final String SEPARATOR_PROCESSED = ",";

	@Value("${application.carpetaFirmas}")
	private String carpetaFirmas;

	@Value("${application.carpetaMuestras}")
	private String carpetaMuestras;
	
	@Value("${application.carpetaTemplates}")
	private String carpetaTemplates;
	
	@Value("${application.carpetaArchivos}")
	private String carpetaArchivos;

	private DirManager dirManager;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		dirManager = new SystemDirManager();
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<File> getArchivosParametros() throws ServiceException {
		return getProcessed();
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<File> getAvailableFiles() throws ServiceException {
		List<File> muestras = getMuestras();
		List<File> processed = getProcessed();

		List<File> availables = new ArrayList<>();
		Iterable<File> iterableProcessed = new IterableFiles(processed);

		for (File file : muestras) {
			if (!IterableUtils.contains(iterableProcessed, file, new FileEquator())) {
				availables.add(file);
			}
		}

		return availables;
	}

	/**
	 * @param ficheros
	 * 
	 * @throws ServiceException
	 */
	public void convertirFicheros(List<File> ficheros) throws ServiceException {
		try {
			for (File file : ficheros) {
				List<String[]> items = getFileItems(carpetaMuestras + "/" + file.getName(), SEPARATOR_MUESTRAS);
				writeFileItems(carpetaFirmas + "/" + file.getName(), items, SEPARATOR_PROCESSED);
			}
		} catch (IOException e) {
			log.error(ERROR, e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @param sFile
	 * @return
	 * @throws ServiceException
	 */
	@SneakyThrows(IOException.class)
	public List<String[]> getFileParameters(String sFile) {
		return getFileItems(carpetaFirmas + "/" + sFile, SEPARATOR_PROCESSED);
	}

	/**
	 * @param sFile
	 * @return
	 * @throws ServiceException
	 */
	private List<String[]> getFileItems(String sFile, String separator) throws IOException {

		try (BufferedReader reader = new BufferedReader(new FileReader(sFile))) {
			List<String[]> items = new ArrayList<>();

			String line = StringUtils.EMPTY;

			do {
				// read next line
				line = reader.readLine();
				if (StringUtils.isNotBlank(line)) {
					String[] lines = line.trim().split(separator);
					items.add(lines);
				}
			} while (line != null);

			return items;
		}
	}

	/**
	 * @param sFile
	 * @param items
	 * @param separator
	 * @return
	 * @throws ServiceException
	 */
	@SneakyThrows(FileNotFoundException.class)
	private void writeFileItems(String sFile, List<String[]> items, String separator) {
		try (PrintWriter pw = new PrintWriter(new File(sFile))) {
			IteratorUtils.forEach(items.iterator(), new WriterClosure(pw, separator));
		} 
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	private List<File> getMuestras() throws ServiceException {
		try {
			List<File> contents = Arrays.asList(dirManager.listContents(carpetaMuestras));

			return (List<File>) CollectionUtils.select(contents, file -> !file.isDirectory());
		} catch (FileManagerException e) {
			log.error(ERROR, e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	private List<File> getProcessed() throws ServiceException {
		try {
			return Arrays.asList(dirManager.listContents(carpetaFirmas));
		} catch (FileManagerException e) {
			log.error(ERROR, e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getTemplates() throws ServiceException {
		try {
			File[] files = dirManager.listContents(carpetaTemplates);
			List<String> names = new ArrayList<>();
			
			for(File file : files) {
				names.add(file.getName());
			}
			
			return names;
		} catch (FileManagerException e) {
			log.error(ERROR, e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getArchivos() throws ServiceException {
		try {
			File[] files = dirManager.listContents(carpetaArchivos);
			List<String> names = new ArrayList<>();
			
			for(File file : files) {
				names.add(file.getName());
			}
			
			return names;
		} catch (FileManagerException e) {
			log.error(ERROR, e);
			throw new ServiceException(e);
		}
	}
}
