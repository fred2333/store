package cn.edu.zhk.jsj141.yin.dao.customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.user.User;
import cn.edu.zhk.jsj141.yin.util.BeanMapUtil;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj144.liao.entity.issue.Issue;
import cn.edu.zhk.jsj144.liao.entity.ma_customer.Tr_record;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;

/**
 * 顾客管理DAO层
 */
public class CustomerManagementDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 获取顾客信息总条数
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int getUserTotalCount(String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM `user` WHERE loginname like ? or email like ? or phone like ? ";
		Object[] params2 = {"%"+params[0]+"%", "%"+params[0]+"%", "%"+params[0]+"%"};
		Map<String,Object> map = qr.query(sql, new MapHandler(), params2);
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取顾客信息
	 * @param pBean
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<User> getUserCurrentPageBean(PageBean<User> pBean,
			String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `user` WHERE loginname like ? or email like ? or phone like ? LIMIT ?,?";
		Object[] params2 = {"%"+params[0]+"%", "%"+params[0]+"%", "%"+params[0]+"%", (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};

		return qr.query(sql, new BeanListHandler<User>(User.class), params2);
	}

	/**
	 * 重置用户密码
	 * @param uid
	 * @throws SQLException
	 */
	public void resetUserPwd(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update `user` set loginpass='' where uid=?";
		qr.update(sql, uid);
	}

	/**
	 * 删除无下单和开店记录的用户
	 * @param uid
	 * @throws SQLException
	 */
	public void delUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from `user` where uid=?";
		qr.update(sql, uid);
	}

	/**
	 * 判断用户是否有开店记录
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public boolean hasShop(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from `shop` where sellerid=?";
		Map<String, Object> shopInfo = qr.query(sql, new MapHandler(), uid);
		if(shopInfo == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断用户是否有下单记录
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public boolean hasOrder(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from `order` where uid=?";
		Map<String, Object> order = qr.query(sql, new MapHandler(), uid);
		if(order == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取顾客交易记录总条数
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int getTrRecordTotalCount(String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM trans_record WHERE loginname like ?  ";
		Map<String,Object> map = qr.query(sql, new MapHandler(), "%"+params[0]+"%");
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取顾客交易记录
	 * @param pBean
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Tr_record> getTrRecordCurrentPageBean(PageBean<Tr_record> pBean,
			String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM trans_record WHERE loginname like ? LIMIT ?,?";
		Object[] params2 = {"%"+params[0]+"%", (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};

		return qr.query(sql, new BeanListHandler<Tr_record>(Tr_record.class), params2);
	}

	/**
	 * 获取顾客交易详情
	 * @param pBean
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Order> getOrderCurrentPageBean(PageBean<Order> pBean,
			String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `order`,`user` WHERE order.uid=user.uid and loginname=? and " + params[1] + " and " + params[2] + " LIMIT ?,?";
		Object[] params2 = {params[0], (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};

		return qr.query(sql, new BeanListHandler<Order>(Order.class), params2);
	}

	/**
	 * 获取顾客交易详情总条数
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int getOrderTotalCount(String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM `order`,`user` WHERE order.uid=user.uid and loginname=? and " + params[1] + " and " + params[2];
		Map<String,Object> map = qr.query(sql, new MapHandler(), params[0]);
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取顾客问题总条数
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int getIssueTotalCount(String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM `issue` WHERE loginname like ? or issue_title like ? or issue_con like ? ";
		Object[] params2 = {"%"+params[0]+"%", "%"+params[0]+"%", "%"+params[0]+"%"};
		Map<String,Object> map = qr.query(sql, new MapHandler(), params2);
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取顾客问题
	 * @param pBean
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Issue> getIssueCurrentPageBean(PageBean<Order> pBean,
			String[] params) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `issue` WHERE loginname like ? or issue_title like ? or issue_con like ? LIMIT ?,?";
		Object[] params2 = {"%"+params[0]+"%", "%"+params[0]+"%", "%"+params[0]+"%", (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};

		return qr.query(sql, new BeanListHandler<Issue>(Issue.class), params2);
	}

	/**
	 * 删除顾客问题
	 * @param loginname
	 * @throws SQLException
	 */
	public void delIssueList(String issueid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from `issue` where issueid=?";
		qr.update(sql, issueid);
	}

	/**
	 * 添加问题反馈记录
	 * @param issue
	 * @throws SQLException 
	 */
	public void AddIssueFeedback(Issue issue) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(issue);
		map.put("issueid", UUID.randomUUID().toString());
		String attr = (String) map.keySet().toArray()[0];
		for (int i = 1; i < 6; i++) {  // 拼接属性字段
			attr = attr + "," + (String) map.keySet().toArray()[i];
		}

		String sql = "insert into issue(" + attr + ") values(?,?,?,?,?,?)";
		qr.update(sql, map.values().toArray());
	}

}
