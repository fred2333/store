package cn.edu.zhk.jsj141.feng.ctrl.comment;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhk.jsj141.feng.service.comment.CommentService;
import cn.edu.zhk.jsj141.feng.servlet.BaseServlet;

public class CommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService commentService = new CommentService();
	/**
	 * 添加评论
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cm = req.getParameter("comm");
		String oid = req.getParameter("orderid");
		String loginname=req.getParameter("lname");
		String []cmlist = cm.split(",");
		commentService.add(oid, cmlist,loginname);
		req.setAttribute("msg", "上传评论成功！");
		req.setAttribute("code", "success");
		return "f:/Front/msg3.jsp";
	}
	}
