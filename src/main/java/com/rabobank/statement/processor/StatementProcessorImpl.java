package com.rabobank.statement.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.utils.Constants;

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

	@Value("${statement.file.output.txt}")
	private String outputFileName;

	@Autowired
	private StatementValidater statementValidater;

	/*
	 * (non-Javadoc) This method read input and output file details from
	 * application.property and read file, validate statement and generate the
	 * report
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementProcessor#processDocument()
	 */
	public Boolean processDocument() {
		Records records = null;
		try {
			String fileName = Constants.FILE_TYPE_CSV.equalsIgnoreCase(fileType) ? csvFileName : xmlFileName;
			records = FileReaderFactory.getFileReader(fileName).readStatement(fileName);
			statementValidater.validateBalance(records);
			statementValidater.validateUnique(records);
			FileReaderFactory.getFileReader(fileName).generateReport(records, outputFileName);
		} catch (Exception e) {
			LOGGER.error("Exception while processing the data", e);
		}
		return records != null && records.isUniqueStatement() && records.isValidEndBalance();
	}
}