package com.wira.client.rest.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Data {

	@XmlElement(name="value")
	List<KeyValuePair> keyValues = new ArrayList<KeyValuePair>();
}
