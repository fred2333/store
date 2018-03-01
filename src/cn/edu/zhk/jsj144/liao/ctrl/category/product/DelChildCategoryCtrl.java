package cn.edu.zhk.jsj144.liao.ctrl.category.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj144.liao.service.category.product.CategoryService;

/**
 * 删除二级分类
 * @author ele
 *
 */
public class DelChildCategoryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DelChildCategoryCtrl() {
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
		PrintWriter out = response.getWriter();
		CategoryService categoryService = new CategoryService();
		String cid = request.getParameter("id");
		int cnt = categoryService.findProductCountByCategory(cid);
		if(cnt > 0) {
			out.write("{\"msg\":\"err\"}"); // 提示该二级分类下还有商品，不能删除
		} else {
			categoryService.delete(cid);
			out.write("{\"msg\":\"ok\"}");
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
