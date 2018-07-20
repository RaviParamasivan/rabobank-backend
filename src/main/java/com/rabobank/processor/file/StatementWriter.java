package com.rabobank.processor.file;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementWriter {

	public boolean writeStatement(Records records, String outputFileName);

}
