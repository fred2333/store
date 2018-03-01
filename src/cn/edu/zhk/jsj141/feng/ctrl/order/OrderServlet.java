package cn.edu.zhk.jsj141.feng.ctrl.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import cn.edu.zhk.jsj141.feng.commons.CommonUtils;
import cn.edu.zhk.jsj141.feng.entity.CartItem;
import cn.edu.zhk.jsj141.feng.service.shoppingCart.CartItemService;
import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.order.OrderItem;
import cn.edu.zhk.jsj141.feng.service.order.OrderService;
import cn.edu.zhk.jsj141.feng.service.shop.ShopService;
import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj141.feng.entity.User;
import cn.edu.zhk.jsj141.feng.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	private CartItemService cartItemService = new CartItemService();
	private ShopService shopService = new ShopService();
	
	/**
	 * 获取当前页码
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/Horizon/ProductServlet?methed=findByCategory&cid=xxx&pc=3
	 * /Horizon/ProductServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
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
	 * 支付准备
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String paymentPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("order", orderService.load(req.getParameter("oid")));
		return "f:/Front/order/pay.jsp";
	}

	/**
	 * 支付方法
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String payment(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String p2_Order = req.getParameter("oid");//订单编码
			orderService.updateStatus(p2_Order, 2);
				req.setAttribute("code", "success");
				req.setAttribute("msg", "恭喜，支付成功！");
				return "f:/Front/msg2.jsp";				
	}
	
	/**
	 * 取消订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 1) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能取消！");
			return "f:/Front/msg2.jsp";
		}
		req.setAttribute("code", "success");
		req.setAttribute("msg", "您的订单已取消，您不后悔吗！");
		req.setAttribute("oid", oid);
		req.setAttribute("check", "check");
		return "f:/Front/msg2.jsp";	
		
	}
	
	public String cancel2(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String oid = req.getParameter("oid");
		req.removeAttribute("check");
		req.setAttribute("code", "success");
		req.setAttribute("msg", "订单已取消！");
		orderService.updateStatus(oid, 5);//设置状态为取消！
		return "f:/Front/msg2.jsp";
	}
	
	/**
	 * 确认收货
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		/*
		 * 校验订单状态
		 */
		int status = orderService.findStatus(oid);
		if(status != 3) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "状态不对，不能确认收货！");
			return "f:/Front/msg2.jsp";
		}
		orderService.updateStatus(oid, 4);//设置状态为交易成功！
		req.setAttribute("code", "success");
		req.setAttribute("msg", "恭喜，交易成功！");
		return "f:/Front/msg2.jsp";		
	}
	
	/**
	 * 加载订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
		req.setAttribute("btn", btn);
		return "/Front/order/desc.jsp";
	}
	
	/**
	 * 加载订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load2(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		Order order = orderService.load(oid);
		req.setAttribute("order", order);
		String btn = req.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
		req.setAttribute("btn", btn);
		return "/Front/order/desc2.jsp";
	}
	
	/**
	 * 生成订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("all")
	public String createOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取所有购物车条目的id，查询之
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		
		List<Order> orderL = new ArrayList<Order>();
		
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		if(cartItemList.size() == 0) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "您没有选择要购买的商品，不能下单！");
			return "f:/Front/msg2.jsp";
		}
		Set set = new LinkedHashSet();
		for(CartItem cartItem : cartItemList){
			String Shopname1 = cartItem.getShopName();
			set.add(Shopname1);
		}
		List<String> relist = new ArrayList<String>();
		Iterator<String> it=set.iterator();
        while(it.hasNext()){
        	relist.add(it.next());
        }
		int llsize = relist.size();
		for(int i=0;i<llsize;i++){
		/*
		 * 2. 创建一个Order
		 */
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置主键
		order.setOrdertime(String.format("%tF %<tT", new Date()));//下单时间
		order.setStatus(1);//设置状态，1表示未付款
		order.setAddress(req.getParameter("address"));//设置收货地址
		User owner = (User)req.getSession().getAttribute("sessionUser");
		order.setOwner(owner);
		String shopiddd=null;
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getShopName().equals(relist.get(i))){
				shopiddd = cartItem.getProduct().getShopid();
				total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
			}
		}
		order.setShopid(shopiddd);
		order.setTotal(total.doubleValue());//设置总计
		
		/*
		 * 3. 创建List<OrderItem>
		 * 一个CartItem对应一个OrderItem
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getShopName().equals(relist.get(i)))
			{
		 	 OrderItem orderItem = new OrderItem();
			 orderItem.setOrderItemId(CommonUtils.uuid());//设置主键
			 orderItem.setQuantity(cartItem.getQuantity());
			 orderItem.setSubtotal(cartItem.getSubtotal());
			 orderItem.setProduct(cartItem.getProduct());
			 orderItem.setOrder(order);
			 orderItemList.add(orderItem);
			}
		    }
		
		order.setOrderItemList(orderItemList);
		
		/*
		 * 4. 调用service完成添加
		 */
		orderService.createOrder(order);
		orderL.add(order);
		}
		// 删除购物车条目
		cartItemService.batchDelete(cartItemIds);
		/*
		 * 5. 保存订单，转发到ordersucc.jsp
		 */
		req.setAttribute("orderL", orderL);
		return "f:/Front/order/ordersucc.jsp";
	}
	
	/**
	 * 我的订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 从当前session中获取User
		 */
		User user = (User)req.getSession().getAttribute("sessionUser");
		
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean2<Order> pb = orderService.myOrders(user.getUid(), pc);
		
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/Product/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/Front/order/list.jsp";
	}
}
