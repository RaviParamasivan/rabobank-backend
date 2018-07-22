package com.rabobank.statement.processor;

import java.math.BigDecimal;

@FunctionalInterface
public interface StatementEndBalanceValidator {
	boolean isValid(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance);
}
