package com.rabobank.factory;

import com.rabobank.file.processor.CSVFileProcessorImpl;
import com.rabobank.file.processor.FileProcessor;
import com.rabobank.file.processor.XMLFileProcessorImpl;

public class FileReaderFactory {

/*	@Autowired
	@Qualifier("xml")
	private static FileReader xmlFileReader;

	@Autowired
	@Qualifier("csv")
	private static FileReader csvFileeader;*/

	public static FileProcessor getFileReader(String fileNmae) throws Exception {
		if (fileNmae.lastIndexOf(".xml") != -1) {
			return new XMLFileProcessorImpl();
		} else if (fileNmae.lastIndexOf(".csv") != -1) {
			return new CSVFileProcessorImpl();
		} else {
			throw new Exception("File Name are empty/ Type not found");
		}
	}
}
