package net.bounceme.chronos.testjopas.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.MimeConstants;
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
@ManagedBean(name = PruebaFopBean.NAME)
@ViewScoped
public class PruebaFopBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "pruebaFopBean";

	/** The logger. */
	private transient Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@Autowired
	private FilesService filesService;

	private String template;

	private String archivoXML;

	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(PruebaFopBean.class, "LOG4J");
	}

	/**
	 * @throws ServiceException
	 */
	public void reset() throws ServiceException {

	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the archivoXML
	 */
	public String getArchivoXML() {
		return archivoXML;
	}

	/**
	 * @param archivoXML the archivoXML to set
	 */
	public void setArchivoXML(String archivoXML) {
		this.archivoXML = archivoXML;
	}

	/**
	 * 
	 */
	public void obtenerPdf() {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = filesService.obtenerPdf(template, archivoXML);

			ByteArrayInputStream istream = (ByteArrayInputStream) input;
			Field field = ByteArrayInputStream.class.getDeclaredField("buf");
			field.setAccessible(true);
			byte[] buffer = (byte[]) field.get(istream);

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

			response.reset();
			response.setContentType(MimeConstants.MIME_PDF);
			response.setContentLength(buffer.length);
			response.setHeader("Content-Disposition", "attachment; filename=\"comunicado.pdf\"");
			output = response.getOutputStream();

			// This will copy the file from the two streams
			IOUtils.copy(input, output);

			// This will close two streams catching exception
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(input);

			fc.responseComplete();
		} catch (ServiceException | NoSuchFieldException | SecurityException | IllegalArgumentException
				| IllegalAccessException | IOException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		} finally {
			if (input != null)
				IOUtils.closeQuietly(input);
			if (output != null)
				IOUtils.closeQuietly(output);
		}
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

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
}
