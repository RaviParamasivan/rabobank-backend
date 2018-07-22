package com.rabobank.statement.processor;

/**
 * @author ravi
 *
 */
public interface StatementProcessor {

	/**
	 * @param outputFilePath
	 * @param inputFilePath
	 * @return
	 */
	void processStatement(String inputFilePath, String outputFilePath);

}
