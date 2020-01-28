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

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(AlgoritmoDtwService.class, "LOG4J");
		
		dirManager = new SystemDirManager();
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
	
	public BigDecimal[][] getParametersMatrix(List<String[]> parameters) {
		int f = parameters.size();
		int c = parameters.get(0).length;
		
		BigDecimal[][] matrix = new BigDecimal[f][c];
		
		int i=0;
		int j=0;
		for (String[] tokens : parameters) {
			for (String num : tokens) {
				matrix[i][j] = BigDecimal.ZERO;
				j++;
			}
			i++;
		}
		
		return matrix;
	}
}
