package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@Component
@Named
@SessionScoped
public class SessionBean extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4764455202310413427L;

	/** The lang. */
	@Getter
	private Locale lang;
	
	@Getter
	@Setter
	private String currentPage;
	
	@Getter
	@Setter
	private String prevPage;
	
	/** The session bean. */
	@Autowired
	@Getter
	@Setter
	private AppBean appBean;
	
	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		lang = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
		
		currentPage = "inicio";
	}

	/**
	 * @param page
	 * @return
	 */
	public String navegar(String page) {
		if (!"inicio".equals(page)) {
			TestJopasConstantes.Paths paths = TestJopasConstantes.Paths.valueOf(TestJopasConstantes.Paths.class, page);
			this.getJsfHelper().setSessionAttribute(paths, "path");
		}
		
		this.currentPage = page;
		return page;
	}
	
	/**
	 * @param nombre
	 * @param valor
	 */
	public void setParam(String nombre, String valor) {
		this.getJsfHelper().setSessionAttribute(valor, nombre);
	}
	
	/**
	 * @return
	 */
	public String getOpcion() {
		return (String) this.getJsfHelper().getSessionAttribute("opcion");
	}
}
