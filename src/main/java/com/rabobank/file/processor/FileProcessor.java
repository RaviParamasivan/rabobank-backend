package com.rabobank.file.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabobank.App;
import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface FileProcessor {

	static final Logger LOGGER = LoggerFactory.getLogger(App.class);

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
	default String getFilePath(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getPath();
	}

	default Boolean generateReport(Records records) throws Exception {
		Boolean isReportGenerated = false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath("test.txt")))) {
			writeToFile(writer, "******************************************************************************");
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
			writeToFile(writer, "******************************************************************************");
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
		try {
			writer.write(statement);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
