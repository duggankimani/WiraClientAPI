package com.wira.client.rest.handlers;

import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;

public class DefaultHandler extends RequestHandlerDelegate {

	
	@Override
	public Response handleRequest(Request request) {
		
		System.err.println(getClass().getCanonicalName()+ ": Handler  used");
		return null;
	}

}
