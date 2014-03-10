package com.wira.client.rest;

import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.wira.client.rest.exception.WiraExceptionModel;
import com.wira.client.rest.jaxb.JAXBProviderImpl;

/**
 * Wira Rest Client Implementation
 * 
 * @author duggan
 *
 */
public class WiraHttpClientImpl {

	static Logger logger = Logger.getLogger(WiraHttpClientImpl.class);
	
	static Properties properties = new Properties();
	
	/**
	 * Jersey Client
	 */
	private Client jclient;
	
	public WiraHttpClientImpl(){
		DefaultClientConfig config = new DefaultClientConfig(JAXBProviderImpl.class);
		jclient = Client.create(config);
		jclient.setConnectTimeout(15000);

	}
	
	/**
	 * ServiceUri property of this request context must have a value
	 * pointing to the Wira BPM 
	 * 
	 * <p>
	 * @param request
	 * @return
	 */
	public Response executeCall(Request request){
		return executeCall(request, null);
	}
	
	/**
	 * ServiceUri - The URI of WiraBPM to which this request will be submitted
	 * <p>
	 * It can either be provided as property of the request context object (ServiceUri property)
	 * or directly through this method call 
	 * <p>
	 * 
	 * @param request
	 * @param serviceUri
	 * @return
	 */
	public Response executeCall(Request request, String serviceUri) {
		
		logger.info("Submitting Request : "+request);

		String uri = null;
		
		Object URI = request.getContext("ServiceUri");
		if(URI!=null && !URI.toString().isEmpty()){
			uri =URI.toString();
		}

		if(uri==null || uri.isEmpty()){
			throw new IllegalArgumentException("REST URI cannot be null for rest service");
		}

		WebResource resource = jclient.resource(uri);

		ClientResponse clientResponse = null;

		try {
			clientResponse = resource.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, request);
			
			// response.getHeaders()
		} catch (Exception e) {

			// server unavailable
			throw new RuntimeException(e);
		}

		if (clientResponse.getClientResponseStatus().equals(
				ClientResponse.Status.OK)) {
			
			Response response = clientResponse.getEntity(Response.class);
			
			return response;
		}else if(clientResponse.getClientResponseStatus().equals(
				ClientResponse.Status.INTERNAL_SERVER_ERROR)) {
			WiraExceptionModel model = clientResponse.getEntity(WiraExceptionModel.class);
			throw new RuntimeException(model.getCause());		
		}else{
			throw new RuntimeException(clientResponse.getEntity(String.class));
		}

		
	}


}
