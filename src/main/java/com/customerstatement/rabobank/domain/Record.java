package com.customerstatement.rabobank.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ravi
 *
 */
@XmlRootElement(name = "record")
public class Record {

	private BigInteger reference;
	private String accountNumber;
	private String description;
	private BigDecimal startBalance;
	private BigDecimal mutation;
	private BigDecimal endBalance;

	private Boolean isValidEndBalance;
	private Boolean isUnique;
	private String errorDescription;

	@XmlAttribute(name = "reference")
	public BigInteger getReference() {
		return reference;
	}

	public void setReference(BigInteger reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public Boolean getIsValidEndBalance() {
		return isValidEndBalance;
	}

	public void setIsValidEndBalance(Boolean isValidEndBalance) {
		this.isValidEndBalance = isValidEndBalance;
	}

	public Boolean getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Override
	public String toString() {
		return "Record [reference=" + reference + ", accountNumber=" + accountNumber + ", description=" + description
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance
				+ ", isValidEndBalance=" + isValidEndBalance + ", isUnique=" + isUnique + ", errorDescription="
				+ errorDescription + "]";
	}

}
