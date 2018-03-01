package cn.edu.zhk.jsj144.liao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = request.getSession();
		if(session.getAttribute("adminName") == null || session.getAttribute("adminName").equals("")) {
			if(request.getServletPath().equals("/Back_Admin/login.jsp") || request.getServletPath().equals("/Back_Admin/css/login.css")) {
				arg2.doFilter(arg0, arg1);
			} else {
				System.out.println("block  " + request.getServletPath() + "?" + request.getQueryString());
				request.setAttribute("gotoURL", request.getServletPath() + "?" + request.getQueryString());
				RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/login.jsp");
				rd.forward(request,response);
			}
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
