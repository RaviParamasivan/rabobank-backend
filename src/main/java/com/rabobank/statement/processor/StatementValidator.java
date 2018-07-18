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
	public Boolean validateBalance(Records records);

	/**
	 * @param records
	 * @return
	 */
	public Boolean validateUnique(Records records);

}
