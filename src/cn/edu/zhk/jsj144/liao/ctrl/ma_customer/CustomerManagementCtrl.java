package cn.edu.zhk.jsj144.liao.ctrl.ma_customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj141.feng.entity.user.User;
import cn.edu.zhk.jsj144.liao.entity.issue.Issue;
import cn.edu.zhk.jsj144.liao.entity.ma_customer.Tr_record;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.service.ma_customer.CustomerManagementService;

/**
 * 顾客管理控制层
 * @author ele
 *
 */
public class CustomerManagementCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerManagementService cuService = new CustomerManagementService();

	/**
	 * Constructor of the object.
	 */
	public CustomerManagementCtrl() {
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

		response.setContentType("text/html");
		String method = request.getParameter("method");
		if(method.equals("getUserList")) {
			try {
				getUserList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("resetUserPwd")) {
			try {
				resetUserPwd(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("delUser")) {
			try {
				delUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("getTrRecordList")) {
			try {
				getTrRecordList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("getOrderList")) {
			try {
				getOrderList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("getIssueList")) {
			try {
				getIssueList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("delIssueList")) {
			try {
				delIssueList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	/**
	 * 获取顾客信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void getUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PageBean<User> pBean = new PageBean<User>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 获取当前页码
		pBean.setCurrentPage(currentPage);
		pBean.setPageSize(20);  // 每页20条记录

		String keyword = URLDecoder.decode(request.getParameter("keyword"), "utf-8");
		PageBean<User> pb = null;
		String[] params = {"%"};
		if (!keyword.equals("")) {
			params[0] = keyword;
		}
		pb= cuService.getByPage(1, pBean, params);
		pb.setUrl("/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getUserList");
		request.setAttribute("pb", pb);
		request.setAttribute("keyword", request.getParameter("keyword"));
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/ma_customer/per_info.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 重置用户密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void resetUserPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String uid = request.getParameter("uid");
		cuService.resetUserPwd(uid);
	}
	
	/**
	 * 删除无下单和开店记录的用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String uid = request.getParameter("uid");
		PrintWriter out = response.getWriter();
		if( cuService.delUser(uid)) {  // 能删除则输出1，否则输出0
			out.print("1");
		} else {
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 获取顾客交易记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void getTrRecordList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PageBean<Tr_record> pBean = new PageBean<Tr_record>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 获取当前页码
		pBean.setCurrentPage(currentPage);
		pBean.setPageSize(20);  // 每页20条记录

		String loginName = URLDecoder.decode(request.getParameter("loginName"), "utf-8");
		PageBean<Tr_record> pb = null;
		String[] params = {"%"};
		if (!loginName.equals("")) {
			params[0] = loginName;
		}
		pb= cuService.getByPage(2, pBean, params);
		pb.setUrl("/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getTrRecordList&loginName=" + request.getParameter("loginName"));
		request.setAttribute("pb", pb);
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/ma_customer/trans_record.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 获取顾客交易详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void getOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PageBean<Tr_record> pBean = new PageBean<Tr_record>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 获取当前页码
		pBean.setCurrentPage(currentPage);
		pBean.setPageSize(20);  // 每页20条记录

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String loginName = URLDecoder.decode(request.getParameter("loginName"), "utf-8");
		PageBean<Tr_record> pb = null;
		String[] params = {loginName, "1=1", "1=1"};
		if (!startDate.equals("")) {
			params[1] = "ordertime >='" + startDate + "'";
		}
		if (!endDate.equals("")) {
			params[2] = "ordertime <='" + endDate + "'";
		}
		pb= cuService.getByPage(3, pBean, params);
		pb.setUrl("/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getOrderList&loginName=" + request.getParameter("loginName") + "&startDate=" + startDate + "&endDate=" + endDate);
		request.setAttribute("pb", pb);
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/ma_customer/trans_record_sub.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 获取顾客问题
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void getIssueList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PageBean<Issue> pBean = new PageBean<Issue>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 获取当前页码
		pBean.setCurrentPage(currentPage);
		pBean.setPageSize(13);  // 每页13条记录

		String keyword = URLDecoder.decode(request.getParameter("keyword"), "utf-8");
		PageBean<Issue> pb = null;
		String[] params = {"%"};
		if (!keyword.equals("")) {
			params[0] = keyword;
		}
		pb= cuService.getByPage(4, pBean, params);
		pb.setUrl("/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getIssueList");
		request.setAttribute("pb", pb);
		request.setAttribute("keyword", request.getParameter("keyword"));
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/ma_customer/issue_feedback.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 删除顾客问题
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void delIssueList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String issueid = request.getParameter("issueid");
		cuService.delIssueList(issueid);
	}

}
