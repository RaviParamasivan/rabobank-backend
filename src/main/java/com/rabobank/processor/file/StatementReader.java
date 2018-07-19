package com.rabobank.processor.file;

import java.io.File;

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

	/**
	 * @param fileName
	 * @return
	 */
	default File getFile(final String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}

}
