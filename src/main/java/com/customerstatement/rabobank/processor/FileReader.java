package com.customerstatement.rabobank.processor;

import com.customerstatement.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface FileReader {
	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Records readStatement(String fileName) throws Exception;
}
