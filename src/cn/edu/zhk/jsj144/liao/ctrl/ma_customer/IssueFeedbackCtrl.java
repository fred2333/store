package cn.edu.zhk.jsj144.liao.ctrl.ma_customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj141.feng.entity.User;
import cn.edu.zhk.jsj144.liao.entity.issue.Issue;
import cn.edu.zhk.jsj144.liao.service.ma_customer.CustomerManagementService;

public class IssueFeedbackCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public IssueFeedbackCtrl() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		CustomerManagementService cuService = new CustomerManagementService();
		Issue issue = new Issue();
		User user = (User) request.getSession().getAttribute("sessionUser");
		String loginname = user.getLoginname();
		issue.setLoginname(loginname);
		issue.setEmail(request.getParameter("email"));
		issue.setIssue_title(request.getParameter("i_title"));
		issue.setIssue_con(request.getParameter("i_text"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		issue.setDatetime(df.format(new Date()));
		try {
			cuService.AddIssueFeedback(issue);
			PrintWriter out = response.getWriter();
			out.println("<h2 align='center'>您的问题已反馈到运营商管理员，管理员将会通过您提供的E-Mail地址进行回复。</h2>");
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
