package com.wira.client.rest;

import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;
import com.wira.client.rest.handlers.RequestHandlerRegistry;

public class WiraClientImpl implements WiraClient{

	@Override
	public Response handleRequest(Request request) {
		
		return RequestHandlerRegistry.getCommandHandler(request.getCommandName()).handleRequest(request);
	}

	@Override
	public Response sendRequest(Request request) {
		
		return new WiraHttpClientImpl().executeCall(request);
	}

	@Override
	public Response sendRequest(Request request, String wiraBPMServerUri) {
		
		return new WiraHttpClientImpl().executeCall(request, wiraBPMServerUri);
	}
	
}
