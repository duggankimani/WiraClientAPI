package com.wira.client.rest.jaxb;

import javax.ws.rs.ext.ContextResolver;

import com.duggan.workflow.server.rest.model.BusinessKey;
import com.duggan.workflow.server.rest.model.Request;
import com.duggan.workflow.server.rest.model.Response;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.wira.client.rest.exception.ExTrace;
import com.wira.client.rest.exception.WiraExceptionModel;

public class JAXBProviderImpl implements ContextResolver<JSONJAXBContext> {

	private JSONJAXBContext jsonJAXBContext;

	@Override
	public JSONJAXBContext getContext(Class<?> arg0) {

		if (!(arg0.equals(BusinessKey.class)
				|| arg0.equals(Request.class) || arg0.equals(Response.class)
				|| arg0.equals(ExTrace.class) || arg0.equals(WiraExceptionModel.class)
				)) {
			return null;
		}

		if (jsonJAXBContext != null) {
			return jsonJAXBContext;
		}

		try {
			jsonJAXBContext = new JSONJAXBContext(BusinessKey.class,
					Request.class, Response.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return jsonJAXBContext;
	}
}
