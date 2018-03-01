package cn.edu.zhk.jsj141.feng.ctrl.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj141.feng.service.product.ProductService;
import cn.edu.zhk.jsj144.liao.entity.shop.SearchShopInfo;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopInfo;
import cn.edu.zhk.jsj141.feng.service.shop.ShopService;
import cn.edu.zhk.jsj141.feng.servlet.BaseServlet;
import cn.edu.zhk.jsj141.feng.entity.comment.Comment;
import cn.edu.zhk.jsj141.feng.service.comment.CommentService;

public class ProductServlet extends BaseServlet {
	private ProductService proService = new ProductService();
	private ShopService shopService = new ShopService();
	private CommentService commentService = new CommentService();
	
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
	 * 按productid查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String productid = req.getParameter("productid");//获取链接的参数productid
		String shopid = req.getParameter("shopid");//获取链接的参数shopid
		Product product = proService.load(productid);//通过productid得到product对象
		
		List<Comment> cmlist = commentService.find(productid);//通过productid得到product对象
		
		ShopInfo sp = shopService.getShopInfo2(shopid);//通过shopid得到shopinfo对象
		req.setAttribute("sp", sp);//保存到req中
		req.setAttribute("product", product);//保存到req中
		req.setAttribute("cmlist", cmlist);	//保存到req中
		return "f:/Front/product/desc.jsp";//转发到desc.jsp
	}
	
	/**
	 * 按分类查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
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
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String cid = req.getParameter("cid");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean2<Product> pb = proService.findByCategory(cid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/Product/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/Front/product/list.jsp";
	}
	
	/**
	 * 按商品名查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBname(HttpServletRequest req, HttpServletResponse resp)
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
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String bname1 = req.getParameter("productName");
		String bname = new String(bname1.getBytes("iso8859-1"),"utf-8");
		System.out.println(bname);
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean2<Product> pb = proService.findByBname(bname, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/Product/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/Front/product/list.jsp";
	}
	
	/**
	 * 按商铺id查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByShop(HttpServletRequest req, HttpServletResponse resp)
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
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String shopid1 = req.getParameter("shopid");
		String shopid = new String(shopid1.getBytes("iso8859-1"),"utf-8");
		System.out.println(shopid);
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean2<Product> pb = proService.findByShop(shopid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/Product/list.jsp
		 */
		ShopInfo sp = shopService.getShopInfo2(shopid);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.setAttribute("sp", sp);
		return "f:/Front/product/list3.jsp";
	}
	
	
	/**
	 * 按销量排名查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findrank(HttpServletRequest req, HttpServletResponse resp)
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
		 * 3. 无需设置，但必须用到的查询条件——销量，即salesNum
		 */
		/*
		 * 4. 使用pc调用service#findByRank得到PageBean
		 */
		PageBean2<Product> pb = proService.findByRank(pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/product/list2.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/Front/product/list2.jsp";
	}
	
	/**
	 * 按店铺名查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findShop(HttpServletRequest req, HttpServletResponse resp)
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
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String shopid1 = req.getParameter("shopname");
		String shopid = new String(shopid1.getBytes("iso8859-1"),"utf-8");
		System.out.println(shopid);
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean2<SearchShopInfo> pb = proService.findShop(shopid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/Product/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/Front/product/shopList.jsp";
	}
	
	/**
	 * 获取商品图片路径
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getProImgPath(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = proService.getProImgPath(req.getParameter("shopid"), req.getParameter("imgNum"));
		
		PrintWriter out = resp.getWriter();
		out.print(path);
		out.flush();
		out.close();
	}
}
