package cn.edu.zhk.jsj144.liao.ctrl.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import cn.edu.zhk.jsj141.feng.entity.User;
import cn.edu.zhk.jsj141.yin.util.JsonUtil;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopVerify;
import cn.edu.zhk.jsj144.liao.service.shop.ShopService;

/**
 * 开店申请控制层
 * @author ele
 *
 */
public class SetUpShopCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SetUpShopCtrl() {
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
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(request.getInputStream(), "utf-8"));  
		String data = "", str = "";
		while ((str = reader.readLine()) != null) {  //读取前端数据
			data = data + str;
		}
		JSONObject jsonObject = JSONObject.fromObject(JsonUtil.urlToJson(data));
		ShopVerify shopVerify = (ShopVerify) JSONObject.toBean(jsonObject, ShopVerify.class);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		shopVerify.setLoginname(user.getLoginname());
		shopVerify.setStatus("");
		
		ShopService shopService = new ShopService();
		shopService.setUpShop(shopVerify);
		
		response.sendRedirect("/Horizon/Front/user/setUpShopMsg.jsp?msg=w");
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
