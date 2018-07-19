package com.rabobank.file.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.file.processor.CSVFileReaderImpl;
import com.rabobank.file.processor.FileReader;
import com.rabobank.file.processor.XMLFileReaderImpl;

public class XMLFileReaderTest {

	private FileReader fileReader;

	@Test
	public void readStatementFromXML() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.xml");
		assertTrue(fileReader instanceof XMLFileReaderImpl);
		assertFalse(fileReader instanceof CSVFileReaderImpl);

		Records records = fileReader.readStatement("records.xml");
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);
	}

}
