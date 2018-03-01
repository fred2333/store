package cn.edu.zhk.jsj141.yin.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import cn.edu.zhk.jsj141.yin.dao.shop.ShopDao;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopInfo;

public class ShopDaoTest {
	ShopDao shopdao = new ShopDao();
	//@Test
	public void testGetShopInfo() {
		try {
			System.out.println(shopdao.getShopInfo("123"));
			System.out.println(shopdao.getShopInfo("1"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//@Test
	public void testAddShopInfo() {
		try {
			ShopInfo sh = new ShopInfo();
			sh.setShopid("1");
			sh.setSellerid("qwe");
			shopdao.addShopInfo(sh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testEditShopInfo() {
		try {
			ShopInfo sh = shopdao.getShopInfo("123");
			sh.setSellerName("qweqew");
			sh.setDescr("afafdsafdsaf");
			shopdao.editShopInfo(sh);
			
			sh = shopdao.getShopInfo("123");
			assertEquals("qweqew", sh.getSellerName());
			assertEquals("afafdsafdsaf", sh.getDescr());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
