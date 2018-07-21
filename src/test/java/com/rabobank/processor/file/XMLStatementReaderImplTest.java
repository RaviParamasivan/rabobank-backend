package com.rabobank.processor.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.rabobank.domain.Records;
import com.rabobank.factory.FileReaderFactory;
import com.rabobank.processor.file.CSVStatementReaderImpl;
import com.rabobank.processor.file.StatementReader;
import com.rabobank.processor.file.XMLStatementReaderImpl;

public class XMLStatementReaderImplTest {

	private StatementReader fileReader;

	@Test
	public void readStatementFromXML() throws Exception {
		fileReader = FileReaderFactory.getFileReader("records.xml");
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
