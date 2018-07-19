package com.rabobank.processor.file;

import com.rabobank.domain.Records;

public interface StatementWriter {

	public Boolean writeStatement(Records records, final String outputFileName);

}
