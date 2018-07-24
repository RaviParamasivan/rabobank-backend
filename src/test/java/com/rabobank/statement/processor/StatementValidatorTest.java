package com.rabobank.statement.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rabobank.model.Record;
import com.rabobank.model.Records;
import com.rabobank.statement.processor.StatementValidator;
import com.rabobank.statement.processor.impl.StatementValidatorImpl;

public class StatementValidatorTest {

	private StatementValidator statementValidater;

	@Test
	public void validateBalanceWithNull() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidRecordBalance(null);
		assertEquals(false, result);
	}

	@Test
	public void validateBalanceInvalidData() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidRecordBalance(getSampleStatements("invalid"));
		assertFalse(result);
	}

	@Test
	public void validateBalanceValidData() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidRecordBalance(getSampleStatements("valid"));
		assertTrue(result);
	}

	@Test
	public void validateUniqueDataNull() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidUniqueRecord(null);
		assertFalse(result);
	}

	@Test
	public void validateUniqueData() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidUniqueRecord(getDuplicateStatements("valid"));
		assertTrue(result);
	}

	@Test
	public void validateDupliacteData() {
		statementValidater = new StatementValidatorImpl();
		boolean result = statementValidater.isValidUniqueRecord(getDuplicateStatements("invalid"));
		assertFalse(result);
	}

	private Records getSampleStatements(String inputType) {

		Records records = new Records();
		List<Record> recordList = new ArrayList<>();
		Record record = null;
		if ("valid".equals(inputType)) {
			record = getSampleRecord(true);
		} else {
			record = getSampleRecord(false);
		}
		recordList.add(record);
		records.setRecord(recordList);
		return records;
	}

	private Records getDuplicateStatements(String inputType) {

		Records records = new Records();
		List<Record> recordList = new ArrayList<>();
		Record recordOne = null;
		Record recordTwo = null;
		recordOne = getSampleRecord(true);
		recordTwo = getSampleRecord(false);
		if ("valid".equals(inputType)) {
			recordTwo.setReference(new BigInteger("54321"));
		}
		recordList.add(recordOne);
		recordList.add(recordTwo);
		records.setRecord(recordList);
		return records;
	}

	private Record getSampleRecord(Boolean isValidEndBalance) {
		Record record = new Record();

		record.setStartBalance(new BigDecimal(25));
		record.setEndBalance(new BigDecimal(30));
		record.setReference(new BigInteger("12345"));

		if (isValidEndBalance) {
			record.setMutation(new BigDecimal(5));
		} else {
			record.setMutation(new BigDecimal(-1));
		}
		return record;
	}
}
