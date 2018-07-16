package com.customerstatement.rabobank.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.customerstatement.rabobank.domain.Records;
import com.customerstatement.rabobank.factory.FileReaderFactory;
import com.customerstatement.rabobank.utils.Constants;

/**
 * @author ravi
 *
 */
@Component
public class StatementProcessorImpl implements StatementProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatementProcessorImpl.class);

	@Value("${statement.file.input.csv}")
	private String csvFileName;

	@Value("${statement.file.input.xml}")
	private String xmlFileName;

	@Value("${statement.file.input.type}")
	private String fileType;

	@Autowired
	public StatementValidater statementValidater;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementProcessor#processDocument()
	 */
	public Boolean processDocument() {
		Records records = null;
		try {
			// Reading the file from xml/csv
			String fileName = Constants.FILE_TYPE_CSV.equalsIgnoreCase(fileType)?csvFileName:xmlFileName;
			records = FileReaderFactory.getFileReader(fileName).readStatement(fileName);
			// End balance validation
			statementValidater.validateBalance(records);
			// Unique transaction validation
			statementValidater.validateUnique(records);
			// Print the report in the console
			this.generateReport(records);
		} catch (Exception e) {
			LOGGER.error("Exception while processing the data", e);
		}
		return records != null && records.isUniqueStatement() && records.isValidEndBalance();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public Records readStatementFromFile() throws Exception {

		return new Records();
	}

	/**
	 * accept records as input param and write the validation out put in console
	 * @param records 
	 */
	private void generateReport(Records records) {
		//Print Unique Validation report in console 
		LOGGER.info("*************************************************************************************");
		if (!records.isUniqueStatement()) {
			records.getRecord().parallelStream().filter(record -> record.getIsUnique() != null && !record.getIsUnique())
					.forEach(invalidRecord -> {
						LOGGER.info("Duplicate Refrence :" + invalidRecord.getReference() + " > AccountNumber :"
								+ invalidRecord.getAccountNumber());
					});
		} else {
			LOGGER.info("No Duplicate Statement Found");
		}
		
		//Print Invalid end statement report to console
		if (!records.isValidEndBalance()) {
			records.getRecord().parallelStream()
					.filter(record -> record.getIsValidEndBalance() != null && !record.getIsValidEndBalance())
					.forEach(invalidRecord -> {
						LOGGER.info("Invalid End Balance Refrence :" + invalidRecord.getReference()
								+ " > AccountNumber :" + invalidRecord.getAccountNumber());
					});
		} else {
			LOGGER.info("End Balance Looks Good");
		}
		LOGGER.info("*************************************************************************************");
	}

}
