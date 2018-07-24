package com.rabobank.statement.processor.impl;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rabobank.model.Record;
import com.rabobank.model.Records;
import com.rabobank.statement.processor.StatementEndBalanceValidator;
import com.rabobank.statement.processor.StatementValidator;

/**
 * @author ravi
 *
 */
@Component
public class StatementValidatorImpl implements StatementValidator {

	private StatementEndBalanceValidator statementEndBalanceValidator = ((startBalance, mutation,
			endBalance) -> !StringUtils.isEmpty(startBalance) && !StringUtils.isEmpty(mutation)
					&& !StringUtils.isEmpty(endBalance) && startBalance.add(mutation).compareTo(endBalance) == 0);

	/*
	 * (non-Javadoc) This method used to validate the given statements are unique or
	 * not
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementValidater#validateUnique(
	 * com.customerstatement.rabobank.domain.Records)
	 */

	public boolean isValidUniqueRecord(final Records records) {
		if (records != null) {
			// Grouping by reference no to get the duplicate statement
			Map<BigInteger, Long> groupByReference = records.getRecord().parallelStream()
					.collect(Collectors.groupingBy(Record::getReference, Collectors.counting()));

			// update the duplicate in Records Object
			groupByReference.entrySet().stream().filter(entrySet -> entrySet.getValue() > 1)
					.forEach(duplicate -> records.getRecord().stream()
							.filter(duplicateStatement -> duplicateStatement.getReference().equals(duplicate.getKey()))
							.forEach(deplicateRecord -> deplicateRecord.setIsUniqueStatement(false)));
			return !groupByReference.entrySet().stream().filter(entrySet -> entrySet.getValue() > 1).findAny()
					.isPresent();
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc) Method used to validate the end balance
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementValidater#validateBalance(
	 * com.customerstatement.rabobank.domain.Records) Method will return true if all
	 * the end balance are correct, else false
	 */
	public boolean isValidRecordBalance(final Records records) {
		if (records != null) {
			records.getRecord().stream().forEach(record -> {
				if (statementEndBalanceValidator.isValid(record.getStartBalance(), record.getMutation(),
						record.getEndBalance())) {
					record.setIsValidEndBalance(true);
				} else {
					record.setIsValidEndBalance(false);
				}
			});
			return !records.getRecord().stream().filter(record -> !record.getIsValidEndBalance()).findAny().isPresent();
		} else {
			return false;
		}
	}
}