package com.rabobank.factory;

import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

/**
 * @author ravi
 *
 */
public class FileReaderFactory {

	/**
	 * @param inputFilePath
	 * @return
	 * @throws Exception
	 */
	public static StatementReader getFileReader(final String inputFilePath) throws RuntimeException {

		FileType fileType = FileType.getFileType(inputFilePath);
		switch (fileType) {
		case CSV:
			return new CSVStatementReaderImpl();
		case XML:
			return new XMLStatementReaderImpl();
		default:
			throw new RuntimeException("Invalid file type, Please check your input arguments");
		}
	}
}
