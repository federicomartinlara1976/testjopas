package net.bounceme.chronos.testjopas.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jopas.Jopas;
import net.bounceme.chronos.utils.jopas.JopasException;

@Service
public class JoPasService {

	/** The logger. */
	private Log logger;

	private Jopas jopas;

	private boolean initialized;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(JoPasService.class, "LOG4J");
			jopas = new Jopas();
			initialized = true;
			logger.debug("Interprete de Octave iniciado");
		} catch (JopasException e) {
			logger.error("Error al inicializar interprete de Octave", e);
			initialized = false;
		}
	}

	public void addPath(String path) throws ServiceException {
		checkIsInitialized();

		StringBuilder sbComando = new StringBuilder();
		sbComando.append("addpath('").append(path).append("')");
		execute(sbComando);
	}

	public void resetPath() throws ServiceException {
		checkIsInitialized();
		
		StringBuilder sbComando = new StringBuilder();
		sbComando.append("restoredefaultpath()");
		execute(sbComando);
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
