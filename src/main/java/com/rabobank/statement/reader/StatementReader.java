package com.rabobank.statement.reader;

import com.rabobank.model.Records;
import com.rabobank.statement.StatementFileType;
import com.rabobank.statement.reader.impl.CSVStatementReaderImpl;
import com.rabobank.statement.reader.impl.XMLStatementReaderImpl;

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
	Records readStatement(String inputFilePath) throws Exception;

	/**
	 * @param inputFilePath
	 * @return
	 * @throws Exception
	 */
	static StatementReader getFileReader(final String inputFilePath) throws Exception {
		switch (StatementFileType.getFileType(inputFilePath)) {
		case CSV:
			return new CSVStatementReaderImpl();
		case XML:
			return new XMLStatementReaderImpl();
		default:
			throw new Exception("Invalid file type, Please check your input arguments");
		}
	}
}
