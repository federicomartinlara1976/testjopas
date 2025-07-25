package net.bounceme.chronos.testjopas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TestJopasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TestJopasApplication.class);
		builder.headless(false);
		builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String version = System.getProperty("java.version");
		log.debug("Java version: {}", version);
	}
}
