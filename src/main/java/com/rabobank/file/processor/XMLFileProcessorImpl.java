package com.rabobank.file.processor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rabobank.App;
import com.rabobank.domain.Records;

/**
 * @author ravi
 *
 */
@Component
@Qualifier("xml")
public class XMLFileProcessorImpl implements FileProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

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
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
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
