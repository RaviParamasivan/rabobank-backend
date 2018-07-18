package com.rabobank.statement.processor;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rabobank.domain.Record;
import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
public class StatementValidatorImpl implements StatementValidator {


	private Validator validator = ((startBalance, mutation, endBalance) -> !StringUtils.isEmpty(startBalance)
			&& !StringUtils.isEmpty(mutation) && !StringUtils.isEmpty(endBalance)
			&& startBalance.add(mutation).compareTo(endBalance) == 0);

	/*
	 * (non-Javadoc) This method used to validate the given statements are unique or
	 * not
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.StatementValidater#validateUnique(
	 * com.customerstatement.rabobank.domain.Records)
	 */

	public Boolean validateUnique(Records records) {

		if (records != null) {
			records.setIsUniqueStatement(true);
			// Grouping by refrences no to get the duplicate statement
			Map<BigInteger, Long> groupByRefrence = records.getRecord().parallelStream()
					.collect(Collectors.groupingBy(Record::getReference, Collectors.counting()));

			// update the duplicate in Records Object
			groupByRefrence.entrySet().stream().filter(entrySet -> entrySet.getValue() > 1).forEach(duplicate -> {
				records.setIsUniqueStatement(false);
				records.getRecord().stream()
						.filter(duplicateStatement -> duplicateStatement.getReference().equals(duplicate.getKey()))
						.forEach(deplicateRecord -> deplicateRecord.setIsUniqueStatement(false));
			});
			return records.isUniqueStatement();
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
	public Boolean validateBalance(Records records) {

		if (records != null) {
			records.setIsValidEndBalance(true);
			records.getRecord().stream().forEach(record -> {
				if (validator.isValid(record.getStartBalance(), record.getMutation(), record.getEndBalance())) {
					record.setIsValidEndBalance(true);
				} else {
					record.setIsValidEndBalance(false);
					records.setIsValidEndBalance(false);
				}
			});
			return records.isValidEndBalance();
		} else {
			return false;
		}

	}
}