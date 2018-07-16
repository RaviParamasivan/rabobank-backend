package com.customerstatement.rabobank.processor;

import com.customerstatement.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementValidater {

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
