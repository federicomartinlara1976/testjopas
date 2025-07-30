package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.util.Locale;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@SessionScoped
public class SessionBean extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4764455202310413427L;

	/** The lang. */
	private Locale lang;
	
	private String currentPage;
	
	private String prevPage;
	
	/** The session bean. */
	@ManagedProperty(value="#{appBean}")
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
	 * Gets the lang.
	 *
	 * @return the lang
	 */
	public Locale getLang() {
		return lang;
	}

	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * @return the prevPage
	 */
	public String getPrevPage() {
		return prevPage;
	}

	/**
	 * @param prevPage the prevPage to set
	 */
	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}
	
	/**
	 * @return the appBean
	 */
	public AppBean getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean the appBean to set
	 */
	public void setAppBean(AppBean appBean) {
		this.appBean = appBean;
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
