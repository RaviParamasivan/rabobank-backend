package com.rabobank.processor.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;

public class CSVStatementReaderImplTest {

	private StatementReader fileReader;

	@Test
	public void readStatementFromCSV() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.csv");
		assertFalse(fileReader instanceof XMLStatementReaderImpl);
		assertTrue(fileReader instanceof CSVStatementReaderImpl);
		Records records = fileReader.readStatement(getCSVFilePath());
		assertNotNull(records);
		assertNotEquals(records.getRecord().size(), 0);

	}

	private String getCSVFilePath() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("records.csv").getFile());
		return file.getAbsolutePath();
	}

}
