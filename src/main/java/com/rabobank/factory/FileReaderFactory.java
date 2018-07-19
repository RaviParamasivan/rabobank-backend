package com.rabobank.factory;

import org.springframework.util.StringUtils;

import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

public class FileReaderFactory {
	public static StatementReader getFileReader(final String fileName) throws Exception {

		if (StringUtils.isEmpty(fileName)) {
			throw new Exception("File Name is empty");
		} else if (fileName.lastIndexOf(".xml") != -1) {
			return new XMLStatementReaderImpl();
		} else if (fileName.lastIndexOf(".csv") != -1) {
			return new CSVStatementReaderImpl();
		} else {
			throw new Exception("Invalid file type");
		}
	}
}
