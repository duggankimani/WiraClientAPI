package com.wira.client.rest.handlers;

import com.wira.client.rest.models.Request;
import com.wira.client.rest.models.Response;

public abstract class RequestHandlerDelegate {

	public abstract Response handleRequest(Request request);
}
