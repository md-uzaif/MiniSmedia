package com.tap.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkEmail extends HttpServlet
{
	
	@Override
	public void init() throws ServletException {
		System.out.println("checked email");
		
		
	}
	
	
	
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
			String email= req.getParameter("email");
			String name= req.getParameter("name");
			Long phonenumber=Long.parseLong(req.getParameter("phoneNumber"));
			String password= req.getParameter("password");
			String cpassword= req.getParameter("cpassword");
			String address= req.getParameter("address");
			
			if(email.length()>=10)
			{	
//				resp.getWriter().println("Email Checked");
//				RequestDispatcher rd=req.getRequestDispatcher("/register"); //below notes for understanding
//				(rd.forward(req, resp);	// only displays the output of the called servlet,
//				rd.include(req, resp);	//displays the outputs of both the calling and called servlets.
//				see  below notes why used session (to store data)				
				HttpSession session= req.getSession();	
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("phonenumber", phonenumber);
				session.setAttribute("password", password);
				session.setAttribute("cpassword", cpassword);
				session.setAttribute("address", address);
				
				resp.sendRedirect("register");	//below notes for understanding 

			
			}
			else 
			{
				resp.getWriter().println("Email Error");
			}
	
	}
	@Override
	public void destroy() {
		System.out.println("Destroy () in checked email");
	}

}


/*
Request Dispatcher: Sends the request and response objects to the target servlet without changing the URL pattern in the browser.
Send Redirect:      Sends a new request to the target servlet, changing the URL pattern in the browser.
@the concept of "sessions" as a solution to the data loss problem when using `sendRedirect`. 
Sessions are a mechanism for storing data across multiple requests within a user session, 
ensuring that data is available even after a redirect.@

*/