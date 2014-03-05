package com.wira.client.rest.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class RequestHandlerRegistry {


	static Logger logger = Logger.getLogger(RequestHandlerRegistry.class);
	
	static Map<String,Class<?>> commandHandlers = new HashMap<>();
	
	static Class<?> defaultHandler = null;
	
	public static void registerDefaultHandler(Class<?> clazz){
		if(!RequestHandlerDelegate.class.isAssignableFrom(clazz)){
			throw new RuntimeException("Only implementations of RequestHandlerDelegate allowed");
		}
		
		defaultHandler = clazz;
	}
	
	public static void registerHandler(String commandName, Class<?> clazz){
		if(!RequestHandlerDelegate.class.isAssignableFrom(clazz)){
			throw new RuntimeException("Only implementations of RequestHandlerDelegate allowed");
		}
		commandHandlers.put(commandName, clazz);
	}
	
	public static RequestHandlerDelegate getCommandHandler(String commandName){
		Class<?> clazz = commandHandlers.get(commandName);
		if(clazz==null){
			if(defaultHandler!=null){
				clazz=defaultHandler;
			}else{
				throw new RuntimeException("No Request handler found for command \""+commandName+"\"");
			}
		}
		RequestHandlerDelegate delegate = null;
		
		try{
			delegate = (RequestHandlerDelegate)clazz.newInstance();
		}catch(Exception e){
			throw new RuntimeException("Failed to initialize "+clazz.getName()+". Ensure that you have provided a Zero argument contructor.");
		}
		
		return delegate;
	}
	
}
