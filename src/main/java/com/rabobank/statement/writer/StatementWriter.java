package com.rabobank.statement.writer;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
public interface StatementWriter {

	boolean writeStatement(Records records, String outputFileName) throws Exception;

}
