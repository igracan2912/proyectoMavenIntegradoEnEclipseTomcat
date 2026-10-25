package org.daw.servlets.teclados;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	private static ObjectMapper jacksonMapper = new ObjectMapper();
	
	
	/**
	 * 
	 * @param c
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String toJSON (Object o) throws JsonProcessingException {
		return jacksonMapper.writeValueAsString(o);
	}
	
	
}
