package com.rabobank.processor.statement;

import java.math.BigDecimal;

@FunctionalInterface
public interface StatementEndBalanceValidator {
	public boolean isValid(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance);
}
