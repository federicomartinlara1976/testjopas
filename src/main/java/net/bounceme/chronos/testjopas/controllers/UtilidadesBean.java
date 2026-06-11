package net.bounceme.chronos.testjopas.controllers;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
public class UtilidadesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	@Autowired
	private transient FilesService filesService;

	@Getter
	private List<File> availables;

	@Getter
	private List<File> droppedFiles;
	
	@Getter
	private List<String> templates;
	
	@Getter
	private List<String> archivos;

	@PostConstruct
	public void initialize() {
		try {
			availables = filesService.getAvailableFiles();
			templates = filesService.getTemplates();
			archivos = filesService.getArchivos();
			
			droppedFiles = new ArrayList<>();
		} catch (ServiceException e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}

	@SuppressWarnings("rawtypes")
	public void onFileDrop(DragDropEvent ddEvent) {
		File file = (File) ddEvent.getData();

		droppedFiles.add(file);
		availables.remove(file);
	}

	public void convertirFicheros() {
		try {
			filesService.convertirFicheros(droppedFiles);
			availables = filesService.getAvailableFiles();
			droppedFiles.clear();
		} catch (ServiceException e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}

	/**
	 * 
	 */
	public void reset() {
		droppedFiles.clear();
	}
}
