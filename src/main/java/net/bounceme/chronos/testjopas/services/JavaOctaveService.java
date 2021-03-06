package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.OctaveUtils;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.OctaveString;
import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToArray;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToBigDecimal;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToInteger;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToMatrix;
import net.bounceme.chronos.utils.calc.converters.OctaveStringToString;
import net.bounceme.chronos.utils.calc.dto.MatrixDTO;
import net.bounceme.chronos.utils.calc.dto.VectorDTO;

@Service("javaOctaveService")
public class JavaOctaveService implements CalcService {

	/** The logger. */
	private Log logger;
	
	private OctaveEngine octave;
	
	private OctaveDoubleToBigDecimal doubleToBigDecimal;
	
	private OctaveDoubleToInteger octaveIntToInteger;
	
	private OctaveDoubleToArray octaveDoubleToArray;
	
	private OctaveDoubleToMatrix octaveDoubleToMatrix;
	
	private OctaveStringToString octaveStringToString;
	
	public JavaOctaveService() {
		super();
		octave = new OctaveEngineFactory().getScriptEngine();
		doubleToBigDecimal = new OctaveDoubleToBigDecimal();
		octaveIntToInteger = new OctaveDoubleToInteger();
		octaveDoubleToArray = new OctaveDoubleToArray();
		octaveDoubleToMatrix = new OctaveDoubleToMatrix();
		octaveStringToString = new OctaveStringToString();
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
			
			String cmd = sbComando.toString();
			logger.info(cmd);
			octave.eval(cmd);
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
			octave.eval(name + " = " + value.toString());
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
			octave.eval(name + " = " + value.toString());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void passVariable(String name, BigDecimal[] value) throws ServiceException {
		try {
			VectorDTO vectorDTO = new VectorDTO(name, value);
			String cmdVar = vectorDTO.toString();
			logger.debug("Pasando variable %s", cmdVar);
			octave.eval(cmdVar);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.bounceme.chronos.testjopas.services.CalcService#passVariable(java.lang.String, java.math.BigDecimal[][])
	 */
	@Override
	public void passVariable(String name, BigDecimal[][] value) throws ServiceException {
		try {
			MatrixDTO matrixDTO = new MatrixDTO(name, value);
			String cmdVar = matrixDTO.toString();
			logger.debug("Pasando variable %s", cmdVar);
			octave.eval(cmdVar);
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

	@Override
	public List<String> getVars() {
		return new ArrayList<>(OctaveUtils.listVars(octave));
	}
	
	@Override
	public BigDecimal getScalar(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return doubleToBigDecimal.assemble(value);
	}

	@Override
	public Integer getIntScalar(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveIntToInteger.assemble(value);
	}

	@Override
	public BigDecimal[] getArray(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveDoubleToArray.assemble(value);
	}
	
	@Override
	public BigDecimal[][] getMatrix(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveDoubleToMatrix.assemble(value);
	}
	
	@Override
	public String getString(String name) {
		OctaveString value = octave.get(OctaveString.class, name);
		return octaveStringToString.assemble(value);
	}
}
