package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jopas.JopasException;
import net.bounceme.chronos.utils.jopas.JopasInterpreter;

@Service
public class JoPasService {

	/** The logger. */
	private Log logger;

	private JopasInterpreter jopas;

	private boolean initialized;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(JoPasService.class, "LOG4J");
			jopas = new JopasInterpreter();
			jopas.loadVersion();
			
			initialized = true;
			logger.debug("Interprete de Octave iniciado");
		} catch (JopasException e) {
			logger.error("Error al inicializar interprete de Octave", e);
			initialized = false;
		}
	}

	/**
	 * @param path
	 * @throws ServiceException
	 */
	public void addPath(String path) throws ServiceException {
		checkIsInitialized();

		StringBuilder sbComando = new StringBuilder();
		sbComando.append("addpath('").append(path).append("')");
		execute(sbComando);
	}

	/**
	 * @throws ServiceException
	 */
	public void resetPath() throws ServiceException {
		checkIsInitialized();
		
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("restoredefaultpath();");
		execute(sbComando);
	}
	
	/**
	 * @throws ServiceException
	 */
	public void clearEnvironment() throws ServiceException {
		checkIsInitialized();
		
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("clear()");
		execute(sbComando);
	}
	
	/**
	 * @throws ServiceException
	 */
	public void execute(String cmd) throws ServiceException {
		checkIsInitialized();
		
		StringBuilder sbComando = new StringBuilder();
		sbComando.append(cmd);
		execute(sbComando);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void passVariable(String name, BigDecimal value) throws ServiceException {
		checkIsInitialized();
		
		logger.debug("Pasando variable %s con valor %.6f", name, value.doubleValue());
		jopas.load(value, name);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void passVariable(String name, Integer value) throws ServiceException {
		checkIsInitialized();
		
		logger.debug("Pasando variable %s con valor %d", name, value);
		jopas.load(value, name);
	}

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * @throws ServiceException
	 */
	private void checkIsInitialized() throws ServiceException {
		if (!initialized) {
			logger.error("Servicio no inicializado");
			throw new ServiceException("Servicio no inicializado");
		}
	}
	
	/**
	 * @param sbComando
	 */
	private void execute(StringBuilder sbComando) {
		logger.debug("Ejecutar comando: %s", sbComando.toString());

		jopas.execute(sbComando.toString());
	}
}
