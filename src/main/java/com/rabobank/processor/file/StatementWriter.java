package com.rabobank.processor.file;

import com.rabobank.domain.Records;

public interface StatementWriter {

	public Boolean writeStatement(final Records records, final String outputFileName);

}
