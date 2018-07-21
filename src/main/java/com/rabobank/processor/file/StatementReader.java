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
	public Records readStatement(String inputFilePath) throws Exception;

	/**
	 * @param inputFilePath
	 * @return
	 * @throws Exception
	 */
	static StatementReader getFileReader(final String inputFilePath) throws Exception {
		FileType fileType = FileType.getFileType(inputFilePath);
		switch (fileType) {
		case CSV:
			return new CSVStatementReaderImpl();
		case XML:
			return new XMLStatementReaderImpl();
		default:
			throw new Exception("Invalid file type, Please check your input arguments");
		}
	}
}
