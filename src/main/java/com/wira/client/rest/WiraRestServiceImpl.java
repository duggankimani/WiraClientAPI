package com.wira.client.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.wira.client.rest.exception.WiraExceptionModel;
import com.wira.client.rest.exception.WiraServiceException;
import com.wira.client.rest.models.Request;
import com.wira.client.rest.models.Response;

@Path("/wiraclient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WiraRestServiceImpl {
	Logger log = Logger.getLogger(WiraRestServiceImpl.class);

	@POST
	@Path("/submit")
	public Response handleRequest(Request request){
		WiraClient client = new WiraClientImpl();
		
		try{
			return client.handleRequest(request);
		}catch(Exception e){
			throw new WiraServiceException(WiraExceptionModel.getExceptionModel(e));
		}
	}
	
}
