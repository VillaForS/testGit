package com.maiyajf.base.utils.xml;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class XmlWrapperUtil {
	private static <T> List<T> unmarshal(javax.xml.bind.Unmarshaller unmarshaller, Class<T> clazz, String xmlLocation)
			throws JAXBException {
		StreamSource xml = new StreamSource(xmlLocation);
		XmlListWrapper<T> wrapper = (XmlListWrapper<T>) unmarshaller.unmarshal(xml, XmlListWrapper.class).getValue();

		return wrapper.getItems();
	}

	public static <T> List parseXmlToList(Class<T> topLevelClass, String xmlLocation) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(XmlListWrapper.class, topLevelClass);
		// Unmarshal
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		List<T> list = unmarshal(unmarshaller, topLevelClass, xmlLocation);

		return list;
	}
}
