package com.rabobank.processor.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Record;
import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
public class CSVStatementReaderImpl implements StatementReader {

	private static final Logger LOGGER = LogManager.getLogger();
	private CSVParser csvParser;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.processor.file.StatementReader#readStatement(java.lang.String)
	 * This Method used to read the statement from the given csv files from command
	 * line and generate the Records object
	 */
	public Records readStatement(final String inputFilePath) throws Exception {
		Records records = null;

		Record record = null;
		LOGGER.debug("CSV File read starting..");
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT
							.withHeader(CSVColumn.REFERENCE.getName(), CSVColumn.ACCOUNT_NO.getName(),
									CSVColumn.DESCRIPTION.getName(), CSVColumn.STARTBALANCE.getName(),
									CSVColumn.MUTATION.getName(), CSVColumn.END_BALANCE.getName())
							.withIgnoreHeaderCase().withTrim());
			List<Record> recordList = new ArrayList<>();
			for (CSVRecord csvRecord : csvParser) {
				if (csvRecord.getRecordNumber() != 1) {
					record = new Record();
					record.setReference(getBigInteger(csvRecord.get(CSVColumn.REFERENCE.getName())));
					record.setAccountNumber(csvRecord.get(CSVColumn.ACCOUNT_NO.getName()));
					record.setDescription(csvRecord.get(CSVColumn.DESCRIPTION.getName()));
					record.setStartBalance(getBigDecimal(csvRecord.get(CSVColumn.STARTBALANCE.getName())));
					record.setMutation(getBigDecimal(csvRecord.get(CSVColumn.MUTATION.getName())));
					record.setEndBalance(getBigDecimal(csvRecord.get(CSVColumn.END_BALANCE.getName())));
					recordList.add(record);
				}
			}
			records = new Records();
			records.setRecord(recordList);
			LOGGER.debug("CSV File read Completed..");

		} catch (Exception e) {
			LOGGER.error("Exception While reading the statement from CSV, Please check the CSV file/Data", e);
			throw new Exception("Exception While reading the statement from CSV, Please check the CSV file/Data");
		}
		return records;
	}

	/**
	 * @param value
	 * @return
	 */
	private BigInteger getBigInteger(final String value) {
		return new BigInteger(value);
	}

	/**
	 * @param value
	 * @return
	 */
	private BigDecimal getBigDecimal(final String value) {
		return new BigDecimal(value);
	}
}
