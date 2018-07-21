package com.rabobank.factory;

import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

/**
 * @author ravi
 *
 */
public class FileReaderFactory {

	private FileReaderFactory() {
		super();
	}
	
	/**
	 * @param inputFilePath
	 * @return
	 * @throws Exception
	 */
	public static StatementReader getFileReader(final String inputFilePath) throws Exception {

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
