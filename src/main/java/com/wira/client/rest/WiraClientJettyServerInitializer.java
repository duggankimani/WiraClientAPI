package com.wira.client.rest;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * This client will Bootstrap and expose the client
 * for external requests 
 *  
 * <p>
 * @author duggan
 *
 */
public class WiraClientJettyServerInitializer {

	static boolean initd=false;
	public static void init() throws Exception{
		if(initd){
			//subsequent calls halted here
			return;
		}
		
		initd=true;
		
        Server server = new Server(9999);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        
        Map<String,Object> initMap = new HashMap<String, Object>();
        initMap.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        initMap.put("com.sun.jersey.config.property.packages", "com.wira.client.rest");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(new PackagesResourceConfig(initMap)));
        
        context.addServlet(servletHolder, "/*");
        server.start();
        server.join();

	}
	
	public static void main(String[] args) throws Exception {
		WiraClientJettyServerInitializer.init();
	}
}
