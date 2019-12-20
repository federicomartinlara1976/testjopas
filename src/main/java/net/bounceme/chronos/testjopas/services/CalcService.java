package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.util.List;

import net.bounceme.chronos.testjopas.exceptions.ServiceException;

public interface CalcService {
	/**
	 * @param path
	 * @throws ServiceException
	 */
	void addPath(String path) throws ServiceException;

	/**
	 * @throws ServiceException
	 */
	void resetPath() throws ServiceException;
	
	/**
	 * @throws ServiceException
	 */
	void clearEnvironment() throws ServiceException;
	
	/**
	 * @throws ServiceException
	 */
	void execute(String cmd) throws ServiceException;
	
	/**
	 * @param name
	 * @param value
	 */
	void passVariable(String name, BigDecimal value) throws ServiceException;
	
	/**
	 * @param name
	 * @param value
	 */
	void passVariable(String name, Integer value) throws ServiceException;
	
	/**
	 * @param name
	 * @param value
	 */
	void terminate() throws ServiceException;
	
	/**
	 * @return
	 */
	List<String> getVars();
	
	/**
	 * @param name
	 * @return
	 */
	BigDecimal getScalar(String name);
	
	/**
	 * @param name
	 * @return
	 */
	Integer getIntScalar(String name);
	
	/**
	 * @param name
	 * @return
	 */
	BigDecimal[] getArray(String name);
	
	/**
	 * @param name
	 * @return
	 */
	String getString(String name);
}
