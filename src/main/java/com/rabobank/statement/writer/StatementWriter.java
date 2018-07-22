package com.rabobank.statement.writer;

import com.rabobank.domain.Records;
import com.rabobank.statement.FileType;
import com.rabobank.statement.writer.impl.CSVStatementWriterImpl;
import com.rabobank.statement.writer.impl.XMLStatementWriterImpl;

/**
 * @author ravi
 *
 */
public interface StatementWriter {

	boolean writeStatement(Records records, String outputFileName) throws Exception;
	
	static StatementWriter getFileWriter(final String outputFilePath) throws Exception {
		switch (FileType.getFileType(outputFilePath.toLowerCase())) {
		case CSV:
			return new CSVStatementWriterImpl();
		case XML:
			return new XMLStatementWriterImpl();
		default:
			throw new Exception("Invalid file type, Please check your input arguments");
		}
	}

}
