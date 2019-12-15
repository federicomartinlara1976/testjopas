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
	public void addPath(JopasInterpreter jopas, String path) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("addpath('").append(path).append("')");
			jopas.execute(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @throws ServiceException
	 */
	public void resetPath(JopasInterpreter jopas) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("restoredefaultpath();");
			jopas.execute(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void clearEnvironment(JopasInterpreter jopas) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("clear()");
			jopas.execute(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void execute(JopasInterpreter jopas, String cmd) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			StringBuilder sbComando = new StringBuilder();
			sbComando.append(cmd);
	
			logger.debug("Ejecutar comando: %s", sbComando.toString());
			jopas.execute(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void passVariable(JopasInterpreter jopas, String name, BigDecimal value) throws ServiceException {
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
	public void passVariable(JopasInterpreter jopas, String name, Integer value) throws ServiceException {
		try {
			jopas.checkIsInitialized();
	
			logger.debug("Pasando variable %s con valor %d", name, value);
			jopas.load(value, name);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public void terminate(JopasInterpreter jopas) throws ServiceException {
		try {
			jopas.checkIsInitialized();
			jopas.terminate();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
