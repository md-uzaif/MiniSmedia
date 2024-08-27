package com.tap.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{
		private Connection Connection;
		private Statement stmt;
		String url="jdbc:mysql://localhost:3306/registrationdata";
		String username="root";
		String pwd="1234";
		private ResultSet res;
		
@Override
public void init() throws ServletException {
System.out.println("init in login");
}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			System.out.println(email+password);
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection=DriverManager.getConnection(url,username,pwd);
			
			String sql=" Select * from user";
			stmt = Connection.createStatement();
			
			res=stmt.executeQuery(sql);	
			System.out.println(res);
			PrintWriter pw = resp.getWriter();
			
			
			
			pw.println("<table>");
			while (res.next()) 
			{
 				String id=res.getString("id"); 
 				String name=res.getString("name"); 
				email=res.getString("email");
				String phonenumber=res.getString("phonenumber");
				String address=res.getString("address"); 
				
				pw.println("<tr>"
						+ "<td>"+id+"</td>"
						+ "<td>"+name+"</td>"
						+ "<td>"+email+"</td>"
						+ "<td>"+phonenumber+"</td>"
						+ "<td>"+address+"</td>"
						+ "</tr>");
				
				
				//pw.println("<h1>Welcome to my First JEE app</h1>");
				
			}			
			pw.println("</table>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			
			
			
			
		}
		@Override
		public void destroy() {
		System.out.println("destroy () Called login");
		}
}
