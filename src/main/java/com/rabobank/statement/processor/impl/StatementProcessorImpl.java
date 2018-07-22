package com.rabobank.statement.processor.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;
import com.rabobank.statement.processor.StatementProcessor;
import com.rabobank.statement.processor.StatementValidator;
import com.rabobank.statement.reader.StatementReader;
import com.rabobank.statement.writer.StatementWriter;

/**
 * @author ravi
 *
 */
@Component
public class StatementProcessorImpl implements StatementProcessor {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private StatementWriter statementWriter;

	@Autowired
	private StatementValidator statementValidater;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.processor.statement.StatementProcessor#processStatement(java.
	 * lang.String, java.lang.String) Accept input and output files and read,
	 * validate and generate the report
	 */
	@Override
	public void processStatement(final String inputFilePath, final String outputFilePath) {
		Records records = null;
		try {
			records = StatementReader.getFileReader(inputFilePath.toLowerCase()).readStatement(inputFilePath);
			statementValidater.isValidRecordBalance(records);
			statementValidater.isValidUniqueRecord(records);
			statementWriter.writeStatement(records, outputFilePath);
		} catch (Exception e) {
			LOGGER.error("Exception while processing the document", e);
		}		
	}
}