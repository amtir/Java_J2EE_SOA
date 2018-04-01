package com.projectJEE.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.projectJEE.common.JDBC.JDBCHelper;
import com.projectJEE.common.model.User;


public class LoggingFilter  implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, 
               ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		try {
			
			System.out.println("----------- Logging Filter -----------");

			User user = (User)(((HttpServletRequest) request).getSession().getAttribute("user"));
			
				if (user != null) {		
					
						System.out.println("User: " + user);
						
					}else {
						request.getRequestDispatcher("/").forward(request, response);
					}
			
			String strURI =  (    ((HttpServletRequest)request).getRequestURI());
			
			JDBCHelper helper;
			helper = new JDBCHelper();
			boolean flag = false;
			if (user != null) {
				flag = helper.loginUser(user);	 
			}
			
				if (	strURI.equals("/SpringMVC/") || 		strURI.equals("/SpringMVC/connectUser") || flag ) {
					chain.doFilter(request, response);
					
				} else {
					request.getRequestDispatcher("/").forward(request, response);
				}
			

		} catch (Exception ex) {
			// System.out.println(ex);   // debug message
			request.getRequestDispatcher("/").forward(request, response);
		}

	}

}
