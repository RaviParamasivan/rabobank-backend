package com.rabobank.factory;

import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

public class FileReaderFactory {
	public static StatementReader getFileReader(final String fileName) throws Exception {

		FileType fileType = FileType.CSV;
		switch (fileType) {
		case CSV:
			return new CSVStatementReaderImpl();
		case XML:
			return new XMLStatementReaderImpl();
		default:
			throw new Exception("Invalid file type");
		}
	}
}
