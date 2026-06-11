package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.controllers.converters.FileSelectItemConverter;
import net.bounceme.chronos.testjopas.dto.AlgoritmoDtwDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.FilesService;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * @author Federico Martín Lara
 *
 */
@Component
@Named
@ViewScoped
@Slf4j
public class AlgoritmosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	@Value("${application.paths.funciones}")
	private String pathFunciones;

	/** The app bean. */
	@Autowired
	private AppBean appBean;
	
	@Autowired
	private transient FilesService filesService;
	
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
			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(pathFunciones);

			reset();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}

	/**
	 * @throws ServiceException
	 */
	@SneakyThrows(ServiceException.class)
	public void reset() {
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
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
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
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}
}
