package cn.edu.zhk.jsj141.feng.service.product;

import java.sql.SQLException;

import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj141.yin.dao.product.ProductDao;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj144.liao.entity.shop.SearchShopInfo;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	/**
	 * 返回当前分类下商品个数
	 * @param cid
	 * @return
	 */
	public int findProductCountByCategory(String cid) {
		try {
			return productDao.findproductCountByCategory(cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	/**
	 * 按分类查
	 * @param cid
	 * @param pc
	 * @return
	 */
	public PageBean2<Product> findByCategory(String cid, int pc) {
		try {
			return productDao.findByCategory(cid, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加载商品
	 * @param productid
	 * @return
	 */
	public Product load(String productid) {
		try {
			return productDao.findByproductid(productid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按名查
	 * @param productname
	 * @param pc
	 * @return
	 */
	public PageBean2<Product> findByBname(String productname, int pc) {
		try {
			return productDao.findByBname(productname, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按商铺id查
	 * @param shopid
	 * @param pc
	 * @return
	 */
	public PageBean2<Product> findByShop(String shopid, int pc) {
		try {
			return productDao.findByShop(shopid, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 按排名查
	 * @param productname
	 * @param pc
	 * @return
	 */
	public PageBean2<Product> findByRank(int pc) {
		try {
			return productDao.findByRank(pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按店铺名查
	 * @param shopid
	 * @param pc
	 * @return
	 */
	public PageBean2<SearchShopInfo> findShop(String shopname, int pc) {
		try {
			return productDao.findShop(shopname, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public String getProImgPath(String shopid, String imgNum) {
		// TODO Auto-generated method stub
		try {
			return productDao.getProImgPath(shopid, imgNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
