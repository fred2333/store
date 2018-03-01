package cn.edu.zhk.jsj141.yin.dao.order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.zhk.jsj141.yin.dao.product.ProductDao;
import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.order.OrderItem;
import cn.edu.zhk.jsj141.feng.entity.pager.Expression;
import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj141.feng.entity.pager.PageConstants;
import cn.edu.zhk.jsj141.yin.util.BeanMapUtil;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj144.liao.entity.product.Product;

public class OrderDao {
private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
private ProductDao prpr = new ProductDao();
	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 * @throws SQLException 
	 */
	public int findStatus(String oid) throws SQLException {
		String sql = "select status from `order` where oid=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), oid);
		return number.intValue();
	}
	

	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String oid, int status) throws SQLException {
		String sql = "update `order` set status=? where oid=?";
		qr.update(sql, status, oid);
	}
	
	
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public Order load(String oid) throws SQLException {
		String sql = "select * from `order` where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		loadOrderItem(order);//为当前订单加载它的所有订单条目
		return order;
	}
	
	/**
	 * 生成订单
	 * @param order
	 * @throws SQLException 
	 */
	public void add(Order order) throws SQLException {
		/*
		 * 1. 插入订单
		 */
		String sql = "insert into `order` values(?,?,?,?,?,?,?)";
		Object[] params = {order.getOid(), order.getShopid(), order.getOrdertime(),
				order.getTotal(),order.getStatus(),order.getAddress(),
				order.getOwner().getUid()};
		qr.update(sql, params);
		
		/*
		 * 2. 循环遍历订单的所有条目,让每个条目生成一个Object[]
		 * 多个条目就对应Object[][]
		 * 执行批处理，完成插入订单条目
		 */
		sql = "insert into `orderitem` values(?,?,?,?,?,?,?,?)";
		int len = order.getOrderItemList().size();
		Object[][] objs = new Object[len][];
		for(int i = 0; i < len; i++){
			OrderItem item = order.getOrderItemList().get(i);
			objs[i] = new Object[]{item.getOrderItemId(),item.getQuantity(),
					item.getSubtotal(),item.getProduct().getProductid(),
					item.getProduct().getProductName(),item.getProduct().getCurrPrice(),
					item.getProduct().getImage_b(),order.getOid()};
		}
		qr.batch(sql, objs);
	}
	
	/**
	 * 按用户查询订单
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean2<Order> findByUser(String uid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("uid", "=", uid));
		return findByCriteria(exprList, pc, null);
	}
	
	/**
	 * 查询所有
	 * @param shopid 
	 */
	public PageBean2<Order> findAll(int pc, String shopid) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc,shopid);
	}
	
	/**
	 * 按状态查询
	 * @param status
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean2<Order> findByStatus(int status, int pc,String shopid) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("status", "=", status + ""));
		return findByCriteria(exprList, pc,shopid);
	}
	
	
	
	private PageBean2<Order> findByCriteria(List<Expression> exprList, int pc,String shopid) throws SQLException {
		/*
		 * 1. 得到ps
		 * 2. 得到tr
		 * 3. 得到beanList
		 * 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.ORDER_PAGE_SIZE;//每页记录数
		/*
		 * 2. 通过exprList来生成where子句
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1"); 
		List<Object> params = new ArrayList<Object>();//SQL中有问号，它是对应问号的值
		for(Expression expr : exprList) {
			/*
			 * 添加一个条件上，
			 * 1) 以and开头
			 * 2) 条件的名称
			 * 3) 条件的运算符，可以是=、!=、>、< ... is null，is null没有值
			 * 4) 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
			 */
			whereSql.append(" and ").append(expr.getName())
				.append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and productid = ?
			if(!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		if(shopid != null) {
			whereSql.append(" and shopid=?"); // 指定店铺ID
			params.add(shopid);
		}

		/*
		 * 3. 总记录数 
		 */
		String sql = "select count(*) from `order`" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();//得到了总记录数
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		sql = "select * from `order`" + whereSql + " order by ordertime desc limit ?,?";
		params.add((pc-1) * ps);//当前页首行记录的下标
		params.add(ps);//一共查询几行，就是每页记录数
		
		List<Order> beanList = qr.query(sql, new BeanListHandler<Order>(Order.class), 
				params.toArray());
		// 虽然已经获取所有的订单，但每个订单中并没有订单条目。
		// 遍历每个订单，为其加载它的所有订单条目
		for(Order order : beanList) {
			loadOrderItem(order);
		}
		
		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean2<Order> pb = new PageBean2<Order>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	/*
	 * 为指定的order载它的所有OrderItem
	 */
	private void loadOrderItem(Order order) throws SQLException {
		/*
		 * 1. 给sql语句select * from orderitem where oid=?
		 * 2. 执行之，得到List<OrderItem>
		 * 3. 设置给Order对象
		 */
		String sql = "select * from `orderitem` where oid=?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		
		order.setOrderItemList(orderItemList);
	}

	/**
	 * 把多个Map转换成多个OrderItem
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList)throws SQLException {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}
	
	/*
	 * 把一个Map转换成一个OrderItem
	 */
	private OrderItem toOrderItem(Map<String, Object> map) throws SQLException{
		OrderItem orderItem = new OrderItem();
		BeanMapUtil.mapToBean(map, orderItem);
		Product product = new Product();
		BeanMapUtil.mapToBean(map, product);
		product.setShopid(prpr.findShopid(product.getProductid()));
		orderItem.setProduct(product);
		return orderItem;
	}
}
