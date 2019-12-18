package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.Octave;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.OctaveInt;
import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;

@Service("javaOctaveService")
public class JavaOctaveService implements CalcService {

	/** The logger. */
	private Log logger;
	
	private OctaveEngine octave;
	
	public JavaOctaveService() {
		super();
		this.octave = new OctaveEngineFactory().getScriptEngine();
	}

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(JavaOctaveService.class, "LOG4J");
	}

	/**
	 * @param path
	 * @throws ServiceException
	 */
	public void addPath(String path) throws ServiceException {
		try {
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("addpath('").append(path).append("')");
			octave.eval(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @throws ServiceException
	 */
	public void resetPath() throws ServiceException {
		try {
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("restoredefaultpath();");
			octave.eval(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void clearEnvironment() throws ServiceException {
		try {
			StringBuilder sbComando = new StringBuilder();
			sbComando.append("clear()");
			octave.eval(sbComando.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public void execute(String cmd) throws ServiceException {
		try {
			StringBuilder sbComando = new StringBuilder();
			sbComando.append(cmd);
	
			logger.debug("Ejecutar comando: %s", sbComando.toString());
			octave.eval(sbComando.toString());
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
			logger.debug("Pasando variable %s con valor %.6f", name, value.doubleValue());
			OctaveDouble octaveDouble = Octave.scalar(value.doubleValue());
			octave.put(name, octaveDouble);
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
			logger.debug("Pasando variable %s con valor %.6f", name, value.doubleValue());
			OctaveInt octaveInt = intScalar(value);
			octave.put(name, octaveInt);
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
			octave.close();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private OctaveInt intScalar(final Integer i) {
        return new OctaveInt(new int[] { i }, 1, 1);
    }
}
