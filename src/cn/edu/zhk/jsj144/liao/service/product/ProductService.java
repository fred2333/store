package cn.edu.zhk.jsj144.liao.service.product;

import java.sql.SQLException;
import java.util.List;

import cn.edu.zhk.jsj141.yin.dao.product.ProductDao;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.entity.product.Product;

/**
 * 商品管理业务层
 * @author ele
 *
 */
public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	/**
	 * 获取当前页商品列表
	 * @param op 商品类别cid或搜索关键字
	 * @param pBean
	 * @param shopid
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Product> getByPage(int op, PageBean<Product> pBean, String shopid, String param)
			throws SQLException {
        
        //查询总条数
        int totalCount=productDao.getTotalCount(op, shopid, param);
        
        //查询当前页的数据
        List<Product> products=productDao.getCurrentPageBean(op, pBean, shopid, param);
        
        PageBean< Product> pBean2=new PageBean<Product>();
        pBean2.setTotalCount(totalCount);
        pBean2.setBean(products);
        pBean2.setCurrentPage(pBean.getCurrentPage());
        pBean2.setPageSize(pBean.getPageSize());
        return pBean2;
    }

	/**
	 * 获取商品详细信息
	 * @param productid
	 * @return
	 */
	public Product getProductByID(String productid) {
		// TODO Auto-generated method stub
		Product product = null;
		try {
			product = productDao.getProductByID(productid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	/**
	 * 添加商品
	 * @param product
	 * @throws SQLException
	 */
	public void addProduct(Product product) throws SQLException {
		productDao.addProduct(product);
	}

	/**
	 * 修改商品
	 * @param product
	 */
	public void updateProduct(Product product) {
		// 补齐属性
		Product pro = getProductByID(product.getProductid());
		product.setShopid(pro.getShopid());
		product.setImage_b(pro.getImage_b());
		product.setImage_w(pro.getImage_w());
		product.setSalesNum(pro.getSalesNum());
		
		try {
			productDao.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除商品
	 * @param productid
	 * @throws SQLException
	 */
	public void delProduct(String productid) throws SQLException {
		// TODO Auto-generated method stub
		productDao.delProduct(productid);
	}
	
}
