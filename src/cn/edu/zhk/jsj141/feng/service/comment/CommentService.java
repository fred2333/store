package cn.edu.zhk.jsj141.feng.service.comment;

import cn.edu.zhk.jsj141.feng.entity.comment.Comment;
import java.sql.SQLException;
import java.util.List;

import cn.edu.zhk.jsj141.yin.dao.comment.CommentDao;

public class CommentService {
	
	private CommentDao commentdao = new CommentDao();
	
	/**
	 * 添加评论
	 * @param cartItem
	 */
	public void add(String oid,String []cmlist,String loginname) {
		try {
			commentdao.add(oid, cmlist,loginname);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据productid查询评论集
	 * @param cartItem
	 */
	public List<Comment> find(String productid) {
		try {
			return commentdao.find(productid);
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
