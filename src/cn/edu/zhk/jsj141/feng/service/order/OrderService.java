package cn.edu.zhk.jsj141.feng.service.order;

import java.sql.SQLException;

import cn.edu.zhk.jsj141.yin.dao.order.OrderDao;
import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 */
	public void updateStatus(String oid, int status) {
		try {
			orderDao.updateStatus(oid, status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 */
	public int findStatus(String oid) {
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {
			JDBCUtils.beginTransaction();
			Order order = orderDao.load(oid);
			JDBCUtils.commitTransaction();
			return order;
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成订单
	 * @param order
	 */
	public void createOrder(Order order) {
		try {
			JDBCUtils.beginTransaction();
			orderDao.add(order);
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 我的订单
	 * @param uid
	 * @param pc
	 * @return
	 */
	public PageBean2<Order> myOrders(String uid, int pc) {
		try {
			JDBCUtils.beginTransaction();
			PageBean2<Order> pb = orderDao.findByUser(uid, pc);
			JDBCUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按状态查询
	 * @param status
	 * @param pc
	 * @return
	 */
	public PageBean2<Order> findByStatus(int status, int pc,String shopid) {
		try {
			JDBCUtils.beginTransaction();
			PageBean2<Order> pb = orderDao.findByStatus(status, pc,shopid);
			JDBCUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询所有
	 * @param pc
	 * @return
	 */
	public PageBean2<Order> findAll(int pc) {
		try {
			JDBCUtils.beginTransaction();
			PageBean2<Order> pb = orderDao.findAll(pc, null);
			JDBCUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}
}
