package com.customerstatement.rabobank.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.customerstatement.rabobank.domain.Records;
import com.customerstatement.rabobank.factory.FileReaderFactory;

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
