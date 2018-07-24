package com.rabobank.statement.processor;

import java.math.BigDecimal;

/**
 * @author ravi this functional interface used to validate the end balance by
 *         adding start balance and mutation, then compare with end balance
 *
 */
@FunctionalInterface
public interface StatementEndBalanceValidator {
	boolean isValid(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance);
}
