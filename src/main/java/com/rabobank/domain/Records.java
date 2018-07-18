package com.rabobank.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ravi
 *
 */
@XmlRootElement(name = "records")
public class Records {
	

	private List<Record> record;
	private Boolean isValidEndBalance;
	private Boolean isUniqueStatement;

	@XmlElement(name = "record")
	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}

	public Boolean isValidEndBalance() {
		return isValidEndBalance;
	}

	public void setIsValidEndBalance(Boolean isValidEndBalance) {
		this.isValidEndBalance = isValidEndBalance;
	}

	public Boolean isUniqueStatement() {
		return isUniqueStatement;
	}

	public void setIsUniqueStatement(Boolean isUniqueStatement) {
		this.isUniqueStatement = isUniqueStatement;
	}

	@Override
	public String toString() {
		return "Records [record=" + record + ", isValidEndBalance=" + isValidEndBalance + ", isUniqueStatement="
				+ isUniqueStatement + "]";
	}
}
