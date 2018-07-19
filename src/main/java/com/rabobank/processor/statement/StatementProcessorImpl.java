package com.rabobank.processor.statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.processor.file.StatementWriter;
import com.rabobank.utils.Constants;

/**
 * @author ravi
 *
 */
@Component
public class StatementProcessorImpl implements StatementProcessor {

	private static final Logger LOGGER = LogManager.getLogger();

	@Value("${statement.file.input.csv}")
	private String csvFileName;

	@Value("${statement.file.input.xml}")
	private String xmlFileName;

	@Value("${statement.file.input.type}")
	private String fileType;

	@Value("${statement.file.output.txt}")
	private String outputFileName;

	@Autowired
	private StatementValidator statementValidater;

	@Autowired
	private StatementWriter statementWriter;

	/*
	 * (non-Javadoc) This method read input and output file details from
	 * application.property and read file, validate statement and generate the
	 * report
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementProcessor#processDocument()
	 */
	public Boolean processStatement() {
		Records records = null;
		try {
			String filePath = Constants.FILE_TYPE_CSV.equalsIgnoreCase(fileType) ? csvFileName : xmlFileName;
			records = FileReaderFactory.getFileReader(filePath).readStatement(filePath);
			statementValidater.isValidRecordBalance(records);
			statementValidater.isValidUniqueRecord(records);
			statementWriter.writeStatement(records, outputFileName);
		} catch (Exception e) {
			LOGGER.error("Exception while processing the data", e);
		}
		return records != null && records.isUniqueStatement() && records.isValidEndBalance();
	}
}