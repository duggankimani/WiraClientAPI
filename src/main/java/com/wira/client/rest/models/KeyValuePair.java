package com.wira.client.rest.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class KeyValuePair {

	/**
	 * Field Name
	 */
	public String name;

	public String type;

	private String stringValue;

	private Boolean booleanValue;

	private Long longValue;

	private Double doubleValue;

	private Date dateValue;

	private List<KeyValuePair> line;

	public KeyValuePair() {
	}

	public KeyValuePair(String key, Object value) {
		this.name = key;

		if (value == null)
			return;

		if (value instanceof String) {
			stringValue = value.toString();
			this.type = "STRING";

		} else if (value instanceof Boolean) {
			booleanValue = (Boolean) value;
			this.type = "BOOLEAN";

		} else if (value instanceof Long) {
			longValue = (Long) value;
			this.type = "LONG";

		} else if (value instanceof Integer) {
			longValue = new Long(value.toString());
			this.type = "INTEGER";

		} else if (value instanceof Double) {
			doubleValue = (Double) value;
			this.type = "DOUBLE";

		} else if (value instanceof Number) {
			doubleValue = ((Number) value).doubleValue();
			this.type = "DOUBLE";

		} else if (value instanceof Date) {
			dateValue = (Date) value;
			this.type = "DATE";

		}

	}

	public String getName() {
		return name;
	}

	public String getStringValue() {
		return stringValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public Object getValue() {
		if (type == null) {
			if (line != null && !line.isEmpty()) {
				return line;
			}
			return null;
		}

		if (type.equals("STRING")) {
			return stringValue;
			
		} else if (type.equals("BOOLEAN")) {
			return booleanValue;
			
		}
		else if (type.equals("LONG")) {
			return longValue;
		}
		else if (type.equals("INTEGER")) {
			return longValue;
			
		}
		else if (type.equals("DOUBLE")) {
			return doubleValue;
			
		} else if (type.equals("DATE")) {
			return dateValue;
			
		}

		return null;
	}

}
