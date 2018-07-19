package com.rabobank.factory;

import org.springframework.util.StringUtils;

import com.rabobank.file.processor.CSVFileReaderImpl;
import com.rabobank.file.processor.FileReader;
import com.rabobank.file.processor.XMLFileReaderImpl;

public class FileReaderFactory {
	public static FileReader getFileReader(final String fileName) throws Exception {

		if (StringUtils.isEmpty(fileName)) {
			throw new Exception("File Name is empty");
		} else if (fileName.lastIndexOf(".xml") != -1) {
			return new XMLFileReaderImpl();
		} else if (fileName.lastIndexOf(".csv") != -1) {
			return new CSVFileReaderImpl();
		} else {
			throw new Exception("Invalid file type");
		}
	}
}
