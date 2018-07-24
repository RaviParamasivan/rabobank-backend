package com.rabobank.statement.writer.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rabobank.model.Record;
import com.rabobank.model.Records;
import com.rabobank.statement.CSVColumn;
import com.rabobank.statement.writer.StatementWriter;

/**
 * @author ravi
 *
 */
@Component
public class CSVStatementWriterImpl implements StatementWriter {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final String NEW_LINE_SEPARATOR = "\n";

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rabobank.processor.file.StatementWriter#writeStatement(com.rabobank.
	 * domain.Records, java.lang.String) This method write the statement validation
	 * details to the output file
	 */
	public boolean writeStatement(final Records records, final String outputFilePath) throws Exception {

		boolean isWriteStatementSuccess = false;
		final Object[] FILE_HEADER = { CSVColumn.REFERENCE.getName(), CSVColumn.ACCOUNT_NO.getName(),
				CSVColumn.DESCRIPTION.getName(), CSVColumn.START_BALANCE.getName(), CSVColumn.MUTATION.getName(),
				CSVColumn.END_BALANCE.getName(), CSVColumn.IS_VALID_RECORD_BALANCE.getName(),
				CSVColumn.IS_VALID_UNIQUE_RECORD.getName() };

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		CSVPrinter csvFilePrinter;

		try (FileWriter fileWriter = new FileWriter(new File(outputFilePath))) {
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			records.getRecord().stream().forEach(record -> writeStatementToCSV(csvFilePrinter, record));
			fileWriter.flush();
			isWriteStatementSuccess = true;
		} catch (Exception e) {
			LOGGER.error("Exception occurred while generating CSV report", e);
			throw new Exception("Exception occurred while generating CSV report");
		}
		return isWriteStatementSuccess;
	}

	/**
	 * @param csvFilePrinter
	 * @param record
	 */
	private void writeStatementToCSV(final CSVPrinter csvFilePrinter, final Record record) {
		try {
			List<Object> statement = new ArrayList<>();
			statement.add(record.getReference());
			statement.add(record.getAccountNumber());
			statement.add(record.getDescription());
			statement.add(record.getStartBalance());
			statement.add(record.getMutation());
			statement.add(record.getEndBalance());
			statement.add(record.getIsValidEndBalance());
			statement.add(record.getIsUniqueStatement());
			csvFilePrinter.printRecord(statement);
		} catch (IOException e) {
			LOGGER.error("Exception occurred while writing the statement to CSV report", e);
		}
	}

}
