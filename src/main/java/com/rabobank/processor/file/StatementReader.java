package com.rabobank.processor.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementReader {

	static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Records readStatement(String fileName) throws Exception;

}
