package cn.edu.zhk.jsj144.liao.ctrl.ma_shop;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopVerify;
import cn.edu.zhk.jsj144.liao.service.ma_shop.ShopManagementService;

/**
 * 店铺管理控制层
 * @author ele
 *
 */
public class ShopManagementCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShopManagementService shopMaService = new ShopManagementService();

	/**
	 * Constructor of the object.
	 */
	public ShopManagementCtrl() {
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
		if(method.equals("shopVerifyList")) {
			try {
				shopVerifyList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("changeVerifyStatus")) {
			try {
				changeVerifyStatus(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(method.equals("delVerifyInfo")) {
			try {
				delVerifyInfo(request, response);
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
	 * 获取开店申请记录列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void shopVerifyList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		PageBean<ShopVerify> pBean = new PageBean<ShopVerify>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 获取当前页码
		pBean.setCurrentPage(currentPage);
		pBean.setPageSize(13);  // 每页13条记录

		String keyword = URLDecoder.decode(request.getParameter("keyword"), "utf-8");
		PageBean<ShopVerify> pb = null;
		String param = "%";
		if (!keyword.equals("")) {
			param = keyword;
		}
		pb= shopMaService.getByPage(pBean, param);
		pb.setUrl("/Horizon/admin/ma_shop/ShopManagementCtrl?method=shopVerifyList");
		request.setAttribute("pb", pb);
		request.setAttribute("keyword", request.getParameter("keyword"));
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Admin/ma_shop/shop_verify.jsp");
		rd.forward(request,response);
	}

	/**
	 * 更改审核状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void changeVerifyStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String loginname = request.getParameter("loginname");
		String status = request.getParameter("status");
		String reason = request.getParameter("reason");
		shopMaService.changeVerifyStatus(loginname, status, reason);
	}
	
	/**
	 * 删除开店申请记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void delVerifyInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String loginname = request.getParameter("loginname");
		shopMaService.delVerifyInfo(loginname);
	}
}
