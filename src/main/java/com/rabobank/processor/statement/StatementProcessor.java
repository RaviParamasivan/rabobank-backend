package com.rabobank.processor.statement;

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
	public boolean processStatement(String inputFilePath, String outputFilePath);

}
