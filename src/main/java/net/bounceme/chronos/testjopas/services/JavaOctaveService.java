package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.OctaveUtils;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.OctaveString;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.utils.calc.converters.Converter;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToArray;
import net.bounceme.chronos.utils.calc.converters.OctaveDoubleToMatrix;
import net.bounceme.chronos.utils.calc.dto.MatrixDTO;
import net.bounceme.chronos.utils.calc.dto.VectorDTO;

@Service
@Slf4j
public class JavaOctaveService implements CalcService {

	private OctaveEngine octave;

	private OctaveDoubleToArray octaveDoubleToArray;

	private OctaveDoubleToMatrix octaveDoubleToMatrix;

	public JavaOctaveService() {
		super();
		octave = new OctaveEngineFactory().getScriptEngine();
		octaveDoubleToArray = new OctaveDoubleToArray();
		octaveDoubleToMatrix = new OctaveDoubleToMatrix();
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
		return OctaveUtils.listVars(octave).stream().toList();
	}

	@Override
	public BigDecimal getScalar(String name) {
		Converter<OctaveDouble, BigDecimal> converter = s -> BigDecimal.valueOf(s.getData()[0])
                .setScale(0, RoundingMode.HALF_UP);
		return converter.apply(octave.get(OctaveDouble.class, name));
	}

	@Override
	public Integer getIntScalar(String name) {
		Converter<OctaveDouble, Integer> converter = s -> BigDecimal.valueOf(s.getData()[0])
                .setScale(0, RoundingMode.HALF_UP) // Redondeo estándar
                .intValueExact();
		return converter.apply(octave.get(OctaveDouble.class, name));
	}

	@Override
	public BigDecimal[] getArray(String name) {
		return octaveDoubleToArray.apply(octave.get(OctaveDouble.class, name));
	}

	@Override
	public BigDecimal[][] getMatrix(String name) {
		return octaveDoubleToMatrix.apply(octave.get(OctaveDouble.class, name));
	}

	@Override
	public String getString(String name) {
		Converter<OctaveString, String> converter = s -> s.getString();
		return converter.apply(octave.get(OctaveString.class, name));
	}
}
