package com.rabobank.processor.file;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
@Qualifier("xml")
public class XMLStatementReaderImpl implements StatementReader {

	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * (non-Javadoc) This method read the xml file from resource directory and
	 * converts into Records pojo
	 * 
	 * @see
	 * com.customerstatement.rabobank.processor.FileReader#readStatement(java.lang.
	 * String)
	 */
	public Records readStatement(String fileName) throws Exception {
		Records records = null;
		try {
			LOGGER.debug("Start Reading the xml file from resource directiory");
			File file = getFile(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			records = (Records) unmarshaller.unmarshal(file);
			LOGGER.debug("Read xml sy=uccessfully and converted xml into pojo completed");
		} catch (Exception e) {
			LOGGER.error("Exception While reading the statement from XML", e);
			throw new Exception("Exception While reading the statement from XML");
		}
		return records;
	}
}
