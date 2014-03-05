package com.wira.client.rest.handlers;

import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;

public abstract class RequestHandlerDelegate {

	public abstract Response handleRequest(Request request);
}
