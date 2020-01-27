package net.bounceme.chronos.testjopas.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.exceptions.FileManagerException;
import net.bounceme.chronos.utils.filemanager.DirManager;
import net.bounceme.chronos.utils.filemanager.impl.system.SystemDirManager;

@Service("algoritmoDtwService")
public class AlgoritmoDtwService {

	private String SEPARATOR = "\\s+";
	
	/** The logger. */
	private Log logger;
	
	@Value("#{myProps['testjopas.carpetaFirmas']}")
	private String carpetaFirmas;
	
	private DirManager dirManager;
	
	public AlgoritmoDtwService() {
		super();
		dirManager = new SystemDirManager();
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(AlgoritmoDtwService.class, "LOG4J");
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<File> getArchivosParametros() throws ServiceException {
		try {
			return Arrays.asList(dirManager.listContents(carpetaFirmas));
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
	
	public List<String[]> getFileParameters(String sFile) throws ServiceException {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(carpetaFirmas + "/" + sFile))) {
			List<String[]> parameters = new ArrayList<>();
			
			String line = reader.readLine();
			String[] lines = line.trim().split(SEPARATOR);
			parameters.add(lines);
			
			while (line != null) {
				// read next line
				line = reader.readLine();
				lines = line.trim().split(SEPARATOR);
				parameters.add(lines);
			}
	
			return parameters;
		} catch (IOException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
}
