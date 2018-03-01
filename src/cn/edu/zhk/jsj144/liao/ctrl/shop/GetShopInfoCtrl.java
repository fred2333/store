package cn.edu.zhk.jsj144.liao.ctrl.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.zhk.jsj141.feng.entity.User;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopInfo;
import cn.edu.zhk.jsj144.liao.service.shop.ShopService;

/**
 * 获取店铺信息
 * @author ele
 *
 */
public class GetShopInfoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public GetShopInfoCtrl() {
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
		ShopService shopService = new ShopService();
		
		User user = (User) request.getSession().getAttribute("sessionUser");
		String loginname = user.getLoginname();
		ShopInfo shopInfo = shopService.getShopInfo(loginname);
        
        if(request.getParameter("check") != null) {  // 检查是否有店铺存在，只返回判断值0和1
        	PrintWriter out = response.getWriter();
        	if(shopInfo != null) {
        		out.print("1");
        	} else {
        		out.print("0");
        	}
        	out.flush();
        	out.close();
        } else {  // 跳转到店铺信息页面
        	request.setAttribute("shopInfo", shopInfo);
        	RequestDispatcher rd=request.getRequestDispatcher("/Back_Shop/ma_shopInfo/infoMain.jsp");
            rd.forward(request,response);
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
