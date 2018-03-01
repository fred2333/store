package cn.edu.zhk.jsj141.yin.dao.comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.edu.zhk.jsj141.feng.commons.CommonUtils;
import cn.edu.zhk.jsj141.feng.entity.comment.Comment;
import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.order.OrderItem;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj141.yin.dao.order.OrderDao;

public class CommentDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	private OrderDao odd = new OrderDao();
	/*
	 * 把一个Map映射成一个Comment
	 */
	private Comment toComment(Map<String,Object> map) {
		if(map == null || map.size() == 0) return null;
		Comment comment = CommonUtils.toBean(map, Comment.class);
		Product product = CommonUtils.toBean(map, Product.class);
		Order order = CommonUtils.toBean(map, Order.class);
		comment.setProduct(product);
		comment.setOrder(order);
		return comment;
	}
	
	/*
	 * 把多个Map(List<Map>)映射成多个Comment(List<Comment>)
	 */
	private List<Comment> toCommentList(List<Map<String,Object>> mapList) {
		List<Comment> commentList = new ArrayList<Comment>();
		for(Map<String,Object> map : mapList) {
			Comment cartItem = toComment(map);
			commentList.add(cartItem);
		}
		return commentList;
	}
	
	/**
	 * 生成评论
	 * @param order
	 * @throws SQLException 
	 */
	public void add(String oid,String []cmlist,String loginname) throws SQLException { 
		String sql = "insert into `comment` values(?,?,?,?)";
		Order order = odd.load(oid);
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for(int i = 0; i < len; i++){
			OrderItem item = order.getOrderItemList().get(i);
			objs[i] = new Object[]{CommonUtils.uuid(),
					item.getProduct().getProductid(),cmlist[i],loginname
					};
		}
		qr.batch(sql, objs);
	}
	
	public List<Comment> find(String productid)throws SQLException{
		String sql = "select * from comment where productid= ?";
		List<Comment> cmlist = qr.query(sql, new BeanListHandler<Comment>(Comment.class),productid);
		return cmlist;
	}
}
