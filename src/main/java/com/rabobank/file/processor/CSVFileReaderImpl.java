package com.rabobank.file.processor;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Record;
import com.rabobank.domain.Records;
import com.rabobank.utils.Constants;

/**
 * @author ravi
 *
 */
@Component
@Qualifier("csv")
public class CSVFileReaderImpl implements FileReader {

	private static final Logger LOGGER = LogManager.getLogger();
	private CSVParser csvParser;

	/**/

	/*
	 * (non-Javadoc) This method read the the cvs file from the resource and
	 * generate Records for validations
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.FileReader#readStatement(java.lang.
	 * String)
	 */
	public Records readStatement(final String fileName) throws Exception {
		Records records = null;
		List<Record> recordList = null;
		Record record = null;
		LOGGER.debug("CSV File read starting..");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(getFile(fileName).getPath()))) {
			recordList = new ArrayList<>();
			csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT
							.withHeader(Constants.CSV_HEADER_NAME_REFERENCE, Constants.CSV_HEADER_NAME_ACCOUNTNO,
									Constants.CSV_HEADER_NAME_DESCRIPTION, Constants.CSV_HEADER_NAME_STARTBALANCE,
									Constants.CSV_HEADER_NAME_MUTATION, Constants.CSV_HEADER_NAME_ENDBALANCE)
							.withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvParser) {
				if (csvRecord.getRecordNumber() != 1) {
					record = new Record();
					record.setReference(getBigInteger(csvRecord.get(Constants.CSV_HEADER_NAME_REFERENCE)));
					record.setAccountNumber(csvRecord.get(Constants.CSV_HEADER_NAME_ACCOUNTNO));
					record.setDescription(csvRecord.get(Constants.CSV_HEADER_NAME_DESCRIPTION));
					record.setStartBalance(getBigDecimal(csvRecord.get(Constants.CSV_HEADER_NAME_STARTBALANCE)));
					record.setMutation(getBigDecimal(csvRecord.get(Constants.CSV_HEADER_NAME_MUTATION)));
					record.setEndBalance(getBigDecimal(csvRecord.get(Constants.CSV_HEADER_NAME_ENDBALANCE)));
					recordList.add(record);
				}
			}
			records = new Records();
			records.setRecord(recordList);
			LOGGER.debug("CSV File read Completed");

		} catch (Exception e) {
			LOGGER.error("Exception While reading the statement from CSV, Please check the CSF file/Data", e);
			throw new Exception("Exception While reading the statement from CSV, Please check the CSF file/Data");
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