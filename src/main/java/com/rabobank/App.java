package com.rabobank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabobank.statement.processor.StatementProcessor;

/**
 * @author ravi,  This is main program for reading the content from xml and csv
 *         file and validate the unique and end balance
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	public StatementProcessor statementProcessor;

	/*
	 * (non-Javadoc) This is init method for this application, Reads file from
	 * resource and validate the statement
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... args) throws Exception {
		LOGGER.info("Statement Processing strats...");
		statementProcessor.processStatement();
		LOGGER.info("Statement Processing End...");
	}
}
