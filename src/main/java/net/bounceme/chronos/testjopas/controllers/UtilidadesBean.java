package net.bounceme.chronos.testjopas.controllers;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.FilesService;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * @author Federico Martín Lara
 *
 */
@ManagedBean(name = UtilidadesBean.NAME)
@ViewScoped
public class UtilidadesBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "utilidadesBean";

	/** The logger. */
	private transient Log logger;

	@Autowired
	private transient FilesService filesService;

	private List<File> availables;

	private List<File> droppedFiles;
	
	private List<String> templates;
	
	private List<String> archivos;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(UtilidadesBean.class, "LOG4J");
			availables = filesService.getAvailableFiles();
			templates = filesService.getTemplates();
			archivos = filesService.getArchivos();
			
			droppedFiles = new ArrayList<>();
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

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
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * 
	 */
	public void reset() {
		droppedFiles.clear();
	}

	/**
	 * @return
	 */
	public List<File> getAvailables() {
		return availables;
	}

	/**
	 * @return
	 */
	public List<File> getDroppedFiles() {
		return droppedFiles;
	}

	/**
	 * @return the templates
	 */
	public List<String> getTemplates() {
		return templates;
	}

	/**
	 * @return the archivos
	 */
	public List<String> getArchivos() {
		return archivos;
	}
}
