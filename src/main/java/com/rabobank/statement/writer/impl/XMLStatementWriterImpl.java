package com.rabobank.statement.writer.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.rabobank.domain.Records;
import com.rabobank.statement.writer.StatementWriter;

public class XMLStatementWriterImpl implements StatementWriter {

	@Override
	public boolean writeStatement(final Records records, final String outputFileName) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(records, new File(outputFileName));
		marshaller.marshal(records, System.out);
		return true;
	}

}
