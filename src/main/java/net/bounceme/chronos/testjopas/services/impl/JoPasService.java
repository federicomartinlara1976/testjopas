package net.bounceme.chronos.testjopas.services.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.CalcService;
import net.bounceme.chronos.utils.jopas.JopasFactory;
import net.bounceme.chronos.utils.jopas.JopasInterpreter;

@Service
@Slf4j
public class JoPasService implements CalcService {
	
	private JopasInterpreter jopas;
	
	private Boolean initialized = false;
	
	private static final String ERROR_ILLEGAL_MSG = "El objeto no ha sido inicializado";

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		try {
			this.jopas = JopasFactory.getInstance().newInstance();
			initialized = true;
		} catch (Exception e) {
			log.error("No se ha podido iniciar el intérprete");
			initialized = false;
		}
	}

	/**
	 * @param path
	 * @throws ServiceException
	 */
	@SneakyThrows
	public void addPath(String path) {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("addpath('").append(path).append("')");
		jopas.execute(sbComando.toString());
	}

	@SneakyThrows
	public void resetPath() {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("restoredefaultpath();");
		jopas.execute(sbComando.toString());
	}
	
	/**
	 * @throws ServiceException
	 */
	@SneakyThrows
	public void clearEnvironment() {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("clear()");
		jopas.execute(sbComando.toString());
	}
	
	/**
	 * @throws ServiceException
	 */
	@SneakyThrows
	public void execute(String cmd) {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		StringBuilder sbComando = new StringBuilder();
		sbComando.append(cmd);
	
		log.debug("Ejecutar comando: {}", sbComando.toString());
		jopas.execute(sbComando.toString());
	}
	
	/**
	 * @param name
	 * @param value
	 */
	@SneakyThrows
	public void passVariable(String name, BigDecimal value) {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		log.debug("Pasando variable {} con valor {}", name, value.doubleValue());
		jopas.load(value, name);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	@SneakyThrows
	public void passVariable(String name, Integer value) {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
	
		log.debug("Pasando variable {} con valor {}", name, value.doubleValue());
		jopas.load(value, name);
	}
	
	@SneakyThrows
	public void terminate() {
		Validate.validState(initialized, ERROR_ILLEGAL_MSG);
		jopas.terminate();
	}

	@Override
	public List<String> getVars() {
		return Collections.emptyList();
	}

	@Override
	public BigDecimal getScalar(String name) {
		return BigDecimal.ZERO;
	}

	@Override
	public Integer getIntScalar(String name) {
		return 0;
	}

	@Override
	public BigDecimal[] getArray(String name) {
		return new BigDecimal[0];
	}
	
	@Override
	public String getString(String name) {
		return StringUtils.EMPTY;
	}

	@Override
	public void passVariable(String name, BigDecimal[] value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passVariable(String name, BigDecimal[][] value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal[][] getMatrix(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
