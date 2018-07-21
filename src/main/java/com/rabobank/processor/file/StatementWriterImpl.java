package com.rabobank.processor.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
public class StatementWriterImpl implements StatementWriter {

	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rabobank.processor.file.StatementWriter#writeStatement(com.rabobank.
	 * domain.Records, java.lang.String) This method write the statement validation
	 * details to the output file
	 */
	public boolean writeStatement(final Records records, final String outputFilePath) {
		Boolean isReportGenerated = false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFilePath)))) {
			writeUniqueStatementDetails(writer, records);
			writer.newLine();
			writeUniqueEndBalanceDetails(writer, records);
			writer.close();
			isReportGenerated = true;
		} catch (FileNotFoundException e) {
			LOGGER.error("File Not Found while generating the report", e);
		} catch (Exception e) {
			LOGGER.error("Exception occured while generating the report", e);
		}
		return isReportGenerated;

	}

	/**
	 * @param writer
	 * @param records
	 */
	private void writeUniqueEndBalanceDetails(BufferedWriter writer, Records records) {
		writeToFile(writer, "******************** UNIQUE STATEMENTS VALIDATION ****************************");
		if (!records.isUniqueStatement()) {
			records.getRecord().parallelStream()
					.filter(record -> record.getIsUniqueStatement() != null && !record.getIsUniqueStatement())
					.forEach(invalidRecord -> {
						writeToFile(writer, "Duplicate Reference :" + invalidRecord.getReference()
								+ " > AccountNumber :" + invalidRecord.getAccountNumber());
					});
		} else {
			writeToFile(writer, "No Duplicate Statement found");
		}

	}

	/**
	 * @param writer
	 * @param records
	 */
	private void writeUniqueStatementDetails(BufferedWriter writer, Records records) {
		writeToFile(writer, "********************* END BALANCE VALIDATION *********************************");
		if (!records.isValidEndBalance()) {
			records.getRecord().parallelStream()
					.filter(record -> record.getIsValidEndBalance() != null && !record.getIsValidEndBalance())
					.forEach(invalidRecord -> {
						writeToFile(writer, "Invalid End Balance Reference :" + invalidRecord.getReference()
								+ " > AccountNumber :" + invalidRecord.getAccountNumber());
					});
		} else {
			writeToFile(writer, "End Balance looks good for all the statements");
		}

	}

	private void writeToFile(BufferedWriter writer, String statement) {
		LOGGER.info(statement);
		try {
			writer.write(statement);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			LOGGER.error("Exception occured while writing the report", e);
		}
	}
}
