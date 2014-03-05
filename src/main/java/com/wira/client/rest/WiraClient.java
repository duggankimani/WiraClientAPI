package com.wira.client.rest;

import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;

public interface WiraClient {

	/**
	 * Handle incoming request (Handle Request from Wira BPM)
	 * 
	 * @param request
	 * @return
	 */
	 public Response handleRequest(Request request);
	 
	 /**
	  * Send request to WiraBPM 
	  * 
	  * <p>
	  * Note, the request must have a property called ServiceUri 
	  * which is the URI of WiraBPM
	  * 
	  * @param request
	  * @return
	  */
	 public Response sendRequest(Request request); 
	 
	 /**
	  * Send request to WiraBPM 
	  * 
	  * @param request
	  * @return
	  */
	 public Response sendRequest(Request request, String wiraBPMServerUri);
}
