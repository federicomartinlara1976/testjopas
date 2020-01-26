package net.bounceme.chronos.testjopas.services;

import java.io.File;
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
}
