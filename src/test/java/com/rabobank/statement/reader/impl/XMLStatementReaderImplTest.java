package com.rabobank.statement.reader.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.rabobank.model.Records;
import com.rabobank.statement.reader.StatementReader;
import com.rabobank.statement.reader.impl.CSVStatementReaderImpl;
import com.rabobank.statement.reader.impl.XMLStatementReaderImpl;

public class XMLStatementReaderImplTest {

	private StatementReader fileReader;

	@Test
	public void readStatementFromXML() throws Exception {
		fileReader = StatementReader.getFileReader("records.xml");
		assertTrue(fileReader instanceof XMLStatementReaderImpl);
		assertFalse(fileReader instanceof CSVStatementReaderImpl);

		Records records = fileReader.readStatement(getCSVFilePath());
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);
	}

	private String getCSVFilePath() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("records.xml").getFile());
		return file.getAbsolutePath();
	}

}
