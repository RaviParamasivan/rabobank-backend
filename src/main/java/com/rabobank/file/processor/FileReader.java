package com.rabobank.file.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface FileReader {

	static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Records readStatement(String fileName) throws Exception;

	/**
	 * @param fileName
	 * @return
	 */
	default File getFile(final String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}

	default Boolean generateReport(Records records, final String outputFileName) throws Exception {
		Boolean isReportGenerated = false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName)))) {
			writeToFile(writer, "******************** UNIQUE STATEMENTS VALIDATION ****************************");
			if (!records.isUniqueStatement()) {
				records.getRecord().parallelStream()
						.filter(record -> record.getIsUniqueStatement() != null && !record.getIsUniqueStatement())
						.forEach(invalidRecord -> {
							writeToFile(writer, "Duplicate Refrence :" + invalidRecord.getReference()
									+ " > AccountNumber :" + invalidRecord.getAccountNumber());
						});
			} else {
				writeToFile(writer, "No Duplicate Statement Found");
			}
			writeToFile(writer, "********************* END BALANCE VALIDATION *********************************");
			if (!records.isValidEndBalance()) {
				records.getRecord().parallelStream()
						.filter(record -> record.getIsValidEndBalance() != null && !record.getIsValidEndBalance())
						.forEach(invalidRecord -> {
							writeToFile(writer, "Invalid End Balance Refrence :" + invalidRecord.getReference()
									+ " > AccountNumber :" + invalidRecord.getAccountNumber());
						});
			} else {
				writeToFile(writer, "End Balance Looks Good");
			}
			writeToFile(writer, "******************************************************************************");
			writer.close();
			isReportGenerated = true;
		} catch (FileNotFoundException e) {
			LOGGER.error("File Not Found while generating the report", e);
		} catch (Exception e) {
			LOGGER.error("Exception occured while generating the report", e);
		}
		return isReportGenerated;

	}

	default void writeToFile(BufferedWriter writer, String statement) {
		LOGGER.error(statement);
		try {
			writer.write(statement);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			LOGGER.error("Exception occured while writing the file", e);
		}

	}
}
