package cn.edu.zhk.jsj144.liao.ctrl.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhk.jsj144.liao.entity.admin.Admin;
import cn.edu.zhk.jsj144.liao.service.admin.AdminService;
import cn.edu.zhk.jsj144.liao.service.admin.ErrorPasswordException;
import cn.edu.zhk.jsj144.liao.service.admin.ErrorUsernameException;

/**
 * 管理员登录控制层
 * @author ele
 *
 */
public class AdminLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AdminLoginCtrl() {
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

		response.setContentType("text/html;charset=utf-8");
		Admin admin = new Admin();
		admin.setAdminname(request.getParameter("username"));
		admin.setAdminpwd(request.getParameter("password"));
		AdminService adminService = new AdminService();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			session.setAttribute("adminName", adminService.login(admin));
			out.print("OK");
		} catch (ErrorUsernameException e) {
			// TODO Auto-generated catch block
			out.print("用户名不存在");
		} catch (ErrorPasswordException e) {
			// TODO Auto-generated catch block
			out.print("密码错误");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("登录异常");
		} finally {
			out.flush();
			out.close();
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
