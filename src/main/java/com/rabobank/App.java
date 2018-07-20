package com.rabobank;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabobank.processor.statement.StatementProcessor;

/**
 * @author ravi, This is main program, accept input and output file from commend
 *         line and read the statement from it and validate the end balance and
 *         unique reference and generate the report
 */

@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @param args
	 *            - will be passed from Command Line Ex -mvn spring-boot:run
	 *            -Dspring-boot.run.arguments=C:\\rabo\\records.csv,C:\\rabo\result.txt
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	public StatementProcessor statementProcessor;

	public void run(String... args) throws Exception {
		LOGGER.info("Statement Processing Starting");
		if (args.length == 2 && Files.exists(Paths.get(args[0]))) {
			statementProcessor.processStatement(args[0], args[1]);
		} else {
			throw new RuntimeException("Invalid arguments/files, Please check your input arguments");
		}
		LOGGER.info("Statement Processing Ending");
	}
}
