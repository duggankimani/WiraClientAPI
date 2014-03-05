package com.wira.client.rest.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.duggan.workflow.server.rest.model.BusinessKey;
import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;
import com.wira.client.rest.WiraClientImpl;

public class TestWiraClient {

	String serviceUrl = "http://localhost:8080/ebusiness/rest/request/approval";
	String ownerId="Administrator";
	
	@Test
	public void submitRequest(){
		
		WiraClientImpl impl = new WiraClientImpl();
		Request request = new Request();
		
		//All data goes here
		Map<String,Object> context = new HashMap<>();
		context.put("ServiceUri", serviceUrl);
		context.put("ownerId", ownerId);
		context.put("docDate", new Date());
		
		request.setContext(context);
		//referencing info
		request.setBusinessKey(new BusinessKey());
		request.setCommandName("NEWAPPROVALREQUESTCOMMAND");
		Response response = impl.sendRequest(request);
		assert response!=null;
	}
	
}
