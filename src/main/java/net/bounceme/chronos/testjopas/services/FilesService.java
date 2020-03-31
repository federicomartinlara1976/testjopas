package net.bounceme.chronos.testjopas.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.FileEquator;
import net.bounceme.chronos.testjopas.services.utils.IterableFiles;
import net.bounceme.chronos.testjopas.services.utils.WriterClosure;
import net.bounceme.chronos.utils.exceptions.FileManagerException;
import net.bounceme.chronos.utils.filemanager.DirManager;
import net.bounceme.chronos.utils.filemanager.impl.system.SystemDirManager;
import net.bounceme.chronos.utils.fop.CreatePdf;
import net.bounceme.chronos.utils.fop.exceptions.ParseException;

@Service("filesService")
public class FilesService {

	private String SEPARATOR_MUESTRAS = "\\s+";
	private String SEPARATOR_PROCESSED = ",";

	/** The logger. */
	private Log logger;

	@Value("#{myProps['testjopas.carpetaFirmas']}")
	private String carpetaFirmas;

	@Value("#{myProps['testjopas.carpetaMuestras']}")
	private String carpetaMuestras;
	
	@Value("#{myProps['testjopas.carpetaTemplates']}")
	private String carpetaTemplates;
	
	@Value("#{myProps['testjopas.carpetaArchivos']}")
	private String carpetaArchivos;

	private DirManager dirManager;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(FilesService.class, "LOG4J");

		dirManager = new SystemDirManager();
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<File> getArchivosParametros() throws ServiceException {
		return getProcessed();
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<File> getAvailableFiles() throws ServiceException {
		List<File> muestras = getMuestras();
		List<File> processed = getProcessed();

		List<File> availables = new ArrayList<>();
		Iterable<File> iterableProcessed = new IterableFiles(processed);

		for (File file : muestras) {
			if (!IterableUtils.contains(iterableProcessed, file, new FileEquator())) {
				availables.add(file);
			}
		}

		return availables;
	}

	/**
	 * @param ficheros
	 * 
	 * @throws ServiceException
	 */
	public void convertirFicheros(List<File> ficheros) throws ServiceException {
		try {
			for (File file : ficheros) {
				List<String[]> items = getFileItems(carpetaMuestras + "/" + file.getName(), SEPARATOR_MUESTRAS);
				writeFileItems(carpetaFirmas + "/" + file.getName(), items, SEPARATOR_PROCESSED);
			}
		} catch (IOException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @param sFile
	 * @return
	 * @throws ServiceException
	 */
	public List<String[]> getFileParameters(String sFile) throws ServiceException {
		try {
			return getFileItems(carpetaFirmas + "/" + sFile, SEPARATOR_PROCESSED);
		} catch (IOException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @param sFile
	 * @return
	 * @throws ServiceException
	 */
	private List<String[]> getFileItems(String sFile, String separator) throws IOException {

		try (BufferedReader reader = new BufferedReader(new FileReader(sFile))) {
			List<String[]> items = new ArrayList<>();

			String line = StringUtils.EMPTY;

			do {
				// read next line
				line = reader.readLine();
				if (StringUtils.isNotBlank(line)) {
					String[] lines = line.trim().split(separator);
					items.add(lines);
				}
			} while (line != null);

			return items;
		}
	}

	/**
	 * @param sFile
	 * @param items
	 * @param separator
	 * @return
	 * @throws ServiceException
	 */
	private void writeFileItems(String sFile, List<String[]> items, String separator) throws FileNotFoundException {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(sFile));

			CollectionUtils.forAllDo(items, new WriterClosure(pw, separator));

		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	private List<File> getMuestras() throws ServiceException {
		try {
			List<File> contents = Arrays.asList(dirManager.listContents(carpetaMuestras));

			return (List<File>) CollectionUtils.select(contents, new Predicate() {

				@Override
				public boolean evaluate(Object object) {
					File f = (File) object;
					return !f.isDirectory();
				}
			});
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	private List<File> getProcessed() throws ServiceException {
		try {
			return Arrays.asList(dirManager.listContents(carpetaFirmas));
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getTemplates() throws ServiceException {
		try {
			File[] files = dirManager.listContents(carpetaTemplates);
			List<String> names = new ArrayList<>();
			
			for(File file : files) {
				names.add(file.getName());
			}
			
			return names;
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getArchivos() throws ServiceException {
		try {
			File[] files = dirManager.listContents(carpetaArchivos);
			List<String> names = new ArrayList<>();
			
			for(File file : files) {
				names.add(file.getName());
			}
			
			return names;
		} catch (FileManagerException e) {
			logger.error("ERROR", e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * @param template
	 * @param archivoXML
	 */
	public InputStream obtenerPdf(String template, String archivoXML) throws ServiceException {
		try {
			StreamSource xslSource = new StreamSource(new File(carpetaTemplates + "/" + template));
			StreamSource xmlSource = new StreamSource(new File(carpetaArchivos + "/" + archivoXML));
		
			CreatePdf createPdf = new CreatePdf();
			OutputStream ostream = createPdf.parse(xslSource, xmlSource);
			
			ByteArrayInputStream istream = new ByteArrayInputStream(((ByteArrayOutputStream)ostream).toByteArray());
			return istream;
		} catch (ParseException e) {
			logger.error("ERROR: ", e);
			throw new ServiceException(e);
		}
	}
}
