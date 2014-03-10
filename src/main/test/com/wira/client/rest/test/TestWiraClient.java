package com.wira.client.rest.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.duggan.workflow.server.rest.model.BusinessKey;
import com.duggan.workflow.server.rest.model.Detail;
import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;
import com.wira.client.rest.WiraClientImpl;
import com.wira.client.rest.jaxb.JAXBProviderImpl;

public class TestWiraClient {

	public static void main(String[] args) {

		//String serviceUrl = "http://localhost:8080/ebusiness/rest/request/approval";
		String serviceUrl = "http://localhost:8888/rest/request/approval";
		String ownerId="Administrator";
		
		WiraClientImpl impl = new WiraClientImpl();
		Request request = new Request();
		
		//All data goes here
		Map<String,Object> context = new HashMap<>();
		context.put("ServiceUri", serviceUrl);
		context.put("docType", "INVOICE"); //mandatory
		context.put("ownerId", ownerId); //mandatory - username of logged in user/ or document creator
		context.put("docDate", new Date()); //mandatory - defaults to now() if not provided
		context.put("subject", null);
		context.put("supplier", "ABC Suppliers");
		context.put("description", "Example Invoice- A1 Integration");
		request.setContext(context);
		
		List<Detail> details = new ArrayList<>();
		double grandTotal=0.0;
		for(int i=0; i<4; i++){
			Map<String,Object> line = new HashMap<>();
			line.put("description", "Item#"+i);
			line.put("qty", new Random().nextInt(10));
			line.put("unitPrice", new Random().nextFloat()*999.50);
			Double total = ((Number)((Integer)line.get("qty")* (Double)line.get("unitPrice"))).doubleValue();
			line.put("total",total );
			grandTotal = grandTotal+total;
			
			Detail detail = new Detail();
			detail.setDetails(line);
			detail.setName("lines");
			details.add(detail);
		}
		context.put("value", grandTotal);
		
		request.setDetails(details);
		
		//referencing info
		request.setBusinessKey(new BusinessKey());
		request.setCommandName("NEWAPPROVALREQUESTCOMMAND");
		
		Response response = impl.sendRequest(request);
		assert response!=null;
		
//		outputJson(request);
		
	}

	private static void outputJson(Request request) {

		StringWriter writer = new StringWriter();
		
		try {
			JSONJAXBContext jaxbCtx = new JAXBProviderImpl().getContext(Request.class);
			
			JSONMarshaller marshaller = jaxbCtx.createJSONMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshallToJSON(request, writer);
			
			System.out.println(writer.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
