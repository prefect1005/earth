package com.gravity.api.support.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bizhenchao
 */
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = -6217865898817274598L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log(req);

		req.getRequestDispatcher("/errors/error.html").forward(req, resp);
	}

	private void log(HttpServletRequest req) {
		String uri = (String)req.getAttribute("javax.servlet.error.request_uri");
		Throwable exception = (Throwable)req.getAttribute("javax.servlet.error.exception");
		Object statusCode = req.getAttribute("javax.servlet.error.status_code");

		if (exception != null) {
			LOGGER.error("Error Sevlet invoked. uri : {}, resp code : {}", uri, statusCode, exception);
		} else {
			LOGGER.error("Error Servlet invoked. uri : {}, resp code : {}", uri, statusCode);
		}
	}

}
