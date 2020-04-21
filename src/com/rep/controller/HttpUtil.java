package com.rep.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
public static void forward(HttpServletRequest req, HttpServletResponse resp, String path) {
		
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			System.out.println("forward ¿À·ù : " + e);
		}
	}
}
