package com.customerstatement.rabobank.processor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.customerstatement.rabobank.App;
import com.customerstatement.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
@Qualifier("xml")
public class XMLFileReaderImpl implements FileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	/* (non-Javadoc)
	 * @see com.customerstatement.rabobank.processor.FileReader#readStatement(java.lang.String)
	 */
	public Records readStatement(String fileName) throws Exception {
		Records records = null;
		try {
			LOGGER.info("Start Reading the xml file from resource directiory");
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			records = (Records) unmarshaller.unmarshal(file);
			LOGGER.info("xml to pojo completed");
		} catch (Exception e) {
			LOGGER.error("Exception While reading the statement from XML", e);
			throw new Exception("Exception While reading the statement from XML");
		}
		return records;
	}
}
