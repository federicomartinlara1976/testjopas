package net.bounceme.chronos.testjopas.services;

import javax.annotation.PostConstruct;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;

@Service("droolsService")
public class DroolsService {

	/** The logger. */
	private Log logger;
	
	private StatefulKnowledgeSession kSession;
	
	private boolean hasError;
	
	/**
	 * Initialize.
	 */
	@PostConstruct
	private void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(DroolsService.class, "LOG4J");
		
			KnowledgeBase kbase = readKnowledgeBase();
			kSession = kbase.newStatefulKnowledgeSession();
			hasError = false;
		} catch (ServiceException e) {
			logger.error("ERROR: ", e);
			hasError = true;
		}
	}
	
	public void reset() {
		kSession.dispose();
	}
	
	public void insertOn(Object object) {
		kSession.insert(object);
	}
	
	/**
	 * @param object
	 */
	public void apply() throws ServiceException {
		if (!hasError) {
			kSession.fireAllRules();
		}
		else {
			throw new ServiceException("No cargado");
		}
	}
	
	private KnowledgeBase readKnowledgeBase() throws ServiceException {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("Order.drl"), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			for (KnowledgeBuilderError error : kbuilder.getErrors()) {
				logger.error("ERROR: ", error);
			}
			throw new ServiceException("Imposible crear knowledge con Order.drl");
		}
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
