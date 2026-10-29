package org.daw.servlets.categoriasYTeclados;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Utils{

	// MÉTODO ORIGINAL: Integer obligatorio
	public static Integer getIntParameterWithCheck (String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
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

	// 1. NUEVO MÉTODO: String obligatorio
	public static String getStringParameterWithCheck (String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro no incluido o vacío: '" + paramName + "'")));
			return null;
		}

		return strParamValue;
	}

	// 2. NUEVO MÉTODO: Integer opcional
	public static Integer getOptionalIntParameter (String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			return null; // Al ser opcional, devolvemos null sin generar error
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

	// 3. NUEVO MÉTODO: String opcional
	public static String getOptionalStringParameter (String paramName, HttpServletRequest request) {
		
		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			return null; // Al ser opcional, devolvemos null directamente
		}

		return strParamValue;
	}

}