package cn.edu.zhk.jsj141.feng.entity.comment;

import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj141.feng.entity.order.Order;;

public class Comment {
	private String commentid;
	private String productid;
	private String desc;
	private Product product;// 评论对应的商品
	private Order order;  //评论对应的订单
	private String loginname;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", productid=" + productid
				+ ", desc=" + desc + ", product=" + product + ", order="
				+ order + ", loginname=" + loginname + "]";
	}
	
}
