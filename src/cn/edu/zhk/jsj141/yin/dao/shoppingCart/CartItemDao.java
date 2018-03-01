package cn.edu.zhk.jsj141.yin.dao.shoppingCart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.edu.zhk.jsj141.yin.dao.shop.ShopDao;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj141.feng.commons.CommonUtils;
import cn.edu.zhk.jsj141.feng.entity.CartItem;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj141.feng.entity.User;

public class CartItemDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	private ShopDao shh = new ShopDao();
	
	/*
	 * 用来生成where子句
	 */
	private String toWhereSql(int len) {
		StringBuilder sb = new StringBuilder("cartItemId in(");
		for(int i = 0; i < len; i++) {
			sb.append("?");
			if(i < len - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 加载多个CartItem
	 * @param cartItemIds
	 * @return
	 * @throws SQLException 
	 */
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {
		/*
		 * 1. 把cartItemIds转换成数组
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		/*
		 * 2. 生成wehre子句
		 */
		
		String whereSql = toWhereSql(cartItemIdArray.length);
		/*
		 * 3. 生成sql语句
		 */
		String sql = "select * from cartitem c, product b where c.productid=b.productid and " + whereSql;
		/*
		 * 4. 执行sql，返回List<CartItem>
		 */
		return toCartItemList(qr.query(sql, new MapListHandler(), cartItemIdArray));
	}
	
	/**
	 * 按id查询
	 * @param cartItemId
	 * @return
	 * @throws SQLException 
	 */
	public CartItem findByCartItemId(String cartItemId) throws SQLException {
		String sql = "select * from cartItem c, product b where c.productid=b.productid and c.cartItemId=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), cartItemId);
		return toCartItem(map);
	}
	
	/**
	 * 批量删除
	 * @param cartItemIds
	 * @throws SQLException
	 */
	public void batchDelete(String cartItemIds) throws SQLException {
		/*
		 * 需要先把cartItemIds转换成数组
		 * 1. 把cartItemIds转换成一个where子句
		 * 2. 与delete from 连接在一起，然后执行之
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhereSql(cartItemIdArray.length);
		String sql = "delete from cartitem where " + whereSql;
		qr.update(sql, cartItemIdArray);//其中cartItemIdArray必须是Object类型的数组！
	}
	
	/**
	 * 查询某个用户的某个商品的购物车条目是否存在
	 * @throws SQLException 
	 */
	public CartItem findByUidAndproductid(String uid, String productid) throws SQLException {
		String sql = "select * from cartitem where uid=? and productid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), uid, productid);
		CartItem cartItem = toCartItem(map);
		return cartItem;
	}
	
	/**
	 * 修改指定条目的数量
	 * @param cartItemId
	 * @param quantity
	 * @throws SQLException 
	 */
	public void updateQuantity(String cartItemId, int quantity) throws SQLException {
		String sql = "update cartitem set quantity=? where cartItemId=?";
		qr.update(sql, quantity, cartItemId);
	}
	
	/**
	 * 添加条目
	 * @param cartItem
	 * @throws SQLException 
	 */
	public void addCartItem(CartItem cartItem) throws SQLException {
		String sql = "insert into cartitem(cartItemId, quantity, productid, uid)" +
				" values(?,?,?,?)";
		Object[] params = {cartItem.getCartItemId(), cartItem.getQuantity(),
				cartItem.getProduct().getProductid(), cartItem.getUser().getUid()};
		qr.update(sql, params);
	}
	
	/*
	 * 把一个Map映射成一个Cartitem
	 */
	private CartItem toCartItem(Map<String,Object> map) throws SQLException {
		if(map == null || map.size() == 0) return null;
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Product product = CommonUtils.toBean(map, Product.class);
		User user = CommonUtils.toBean(map, User.class);
		cartItem.setProduct(product);
		cartItem.setUser(user);
		return cartItem;
	}
	
	/*
	 * 把一个Map映射成一个Cartitem  2222222
	 */
	private CartItem toCartItem2(Map<String,Object> map) throws SQLException {
		if(map == null || map.size() == 0) return null;
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Product product = CommonUtils.toBean(map, Product.class);
		User user = CommonUtils.toBean(map, User.class);
		String shoname = shh.getShopName(product.getShopid());
		cartItem.setProduct(product);
		cartItem.setShopName(shoname);
		cartItem.setUser(user);
		return cartItem;
	}
	
	
	
	/*
	 * 把多个Map(List<Map>)映射成多个CartItem(List<CartItem>)
	 */
	private List<CartItem> toCartItemList(List<Map<String,Object>> mapList) throws SQLException{
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		for(Map<String,Object> map : mapList) {
			CartItem cartItem = toCartItem2(map);
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}
	
	/**
	 * 通过用户查询购物车条目
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public List<CartItem> findByUser(String uid) throws SQLException {
		String sql = "select * from cartitem c, product b where c.productid=b.productid and uid=? order by c.orderBy";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), uid);
		return toCartItemList(mapList);
	}
}
