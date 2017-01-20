package com.maiyajf.base.utils.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

import org.apache.poi.ss.formula.functions.T;

public class XmlListWrapper<T> {
	private List<T> items;

	public XmlListWrapper() {
		items = new ArrayList<T>();
	}

	public XmlListWrapper(List<T> items) {
		this.items = items;
	}

	@XmlAnyElement(lax = true)
	public List<T> getItems() {
		return items;
	}
}
