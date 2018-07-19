package com.rabobank.file.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;

public class CSVFileReaderTest {

	private FileReader fileReader;

	@Test
	public void readStatementFromCSV() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.csv");
		assertFalse(fileReader instanceof XMLFileReaderImpl);
		assertTrue(fileReader instanceof CSVFileReaderImpl);

		Records records = fileReader.readStatement("records.csv");
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);

	}

}
