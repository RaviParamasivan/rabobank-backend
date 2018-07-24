package com.rabobank.statement;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

/**
 * @author ravi this file used to validate the input and output statement file
 *         type. the file will not be processed if file type not matching
 *
 */
public enum StatementFileType {
	CSV("csv"), XML("xml"), FILE_TYPE_NOT_SUPPORTED("File type not supported");

	String fileExtension;

	private StatementFileType(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	String getFileExtension() {
		return fileExtension;
	}

	public static StatementFileType getFileType(final String inputFilePath) {
		if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), CSV.getFileExtension())) {
			return CSV;
		} else if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), XML.getFileExtension())) {
			return XML;
		}
		return FILE_TYPE_NOT_SUPPORTED;
	}

	public static boolean isValidFileType(final String inputFilePath, final String outputFilePath) {
		return !FILE_TYPE_NOT_SUPPORTED.equals(StatementFileType.getFileType(inputFilePath))
				&& !FILE_TYPE_NOT_SUPPORTED.equals(StatementFileType.getFileType(outputFilePath))
				&& Files.exists(Paths.get(inputFilePath));
	}

}
