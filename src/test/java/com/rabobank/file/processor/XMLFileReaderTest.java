package com.rabobank.file.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.file.processor.CSVFileProcessorImpl;
import com.rabobank.file.processor.FileProcessor;
import com.rabobank.file.processor.XMLFileProcessorImpl;

public class XMLFileReaderTest {

	private FileProcessor fileReader;

	@Test
	public void readStatementFromXML() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.xml");
		assertTrue(fileReader instanceof XMLFileProcessorImpl);
		assertFalse(fileReader instanceof CSVFileProcessorImpl);

		Records records = fileReader.readStatement("records.xml");
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);
	}

}
