package cn.edu.zhk.jsj141.yin.dao.shop;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.edu.zhk.jsj141.yin.util.JDBCUtils;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopVerify;

/**
 * 店铺管理DAO层
 */
public class ShopManagementDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 获取开店申请记录总数
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public int getTotalCount(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM `shopVerify` WHERE loginname like ? or name like ? ";
		Object[] params = {"%"+keyword+"%", "%"+keyword+"%"};
		Map<String,Object> map = qr.query(sql, new MapHandler(), params);
		return Integer.parseInt(String.valueOf(map.get("count(*)")));
	}

	/**
	 * 获取当前页的开店申请列表
	 * @param pBean
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<ShopVerify> getCurrentPageBean(PageBean<ShopVerify> pBean, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM `shopVerify` WHERE loginname like ? or name like ? LIMIT ?,?";
		Object[] params = {"%"+keyword+"%", "%"+keyword+"%", (pBean.getCurrentPage()-1)*pBean.getPageSize(), pBean.getPageSize()};

		return qr.query(sql, new BeanListHandler<ShopVerify>(ShopVerify.class), params);
	}

	/**
	 * 改变开店申请状态
	 * @param loginname
	 * @param status 空串：未完成审核    "0"：申请被拒绝      "1"：通过开店申请
	 * @param reason
	 * @throws SQLException
	 */
	public void changeVerifyStatus(String loginname, String status, String reason) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update `shopVerify` set status=?,reason=? where loginname=?";
		qr.update(sql, status, reason, loginname);
	}

	/**
	 * 删除开店申请记录
	 * @param loginname
	 * @throws SQLException
	 */
	public void delVerifyInfo(String loginname) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from `shopVerify` where loginname=?";
		qr.update(sql, loginname);
	}

}
