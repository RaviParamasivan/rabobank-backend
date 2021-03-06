package com.rabobank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabobank.statement.StatementFileType;
import com.rabobank.statement.processor.StatementProcessor;

/**
 * @author ravi, This is main program, accept input and output file from commend
 *         line and read the statement from it and validate the end balance and
 *         unique reference and generate the report
 */

@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	public StatementProcessor statementProcessor;

	/**
	 * @param args
	 *            - will be passed from Command Line Ex -mvn spring-boot:run
	 *            -Dspring-boot.run.arguments=C:\\rabo\\records.csv,C:\\rabo\\result.csv
	 *            input file type can be csv or xml. output file type can be csv or
	 *            xml
	 */
	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[]) This
	 * method validate the user input/output file type and initiate the statement
	 * processing
	 */
	public void run(final String... args) throws Exception {
		LOGGER.info("Statement Process Starting");
		if (args != null && args.length == 2 && StatementFileType.isValidFileType(args[0], args[1])) {
			statementProcessor.processStatement(args[0], args[1]);
		} else {
			throw new Exception("Invalid arguments/files, Please check your input arguments");
		}
		LOGGER.info("Statement Process Ending");
	}
}
