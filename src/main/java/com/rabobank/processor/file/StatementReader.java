package com.rabobank.processor.file;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementReader {

	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Records readStatement(String fileName) throws Exception;

}
