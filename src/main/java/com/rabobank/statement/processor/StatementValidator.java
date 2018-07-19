package com.rabobank.statement.processor;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementValidator {

	/**
	 * @param records
	 * @return
	 */
	public Boolean isValidRecordBalance(Records records);

	/**
	 * @param records
	 * @return
	 */
	public Boolean isValidUniqueRecord(Records records);

}
