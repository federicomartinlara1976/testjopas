package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = SessionBean.NAME)
@SessionScoped
public class SessionBean extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4764455202310413427L;

	/** The Constant NAME. */
	public static final String NAME = "sessionBean";
	
	/** The lang. */
	private Locale lang;
	
	private String currentPage;
	
	private String prevPage;
	
	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		lang = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
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
	 * @param page
	 * @return
	 */
	public String navegar(String page) {
		this.currentPage = page;
		return page;
	}
}
