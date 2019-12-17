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
public class JoPasService implements Service {

	/** The logger. */
	private Log logger;
	
	private JopasInterpreter jopas;
	
	public JoPasService(JopasInterpreter jopas) {
		super();
		this.jopas = jopas;
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(JoPasService.class, "LOG4J");
	}

	/**
	 * @param path
	 * @throws ServiceException
	 */
	public void addPath(String path) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("addpath('").append(path).append("')");
			jopas.execute(sbComando);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @throws ServiceException
	 */
	public void resetPath() throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("restoredefaultpath();");
			jopas.execute(sbComando);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void clearEnvironment() throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("clear()");
			jopas.execute(sbComando);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void execute(String cmd) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append(cmd);
	
			logger.debug("Ejecutar comando: %s", sbComando.toString());
			jopas.execute(sbComando);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void passVariable(String name, BigDecimal value) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			logger.debug("Pasando variable %s con valor %.6f", name, value.doubleValue());
			jopas.load(value, name);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void passVariable(String name, Integer value) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			logger.debug("Pasando variable %s con valor %.6f", name, value.doubleValue());
			jopas.load(value, name);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void terminate() throws ServiceException {
		try {
			jopas.checkIsInitialized();
			jopas.terminate();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
