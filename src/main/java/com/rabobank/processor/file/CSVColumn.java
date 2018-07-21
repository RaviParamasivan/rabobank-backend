package com.rabobank.processor.file;

/**
 * @author ravi - This class provide the csv header for CSV Statement reader
 */
public enum CSVColumn {
	REFERENCE("Reference"), ACCOUNT_NO("AccountNumber"), DESCRIPTION("Description"), START_BALANCE(
			"Start Balance"), MUTATION("Mutation"), END_BALANCE("End Balance");

	private String name;

	private CSVColumn(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
