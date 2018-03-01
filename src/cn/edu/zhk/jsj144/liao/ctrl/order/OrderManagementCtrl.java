package cn.edu.zhk.jsj144.liao.ctrl.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj144.liao.service.order.OrderService;

public class OrderManagementCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String method = request.getParameter("method");
		if(method.equals("load")) {
			load(request, response);
		} else if(method.equals("findAll")) {
			findAll(request, response);
		} else if(method.equals("findByStatus")) {
			findByStatus(request, response);
		} else if(method.equals("deliver")) {
			deliver(request, response);
		} else if(method.equals("cancel")) {
			cancel(request, response);
		} 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	/**
	 * 获取当前页码
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request) {
		int pc = 1;
		String param = request.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	
	/**
	 * 获取url，用于页面中的分页导航
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	/**
	 * 查看所有订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pc = getPc(request); // 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		String url = getUrl(request);
		
		// 使用pc和cid调用service#findByCategory得到PageBean2
		PageBean2<Order> pb = orderService.findAll(pc,request.getParameter("shopid"));
		// 给PageBean2设置url，保存PageBean2，转发到/jsps/Product/list.jsp
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Shop/ma_order/orderMain.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 按状态查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findByStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		int pc = getPc(request);
		// 得到url：...
		String url = getUrl(request);
		// 获取链接参数：status
		int status = Integer.parseInt(request.getParameter("status"));
		// 使用pc和cid调用service#findByCategory得到PageBean2
		PageBean2<Order> pb = orderService.findByStatus(status, pc, request.getParameter("shopid"));
		// 给PageBean2设置url，保存PageBean2，转发到/jsps/Product/list.jsp
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Shop/ma_order/orderMain.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 查看订单详细信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
		request.setAttribute("btn", btn);
		RequestDispatcher rd=request.getRequestDispatcher("/Back_Shop/ma_order/orderDesc.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 1) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "订单状态不符合取消条件，无法取消！");
		} else {
			orderService.updateStatus(oid, 5);//设置状态为取消！
			request.setAttribute("code", "success");
			request.setAttribute("msg", "本订单已取消。");
		}
		RequestDispatcher rd=request.getRequestDispatcher("/order/OrderManagementCtrl?method=load&oid="+oid);
		rd.forward(request,response);
	}
	
	/**
	 * 发货功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deliver(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 2) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", "订单状态不符合发货条件。");
		} else {
			orderService.updateStatus(oid, 3);//设置状态为取消！
			request.setAttribute("code", "success");
			request.setAttribute("msg", "本订单的商品已发货！");
		}
		RequestDispatcher rd=request.getRequestDispatcher("/order/OrderManagementCtrl?method=load&oid="+oid);
		rd.forward(request,response);
	}

}
