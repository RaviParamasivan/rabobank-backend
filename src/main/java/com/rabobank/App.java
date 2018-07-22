package com.rabobank;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

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
	 */
	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void run(final String... args) throws Exception {
		LOGGER.info("Statement Process Starting");
		if (isValidArgs(args)) {
			statementProcessor.processStatement(args[0], args[1]);
		} else {
			throw new Exception("Invalid arguments/files, Please check your input arguments");
		}
		LOGGER.info("Statement Process Ending");
	}

	private boolean isValidArgs(final String... args) {
		return (args != null && args.length == 2 && Files.exists(Paths.get(args[0])) && !StringUtils.isEmpty(args[1]));
	}
}
