package cn.edu.zhk.jsj141.yin.dao.product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.zhk.jsj141.yin.util.BeanMapUtil;
import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj144.liao.entity.shop.SearchShopInfo;
import cn.edu.zhk.jsj141.feng.entity.pager.PageBean2;
import cn.edu.zhk.jsj141.feng.entity.pager.Expression;
import cn.edu.zhk.jsj141.feng.entity.pager.PageConstants;
import cn.edu.zhk.jsj141.feng.commons.CommonUtils;
import cn.edu.zhk.jsj144.liao.entity.category.product.Category;

public class ProductDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 获取商品总数
	 * @param op 商品类别cid或搜索关键字
	 * @param shopid
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public int getTotalCount(int op, String shopid, String param) throws SQLException {
		// TODO Auto-generated method stub
		String sql = null;
		Object[] params = null;
		if(op == 0) {
			sql="SELECT count(*) FROM product WHERE shopid like ? and cid like ?";
			Object[] params1 = {shopid, param};
			params = params1;
		} else {
			sql="SELECT count(*) FROM product WHERE shopid like ? and ( productName like ? or brand like ? )";
			Object[] params1 = {shopid, "%"+param+"%", "%"+param+"%"};
			params = params1;
		}
		Map<String,Object> map = qr.query(sql, new MapHandler(), params);
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取某店铺当前页的指定商品
	 * @param op 商品类别cid或搜索关键字
	 * @param pBean
	 * @param shopid
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getCurrentPageBean(int op, PageBean<Product> pBean, String shopid, String param) throws SQLException {
		// TODO Auto-generated method stub
		String sql = null;
		Object[] params = null;
		if(op == 0) {
			sql="SELECT * FROM product WHERE shopid like ? and cid like ? ORDER BY orderBy DESC LIMIT ?,?";
			Object[] params1 = {shopid, param, (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};
			params = params1;
		} else {
			sql="SELECT * FROM product WHERE shopid like ? and ( productName like ? or brand like ? ) ORDER BY orderBy DESC LIMIT ?,?";
			Object[] params1 = {shopid, "%"+param+"%", "%"+param+"%", (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};
			params = params1;
		}
		
        return qr.query(sql, new BeanListHandler<Product>(Product.class), params);
	}

	/**
	 * 根据商品ID获取商品
	 * @param productid
	 * @return
	 * @throws SQLException
	 */
	public Product getProductByID(String productid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE productid = ?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), productid);
		Product product = new Product();
		BeanMapUtil.mapToBean(map, product);
		return product;
	}

	/**
	 * 添加商品
	 * @param product
	 * @throws SQLException 
	 */
	public void addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(product);
		map.remove("category");
		
		String attr = (String) map.keySet().toArray()[0];
		for (int i = 1; i < 14; i++) {  // 拼接属性字段
			attr = attr + "," + (String) map.keySet().toArray()[i];
		}
		
		String sql = "insert into product(" + attr + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, map.values().toArray());
	}

	/**
	 * 修改商品
	 * @param product
	 * @throws SQLException 
	 */
	public void updateProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String, Object>) BeanMapUtil.beanToMap(product);
		map.remove("category");
		
		String attr = "";
		for (int i = 0; i < 14; i++) {  // 拼接属性字段
			attr = attr + (String) map.keySet().toArray()[i] + "=?, ";
		}
		attr = attr.substring(0, attr.length()-2); //去除最后的逗号
		
		String sql = "update product set " + attr + "where productid = '" + product.getProductid() + "'";
		qr.update(sql, map.values().toArray());
	}

	public void delProduct(String productid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from product where productid=?";
		qr.update(sql, productid);
	}
	
	//分界线
	
	private PageBean2<Product> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		/*
		 * 1. 得到ps
		 * 2. 得到tr
		 * 3. 得到beanList
		 * 4. 创建PageBean，返回
		 */
		/*
		 * 1. 得到ps
		 */
		int ps = PageConstants.PRO_PAGE_SIZE;//每页记录数
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

		/*
		 * 3. 总记录数 
		 */
		String sql = "select count(*) from product" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();//得到了总记录数
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		sql = "select * from product" + whereSql + " order by orderBy limit ?,?";
		params.add((pc-1) * ps);//当前页首行记录的下标
		params.add(ps);//一共查询几行，就是每页记录数
		
		List<Product> beanList = qr.query(sql, new BeanListHandler<Product>(Product.class), 
				params.toArray());
		
		/*
		 * 5. 创建PageBean，设置参数
		 */
		PageBean2<Product> pb = new PageBean2<Product>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
	
	/**
	 * 按名模糊查询
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean2<Product> findByBname(String productname, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("productname", "like", "%" + productname + "%"));
		return findByCriteria(exprList, pc);
	}
	
	
	/**
	 * 按商铺id查询
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean2<Product> findByShop(String shopid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("shopid", "=", shopid));
		return findByCriteria(exprList, pc);
	}
	
	/**
	 *  查找特定商品的shopid！！
	 * @param productid
	 * @return
	 * @throws SQLException
	 */
	public String findShopid(String productid) throws SQLException{
		String sql = "select shopid from product where productid = ?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), productid);
		return (String)map.get("shopid");
	}
	
	
	
	/**
	 * 按productid查询
	 * @param productid
	 * @return
	 * @throws SQLException
	 */
	public Product findByproductid(String productid) throws SQLException {
		String sql = "SELECT * FROM product b, category c WHERE b.cid=c.cid AND b.productid=?";
		// 一行记录中，包含了很多的product的属性，还有一个cid属性
		Map<String,Object> map = qr.query(sql, new MapHandler(), productid);
		// 把Map中除了cid以外的其他属性映射到product对象中
		Product product = CommonUtils.toBean(map, Product.class);
		// 把Map中cid属性映射到Category中，即这个Category只有cid
		Category category = CommonUtils.toBean(map, Category.class);
		// 两者建立关系
		product.setCategory(category);
		
		// 把pid获取出来，创建一个Category parnet，把pid赋给它，然后再把parent赋给category
		if(map.get("pid") != null) {
			Category parent = new Category();
			parent.setCid((String)map.get("pid"));
			category.setParent(parent);
		}
		return product;
	}
	
	/**
	 * 查询指定分类下商品的个数
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	public int findproductCountByCategory(String cid) throws SQLException {
		String sql = "select count(*) from product where cid=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), cid);
		return cnt == null ? 0 : cnt.intValue();
	}
	
	/**
	 * 按分类查询
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public PageBean2<Product> findByCategory(String cid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("cid", "=", cid));
		return findByCriteria(exprList, pc);
	}
	/**
	 * 按销量查询
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public PageBean2<Product> findByRank(int pc) throws SQLException {
		String sql="select * from product order by salesNum desc limit 0,8";
		List<Product> beanList=qr.query(sql, new BeanListHandler<Product>(Product.class));
		int ps = 6;
		int tr = 6;
		PageBean2<Product> pb = new PageBean2<Product>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	/**
	 * 按店铺名查询
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public PageBean2<SearchShopInfo> findShop(String shopname, int pc) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select shopid, shopname, busi from shop where shopname like ?";
		List<SearchShopInfo> beanList=qr.query(sql, new BeanListHandler<SearchShopInfo>(SearchShopInfo.class), "%"+shopname+"%");
		int ps = 6;
		int tr = 6;
		PageBean2<SearchShopInfo> pb = new PageBean2<SearchShopInfo>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	public String getProImgPath(String shopid, String imgNum) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select image_b from product WHERE shopid=? order by salesNum desc limit " + imgNum + ",1";
		Map<String,Object> map = qr.query(sql, new MapHandler(), shopid);
		return (String) map.get("image_b");
	}
}
