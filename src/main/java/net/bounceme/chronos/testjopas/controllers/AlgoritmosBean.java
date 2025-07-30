package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.controllers.converters.FileSelectItemConverter;
import net.bounceme.chronos.testjopas.dto.AlgoritmoDtwDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.FilesService;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * @author Federico Martín Lara
 *
 */
@ViewScoped
@Slf4j
public class AlgoritmosBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private transient FilesService filesService;
	
	@Autowired
	private Utilidades utilidades;

	@Getter
	@Setter
	private AlgoritmoDtwDTO algoritmoDtwDTO;

	@Getter
	private List<SelectItem> archivos;

	private transient FileSelectItemConverter fileSelectItemConverter;

	private List<String[]> parametrosFirma1;

	private List<String[]> parametrosFirma2;

	@Getter
	private BigDecimal distancia;
	
	@Getter
	private Long lastExecution;

	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");
			
			initializePaths(paths);
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	@SneakyThrows
	private void initializePaths(TestJopasConstantes.Paths paths) {
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.FUNCIONES.getPath()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.getPath()));

		reset();
	}

	/**
	 * @throws ServiceException
	 */
	public void reset() throws ServiceException {
		fileSelectItemConverter = new FileSelectItemConverter();
		archivos = (List<SelectItem>) fileSelectItemConverter.assemble(filesService.getArchivosParametros());

		algoritmoDtwDTO = new AlgoritmoDtwDTO();
		if (CollectionUtils.isNotEmpty(parametrosFirma1)) {
			parametrosFirma1.clear();
		}

		if (CollectionUtils.isNotEmpty(parametrosFirma2)) {
			parametrosFirma2.clear();
		}

		distancia = BigDecimal.ZERO;
	}

	/**
	 * 
	 */
	public void cambiarFichero1() {
		try {
			parametrosFirma1 = filesService.getFileParameters(algoritmoDtwDTO.getFichero1());
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * 
	 */
	public void cambiarFichero2() {
		try {
			parametrosFirma2 = filesService.getFileParameters(algoritmoDtwDTO.getFichero2());
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
}
