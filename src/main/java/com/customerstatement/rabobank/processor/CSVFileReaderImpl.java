package com.customerstatement.rabobank.processor;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.customerstatement.rabobank.App;
import com.customerstatement.rabobank.domain.Record;
import com.customerstatement.rabobank.domain.Records;
import com.customerstatement.rabobank.utils.Constants;

/**
 * @author ravi
 *
 */
@Component
@Qualifier("csv")
public class CSVFileReaderImpl implements FileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private CSVParser csvParser;

	/**/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.FileReader#readStatement(java.lang.
	 * String)
	 */
	public Records readStatement(String fileName) throws Exception {

		Records records = new Records();
		List<Record> recordList = new ArrayList<>();
		Record record = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(getFilePath(fileName)))) {
			csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT
							.withHeader(Constants.CSV_HEADER_NAME_REFERENCE, Constants.CSV_HEADER_NAME_ACCOUNTNO,
									Constants.CSV_HEADER_NAME_DESCRIPTION, Constants.CSV_HEADER_NAME_STARTBALANCE,
									Constants.CSV_HEADER_NAME_MUTATION, Constants.CSV_HEADER_NAME_ENDBALANCE)
							.withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvParser) {
				// For ignoring the CSV header
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
			records.setRecord(recordList);

		} catch (Exception e) {
			LOGGER.error("Exception While reading the statement from CSV, Please check the CSF file/Data", e);
			throw new Exception("Exception While reading the statement from CSV, Please check the CSF file/Data");
		}
		return records;
	}

	/**
	 * @param fileName
	 * @return
	 */
	private String getFilePath(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getPath();
	}

	/**
	 * @param value
	 * @return
	 */
	private BigInteger getBigInteger(String value) {
		return new BigInteger(value);
	}

	/**
	 * @param value
	 * @return
	 */
	private BigDecimal getBigDecimal(String value) {
		return new BigDecimal(value);
	}
}
