package com.rabobank.statement.writer.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabobank.model.Records;
import com.rabobank.statement.writer.StatementWriter;

public class XMLStatementWriterImpl implements StatementWriter {
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public boolean writeStatement(final Records records, final String outputFileName) throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(records, new File(outputFileName));
			marshaller.marshal(records, System.out);
			return true;
		}catch (JAXBException e) {
			LOGGER.error("JAXBException While generating the statement output XML", e);
			throw new Exception("JAXBException While generating the statement output XML");
		} catch (Exception e) {
			LOGGER.error("Exception While generating the statement output XML", e);
			throw new Exception("Exception While generating the statement output XML");
		}
	}
}
