package com.rabobank.statement.reader;

import org.apache.commons.io.FilenameUtils;

import com.rabobank.domain.Records;
import com.rabobank.statement.reader.impl.CSVStatementReaderImpl;
import com.rabobank.statement.reader.impl.XMLStatementReaderImpl;

/**
 * @author ravi
 *
 */
public interface StatementReader {

	enum FileType {
		CSV("csv"), XML("xml");
		String fileExtension;

		private FileType(final String fileExtension) {
			this.fileExtension = fileExtension;
		}

		String getFileExtension() {
			return fileExtension;
		}

		static FileType getFileType(final String inputFilePath) {
			if (FilenameUtils.isExtension(inputFilePath, CSV.getFileExtension())) {
				return CSV;
			} else if (FilenameUtils.isExtension(inputFilePath, XML.getFileExtension())) {
				return XML;
			}
			return null;
		}
	}

	/**
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	Records readStatement(final String inputFilePath) throws Exception;

	/**
	 * @param inputFilePath
	 * @return
	 * @throws Exception
	 */
	static StatementReader getFileReader(final String inputFilePath) throws Exception {
		FileType fileType = FileType.getFileType(inputFilePath.toLowerCase());
		switch (fileType) {
		case CSV:
			return new CSVStatementReaderImpl();
		case XML:
			return new XMLStatementReaderImpl();
		default:
			throw new Exception("Invalid file type, Please check your input arguments");
		}
	}
}
