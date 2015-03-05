package com.ourchem.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { 
		 String username = req.getParameter("username");
		 String password = req.getParameter("password");
		 if("admin".equals(username) && "password".equals(password)){ 
			 req.getRequestDispatcher("success.jsp").forward(req, resp);
		 }else{
			 req.setAttribute("is_error", "true");
			 req.getRequestDispatcher("login.jsp").forward(req, resp);
		 }
		 
	}

}
