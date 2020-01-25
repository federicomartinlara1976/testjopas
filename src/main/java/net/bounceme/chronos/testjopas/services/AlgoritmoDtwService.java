package net.bounceme.chronos.testjopas.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;

@Service("algoritmoDtwService")
public class AlgoritmoDtwService {

	/** The logger. */
	private Log logger;
	
	@Value("#{myProps['testjopas.carpetaFirmas']}")
	private String carpetaFirmas;
	
	public AlgoritmoDtwService() {
		super();
		
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(AlgoritmoDtwService.class, "LOG4J");
	}

	
}
