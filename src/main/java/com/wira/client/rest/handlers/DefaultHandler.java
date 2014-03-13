package com.wira.client.rest.handlers;

import com.wira.client.rest.models.Request;
import com.wira.client.rest.models.Response;

public class DefaultHandler extends RequestHandlerDelegate {

	
	@Override
	public Response handleRequest(Request request) {
		
		System.err.println(getClass().getCanonicalName()+ ": Handler  used");
		return null;
	}

}
