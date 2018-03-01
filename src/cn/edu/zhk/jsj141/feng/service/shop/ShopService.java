package cn.edu.zhk.jsj141.feng.service.shop;

import java.sql.SQLException;

import cn.edu.zhk.jsj141.yin.dao.shop.ShopDao;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopInfo;

/**
 * 店铺信息业务层
 * @author ele
 *
 */
public class ShopService {
	private ShopDao shopDao = new ShopDao();
	
	/**
	 * 获取店铺信息
	 * @param uid
	 * @return
	 */
	public ShopInfo getShopInfo(String uid) {
		ShopInfo shopInfo = null;
		try {
			shopInfo = shopDao.getShopInfo(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopInfo;
	}
	
	/**
	 * 通过shopid获取店铺信息
	 * @param uid
	 * @return
	 */
	public ShopInfo getShopInfo2(String shopid) {
		ShopInfo shopInfo = null;
		try {
			shopInfo = shopDao.getShopInfo2(shopid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopInfo;
	}
	public String getShopname(String shopid){
		String shopname=null;
		try {
			shopname= shopDao.getShopName(shopid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shopname;
	}
}
