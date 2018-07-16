package com.customerstatement.rabobank.factory;

import com.customerstatement.rabobank.processor.CSVFileReaderImpl;
import com.customerstatement.rabobank.processor.FileReader;
import com.customerstatement.rabobank.processor.XMLFileReaderImpl;

public class FileReaderFactory {

/*	@Autowired
	@Qualifier("xml")
	private static FileReader xmlFileReader;

	@Autowired
	@Qualifier("csv")
	private static FileReader csvFileeader;*/

	public static FileReader getFileReader(String fileNmae) throws Exception {
		if (fileNmae.lastIndexOf(".xml") != -1) {
			return new XMLFileReaderImpl();
		} else if (fileNmae.lastIndexOf(".csv") != -1) {
			return new CSVFileReaderImpl();
		} else {
			throw new Exception("File Name are empty/ Type not found");
		}
	}
}
