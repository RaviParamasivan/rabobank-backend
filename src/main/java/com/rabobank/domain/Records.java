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

	@XmlElement(name = "record")
	public List<Record> getRecord() {
		return record;
	}

	public void setRecord(List<Record> record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "Records [record=" + record + "]";
	}
}
