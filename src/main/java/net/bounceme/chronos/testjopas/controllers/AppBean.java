package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import net.bounceme.chronos.testjopas.services.CalcService;

/**
 * The Class AppBean.
 */
@Component
@Named
@ApplicationScoped
public class AppBean implements Serializable {
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -195766865081737425L;
    
    @Getter
	@Value("${spring.application.name}")
	private String appName;

	@Getter
	@Value("${spring.application.version}")
	private String appVersion;
	
	@Autowired
	@Qualifier("javaOctaveService")
	@Getter
	private transient CalcService calcService;
}