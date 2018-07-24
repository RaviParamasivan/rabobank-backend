package com.rabobank.statement;

/**
 * @author ravi - This class provide the csv header information to read/write
 *         the statement from/to csv
 */
public enum CSVColumn {
	REFERENCE("Reference"), ACCOUNT_NO("AccountNumber"), DESCRIPTION("Description"), START_BALANCE(
			"Start Balance"), MUTATION("Mutation"), END_BALANCE("End Balance"), IS_VALID_RECORD_BALANCE(
					"Is Valid Record Balance"), IS_VALID_UNIQUE_RECORD("Is Valid Unique Record");

	private String name;

	private CSVColumn(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
