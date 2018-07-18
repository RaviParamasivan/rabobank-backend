package com.rabobank.statement.processor;

import java.math.BigDecimal;

@FunctionalInterface
public interface Validator {
	public Boolean isValid(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance);
}
