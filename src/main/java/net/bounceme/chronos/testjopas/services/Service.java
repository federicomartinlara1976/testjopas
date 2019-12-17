package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import net.bounceme.chronos.testjopas.exceptions.ServiceException;

public interface Service {
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
}
