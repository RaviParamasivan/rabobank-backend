package com.rabobank.processor.statement;

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
	public boolean isValidRecordBalance(Records records);

	/**
	 * @param records
	 * @return
	 */
	public boolean isValidUniqueRecord(Records records);

}
