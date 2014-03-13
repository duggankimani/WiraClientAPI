package com.wira.client.rest.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * JAXB Map Adaptor
 * 
 * @author duggan
 *
 */
public class MapAdapter extends XmlAdapter<Data, Map<String,Object>>{

	@Override
	public Map<String, Object> unmarshal(Data data) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(data!=null){
			result = getMap(data.keyValues);
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getMap(List<KeyValuePair> keyValues) {
		Map<String, Object> result = new HashMap<String, Object>();
		

		for(KeyValuePair pair: keyValues){
			Object value = pair.getValue();
			if(value instanceof List){
				result.put(pair.name, getMap((List<KeyValuePair>) value) );
			}else{
				result.put(pair.name, pair.getValue());
			}
			
		}
		
		return result;
	}

	@Override
	public Data marshal(Map<String, Object> v) throws Exception {
		
		Data data = new Data();
		
		if(v!=null)
			for(Entry<String, Object> e: v.entrySet()){
				data.keyValues.add(new KeyValuePair(e.getKey(), (Serializable)e.getValue()));
			}
		
		return data;
	}
	
}
