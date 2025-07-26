package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.OctaveUtils;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.OctaveString;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToArray;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToBigDecimal;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToInteger;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToMatrix;
import net.bounceme.chronos.utils.calc.converters.OctaveStringToString;
import net.bounceme.chronos.utils.calc.dto.MatrixDTO;
import net.bounceme.chronos.utils.calc.dto.VectorDTO;

@Service
@Slf4j
public class JavaOctaveService implements CalcService {

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

	@SneakyThrows
	public void addPath(String path) {
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("addpath('").append(path).append("')");

		String cmd = sbComando.toString();
		log.debug(cmd);
		octave.eval(cmd);

	}

	@SneakyThrows
	public void resetPath() {
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("restoredefaultpath();");
		octave.eval(sbComando.toString());
	}

	@SneakyThrows
	public void clearEnvironment() {
		octave.eval("clear()");
	}

	@SneakyThrows
	public void execute(String cmd) {
		StringBuilder sbComando = new StringBuilder();
		sbComando.append(cmd);

		log.debug("Ejecutar comando: {}", sbComando.toString());
		octave.eval(sbComando.toString());
	}

	/**
	 * @param name
	 * @param value
	 */
	@SneakyThrows
	public void passVariable(String name, BigDecimal value) {
		log.debug("Pasando variable {} con valor {}", name, value.doubleValue());
		octave.eval(name + " = " + value.toString());
	}

	/**
	 * @param name
	 * @param value
	 */
	@SneakyThrows
	public void passVariable(String name, Integer value) {
		log.debug("Pasando variable {} con valor {}", name, value.doubleValue());
		octave.eval(name + " = " + value.toString());
	}

	@Override
	@SneakyThrows
	public void passVariable(String name, BigDecimal[] value) {
		VectorDTO vectorDTO = new VectorDTO(name, value);
		String cmdVar = vectorDTO.toString();
		log.debug("Pasando variable {}", cmdVar);
		octave.eval(cmdVar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.bounceme.chronos.testjopas.services.CalcService#passVariable(java.lang.
	 * String, java.math.BigDecimal[][])
	 */
	@Override
	@SneakyThrows
	public void passVariable(String name, BigDecimal[][] value) {
		MatrixDTO matrixDTO = new MatrixDTO(name, value);
		String cmdVar = matrixDTO.toString();
		log.debug("Pasando variable {}", cmdVar);
		octave.eval(cmdVar);
	}

	/**
	 * @param name
	 * @param value
	 */
	@SneakyThrows
	public void terminate() {
		octave.close();
	}

	@Override
	public List<String> getVars() {
		return new ArrayList<>(OctaveUtils.listVars(octave));
	}

	@Override
	public BigDecimal getScalar(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return doubleToBigDecimal.convert(value);
	}

	@Override
	public Integer getIntScalar(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveIntToInteger.convert(value);
	}

	@Override
	public BigDecimal[] getArray(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveDoubleToArray.convert(value);
	}

	@Override
	public BigDecimal[][] getMatrix(String name) {
		OctaveDouble value = octave.get(OctaveDouble.class, name);
		return octaveDoubleToMatrix.assemble(value);
	}

	@Override
	public String getString(String name) {
		OctaveString value = octave.get(OctaveString.class, name);
		return octaveStringToString.convert(value);
	}
}
