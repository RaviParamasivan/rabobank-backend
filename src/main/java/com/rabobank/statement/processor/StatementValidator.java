package com.rabobank.statement.processor;

import com.rabobank.model.Records;

/**
 * @author ravi
 *
 */
public interface StatementValidator {

	/**
	 * @param records
	 * @return
	 */
	boolean isValidRecordBalance(Records records);

	/**
	 * @param records
	 * @return
	 */
	boolean isValidUniqueRecord(Records records);

}
