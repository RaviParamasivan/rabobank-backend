package com.rabobank.processor.file;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component

public class XMLStatementReaderImpl implements StatementReader {

	private static final Logger LOGGER = LogManager.getLogger();

	public Records readStatement(final String inputFilePath) throws Exception {
		Records records = null;
		try {
			LOGGER.debug("Start Reading the xml file from resource directiory");
			File file = new File(inputFilePath);
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
