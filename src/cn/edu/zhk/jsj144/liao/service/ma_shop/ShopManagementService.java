package cn.edu.zhk.jsj144.liao.service.ma_shop;

import java.sql.SQLException;
import java.util.List;

import cn.edu.zhk.jsj141.yin.dao.shop.ShopManagementDao;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;
import cn.edu.zhk.jsj144.liao.entity.shop.ShopVerify;

public class ShopManagementService {
	private ShopManagementDao shopManagementDao = new ShopManagementDao();

	public PageBean<ShopVerify> getByPage(PageBean<ShopVerify> pBean, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		//查询总条数
        int totalCount=shopManagementDao.getTotalCount(keyword);
        
        //查询当前页的数据
        List<ShopVerify> records=shopManagementDao.getCurrentPageBean(pBean, keyword);
        
        PageBean<ShopVerify> pBean2=new PageBean<ShopVerify>();
        pBean2.setTotalCount(totalCount);
        pBean2.setBean(records);
        pBean2.setCurrentPage(pBean.getCurrentPage());
        pBean2.setPageSize(pBean.getPageSize());
        return pBean2;
	}

	public void changeVerifyStatus(String loginname, String status, String reason) throws SQLException {
		// TODO Auto-generated method stub
		shopManagementDao.changeVerifyStatus(loginname, status, reason);
	}

	public void delVerifyInfo(String loginname) throws SQLException {
		// TODO Auto-generated method stub
		shopManagementDao.delVerifyInfo(loginname);
	}

}
