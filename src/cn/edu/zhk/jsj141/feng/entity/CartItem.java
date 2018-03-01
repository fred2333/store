package cn.edu.zhk.jsj141.feng.entity;

import java.math.BigDecimal;

import cn.edu.zhk.jsj141.feng.entity.User;
import cn.edu.zhk.jsj144.liao.entity.product.Product;;

public class CartItem {
	private String cartItemId;// 主键
	private int quantity;// 数量
	private Product product;// 条目对应的商品
	private User user;// 所属用户
	private String shopName; //商品所属商店
	// 添加小计方法
	public double getSubtotal() {
		/*
		 * 使用BigDecimal不会有误差
		 * 要求必须使用String类型构造器
		 */
		BigDecimal b1 = new BigDecimal(this.getProduct().getCurrPrice() + "");
		BigDecimal b2 = new BigDecimal(this.getQuantity() + "");
		BigDecimal b3 = b1.multiply(b2);
		return b3.doubleValue();
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
