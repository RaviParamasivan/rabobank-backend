package com.customerstatement.rabobank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.customerstatement.rabobank.processor.StatementProcessor;

/**
 * @author ravi
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	public StatementProcessor statementProcessor;

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... args) throws Exception {
		LOGGER.info("Statement Processing strats...");
		statementProcessor.processDocument();
		LOGGER.info("Statement Processing End...");
	}
}
