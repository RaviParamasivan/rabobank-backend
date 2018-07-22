package com.rabobank.statement;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

public enum FileType {
	CSV("csv"), XML("xml"), FILE_TYPE_NOT_SUPPORTED("File type not supported");

	String fileExtension;

	private FileType(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	String getFileExtension() {
		return fileExtension;
	}

	public static FileType getFileType(final String inputFilePath) {
		if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), CSV.getFileExtension())) {
			return CSV;
		} else if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), XML.getFileExtension())) {
			return XML;
		}
		return FILE_TYPE_NOT_SUPPORTED;
	}

	public static boolean isValidFileType(final String inputFilePath, final String outputFilePath) {
		return !FILE_TYPE_NOT_SUPPORTED.equals(FileType.getFileType(inputFilePath))
				&& !FILE_TYPE_NOT_SUPPORTED.equals(FileType.getFileType(outputFilePath))
				&& Files.exists(Paths.get(inputFilePath));
	}

}
