package org.daw.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Utils {

	/**
	 * MÉTODO AUXILIAR: Busca el valor primero como parámetro (URL o Form Body).
	 * Si no lo encuentra, lo busca como Header (Cabecera).
	 */
	private static String getParamOrHeader(String name, HttpServletRequest request) {
		String value = request.getParameter(name);
		if (value == null || value.trim().isEmpty()) {
			value = request.getHeader(name);
		}
		return value;
	}

	// MÉTODO ORIGINAL: Integer obligatorio
	public static Integer getIntParameterWithCheck(String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro o Cabecera no incluida: '" + paramName + "'")));
			return null;
		}

		try {
			return Integer.parseInt(strParamValue);
		} catch (NumberFormatException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"El valor de '" + paramName + "': '" + strParamValue + "' no es un número entero")));		
		}
		
		return null;
	}

	// 1. NUEVO MÉTODO: String obligatorio
	public static String getStringParameterWithCheck(String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro o Cabecera no incluida o vacía: '" + paramName + "'")));
			return null;
		}

		return strParamValue;
	}

	// 2. NUEVO MÉTODO: Integer opcional
	public static Integer getOptionalIntParameter(String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			return null; // Al ser opcional, devolvemos null sin generar error
		}

		try {
			return Integer.parseInt(strParamValue);
		} catch (NumberFormatException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"El valor opcional '" + paramName + "': '" + strParamValue + "' no es un número entero")));		
		}
		
		return null;
	}

	// 3. NUEVO MÉTODO: String opcional
	public static String getOptionalStringParameter(String paramName, HttpServletRequest request) {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			return null; // Al ser opcional, devolvemos null directamente
		}

		return strParamValue;
	}
	
	public static String obtenerVariableEntorno(String nombre) {
		String valor = System.getenv(nombre);

		if (valor == null) {
			throw new IllegalStateException(
				"La variable de entorno " + nombre + " es obligatoria."
			);
		}

		return valor;
	}
	
	public static boolean comprobarSesion(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("usuario") == null) {
			JSONUtils.enviarError(response,
					HttpServletResponse.SC_UNAUTHORIZED,
					"Es necesario autenticarse para realizar esta operación.");
			return false;
		}
		return true;
	}
	
	// NUEVO MÉTODO: Fecha obligatoria
	public static LocalDate getDateParameterWithCheck(String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"Parámetro o Cabecera no incluida o vacía: '" + paramName + "'")));
			return null;
		}

		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(strParamValue, formato);
		} catch (DateTimeParseException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"El valor '" + paramName + "': '" + strParamValue + "' no es una fecha válida (Formato esperado: YYYY-MM-DD)")));		
		}
		return null;
	}

	// NUEVO MÉTODO: Fecha opcional
	public static LocalDate getOptionalDateParameter(String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strParamValue = getParamOrHeader(paramName, request);
		if (strParamValue == null || strParamValue.trim().isEmpty()) {
			return null; // Al ser opcional, devolvemos null sin generar error
		}

		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(strParamValue, formato);
		} catch (DateTimeParseException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print(JSONUtils.toJSON(new Error(
					"El valor opcional '" + paramName + "': '" + strParamValue + "' no es una fecha válida (Formato esperado: YYYY-MM-DD)")));		
		}
		
		return null;
	}
}