package com.rabobank.processor.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

public class CSVStatementReaderTest {

	private StatementReader fileReader;

	@Test
	public void readStatementFromCSV() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.csv");
		assertFalse(fileReader instanceof XMLStatementReaderImpl);
		assertTrue(fileReader instanceof CSVStatementReaderImpl);

		Records records = fileReader.readStatement("records.csv");
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);

	}

}
