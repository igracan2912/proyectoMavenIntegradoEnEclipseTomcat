package org.daw.servlets.teclados;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Utils{

	public static Integer getIntParameterWithCheck (String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro no incluido: '" + paramName + "'")));
			return null;
		}

		try {
			return Integer.parseInt(strParamValue);
		}
		catch (NumberFormatException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro '" + paramName + "': '" + strParamValue + "' no es un valor entero")));		
		}
		
		return null;
	}

}
