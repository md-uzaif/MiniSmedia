  package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	private PrintWriter pw;
	private Connection connection;
	
	
	String url="jdbc:mysql://localhost:3306/registrationdata";
	String username="root";
	String pwd="1234";
	private PreparedStatement pstmt;
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("init () checked in register");
	}
	
	@Override	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession();	
		String name=(String) session.getAttribute("name");
		String email=(String) session.getAttribute("email");
		String password=(String) session.getAttribute("password");
		String cpassword=(String) session.getAttribute("cpassword");
		String address=(String) session.getAttribute("address");
		long phonenumber=(long) session.getAttribute("phonenumber");

		
		pw =resp.getWriter();
		
		String sql="insert into user (name,email,phonenumber,password,address)"
				+ " value(?,?,?,?,?)";
		
		try {
			
			if(password.equals(cpassword))
			{   
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection(url,username,pwd);
				
				pstmt=connection.prepareStatement(sql);
				
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setLong(3, phonenumber);
				pstmt.setString(4, password);
				pstmt.setString(5, address);
				
				int x=pstmt.executeUpdate();
				
				if(x!=0) {
					resp.sendRedirect("successRegister.html");
					//pw.println(name +" Data Insert Successfully");//not required if call resp.sendRedirect();
				}
				else {
					resp.sendRedirect("failed.html");
					//pw.println("Fail to Insert");
				}
			}
			else
			{
				resp.sendRedirect("passwordMisMatch.html");
				//pw.println(name +" password not match");
			}
		

		}
		 catch (ClassNotFoundException e) {
			pw.println("Exception Occur: some problem ");
			 e.printStackTrace();
		} 
		catch(SQLException e) {
			resp.sendRedirect("DataMisMatch.html");
			//pw.println("Maybe Data already Insert in Database");
			 e.printStackTrace();

		}
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() in register");
	}

}




