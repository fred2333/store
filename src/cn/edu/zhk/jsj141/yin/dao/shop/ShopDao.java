package cn.edu.zhk.jsj141.yin.dao.shop;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.edu.zhk.jsj141.yin.util.BeanMapUtil;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopInfo;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopVerify;

//店铺信息Dao层
public class ShopDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 获取店铺信息
	 * 
	 * @param loginname
	 * @return
	 * @throws SQLException
	 */
	public ShopInfo getShopInfo(String loginname) throws SQLException {
		String sql = "select * from shop where sellerid = ?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), loginname);
		ShopInfo shopInfo = null;
		if (!mapList.isEmpty()) {
			shopInfo = new ShopInfo();
			BeanMapUtil.mapToBean(mapList.get(0), shopInfo);
		}
		return shopInfo;
	}


	/**
	 * 通过shopid获取店铺信息
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public ShopInfo getShopInfo2(String shopid) throws SQLException {
		String sql = "select * from shop where shopid like ?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), "%"+shopid+"%");
		ShopInfo shopInfo = null;
		if (!mapList.isEmpty()) {
			shopInfo = new ShopInfo();
			BeanMapUtil.mapToBean(mapList.get(0), shopInfo);
		}
		return shopInfo;
	}
	
	/**
	 * 通过shopid获取shopname
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public String getShopName(String shopid) throws SQLException {
		String sql = "select * from shop where shopid like ?";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), "%"+shopid+"%");
		ShopInfo shopInfo = null;
		if (!mapList.isEmpty()) {
			shopInfo = new ShopInfo();
			BeanMapUtil.mapToBean(mapList.get(0), shopInfo);
		}
		return shopInfo.getShopName();
	}
	
	
	
	/**
	 * 添加店铺信息
	 * 
	 * @param shopInfo
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void addShopInfo(ShopInfo shopInfo) throws SQLException {
		shopInfo.setShopid(UUID.randomUUID().toString());
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(shopInfo);
		
		String attr = (String) map.keySet().toArray()[0];
		for (int i = 1; i < 12; i++) {  // 拼接属性字段
			attr = attr + "," + (String) map.keySet().toArray()[i];
		}
		
		String sql = "insert into shop(" + attr + ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, map.values().toArray());
	}

	/**
	 * 修改店铺信息
	 * 
	 * @param shopInfo
	 * @throws SQLException 
	 */
	public void editShopInfo(ShopInfo shopInfo) throws SQLException {
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(shopInfo);
		
		String attr = "";
		for (int i = 0; i < 12; i++) {  // 拼接属性字段
			attr = attr + (String) map.keySet().toArray()[i] + "=?, ";
		}
		attr = attr.substring(0, attr.length()-2); //去除最后的逗号
		
		String sql = "update shop set " + attr + "where shopid = '" + shopInfo.getShopid() + "'";
		qr.update(sql, map.values().toArray());
	}

	/**
	 * 添加开店申请信息
	 * @param shopVerify
	 * @throws SQLException
	 */
	public void setUpShop(ShopVerify shopVerify) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(shopVerify);
		String attr = (String) map.keySet().toArray()[0];
		for (int i = 1; i < 10; i++) {  // 拼接属性字段
			attr = attr + "," + (String) map.keySet().toArray()[i];
		}
		
		String sql = "insert into shopVerify(" + attr + ") values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, map.values().toArray());
	}

	/**
	 * 检查开店申请状态
	 * @param loginname
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> CheckShopApplicationStatus(String loginname) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select status, reason from shopVerify where loginname = ?";
		return qr.query(sql, new MapHandler(), loginname);
	}
}
