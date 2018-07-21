package com.rabobank.processor.file;

import org.apache.commons.io.FilenameUtils;

/**
 * @author ravi
 *
 */
public enum FileType {

	CSV("csv"), XML("xml");

	String fileExtension;

	private FileType(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public static FileType getFileType(final String inputFilePath) {
		if (FilenameUtils.isExtension(inputFilePath, CSV.getFileExtension())) {
			return CSV;
		} else if (FilenameUtils.isExtension(inputFilePath, XML.getFileExtension())) {
			return XML;
		}
		return null;
	}

}
