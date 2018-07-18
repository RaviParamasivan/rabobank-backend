package com.rabobank.statement.processor;

import java.math.BigDecimal;

@FunctionalInterface
public interface Validater {
	public Boolean isValid(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance);
}
