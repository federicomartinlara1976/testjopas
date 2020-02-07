package net.bounceme.chronos.testjopas.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.exceptions.FileManagerException;
import net.bounceme.chronos.utils.filemanager.DirManager;
import net.bounceme.chronos.utils.filemanager.impl.system.SystemDirManager;

@Service("filesService")
public class FilesService {

	//private String SEPARATOR = "\\s+";
	private String SEPARATOR = ",";

	/** The logger. */
	private Log logger;

	@Value("#{myProps['testjopas.carpetaFirmas']}")
	private String carpetaFirmas;
	
	@Value("#{myProps['testjopas.carpetaMuestras']}")
	private String carpetaMuestras;

	private DirManager dirManager;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(FilesService.class, "LOG4J");
		
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
		
		return (List<File>) CollectionUtils.subtract(muestras, processed);
	}

	/**
	 * @param sFile
	 * @return
	 * @throws ServiceException
	 */
	public List<String[]> getFileParameters(String sFile) throws ServiceException {

		try (BufferedReader reader = new BufferedReader(new FileReader(carpetaFirmas + "/" + sFile))) {
			List<String[]> parameters = new ArrayList<>();

			String line = StringUtils.EMPTY;

			do {
				// read next line
				line = reader.readLine();
				if (StringUtils.isNotBlank(line)) {
					String[] lines = line.trim().split(SEPARATOR);
					parameters.add(lines);
				}
			} while (line != null);

			return parameters;
		} catch (IOException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @return
	 * @throws ServiceException
	 */
	private List<File> getMuestras() throws ServiceException {
		try {
			List<File> contents = dirManager.listContents(carpetaMuestras);
			
			return (List<File>) CollectionUtils.select(contents, new Predicate() {
				
				@Override
				public boolean evaluate(Object object) {
					File f = (File) object;
					return !f.isDir();	
				}
			});
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
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
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
}
