package cn.edu.zhk.jsj144.liao.service.ma_customer;

import java.sql.SQLException;
import java.util.List;

import cn.edu.zhk.jsj141.feng.entity.order.Order;
import cn.edu.zhk.jsj141.feng.entity.user.User;
import cn.edu.zhk.jsj141.yin.dao.customer.CustomerManagementDao;
import cn.edu.zhk.jsj144.liao.entity.issue.Issue;
import cn.edu.zhk.jsj144.liao.entity.ma_customer.Tr_record;
import cn.edu.zhk.jsj144.liao.entity.pager.PageBean;

/**
 * 顾客管理业务层
 * @author ele
 *
 */
public class CustomerManagementService {

	CustomerManagementDao customerManagementDao = new CustomerManagementDao();

	/**
	 * 获取某页记录
	 * @param op 1.用户信息列表；2.交易记录列表；3.交易记录详情列表；4.顾客问题反馈列表
	 * @param pBean
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public <T> PageBean<T> getByPage(int op, PageBean<T> pBean, String[] params)
			throws SQLException {
        //查询总条数和当前页的数据
		List<T> records = null;
		int totalCount = 0;
		switch(op) {
		case 1:
			totalCount=customerManagementDao.getUserTotalCount(params);
			records=(List<T>) customerManagementDao.getUserCurrentPageBean((PageBean<User>) pBean, params);
			break;
		case 2:
			totalCount=customerManagementDao.getTrRecordTotalCount(params);
			records=(List<T>) customerManagementDao.getTrRecordCurrentPageBean((PageBean<Tr_record>) pBean, params);
			break;
		case 3:
			totalCount=customerManagementDao.getOrderTotalCount(params);
			records=(List<T>) customerManagementDao.getOrderCurrentPageBean((PageBean<Order>) pBean, params);
			break;
		case 4:
			totalCount=customerManagementDao.getIssueTotalCount(params);
			records=(List<T>) customerManagementDao.getIssueCurrentPageBean((PageBean<Order>) pBean, params);
		}
        
        PageBean<T> pBean2=new PageBean<T>();
        pBean2.setTotalCount(totalCount);
        pBean2.setBean(records);
        pBean2.setCurrentPage(pBean.getCurrentPage());
        pBean2.setPageSize(pBean.getPageSize());
        return pBean2;
    }

	/**
	 * 重置用户密码
	 * @param uid
	 * @throws SQLException
	 */
	public void resetUserPwd(String uid) throws SQLException {
		// TODO Auto-generated method stub
		customerManagementDao.resetUserPwd(uid);
	}

	/**
	 * 删除无下单和开店记录的用户
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public boolean delUser(String uid) throws SQLException {
		// TODO Auto-generated method stub
		boolean hasShop = customerManagementDao.hasShop(uid);
		boolean hasOrder = customerManagementDao.hasOrder(uid);
		boolean allowDel = false;
		if(!hasShop && !hasOrder) {
			customerManagementDao.delUser(uid);
			allowDel = true;
		}
		return allowDel;
	}

	/**
	 * 删除顾客问题记录
	 * @param loginname
	 * @throws SQLException
	 */
	public void delIssueList(String issueid) throws SQLException {
		// TODO Auto-generated method stub
		customerManagementDao.delIssueList(issueid);
	}

	/**
	 * 添加问题反馈记录
	 * @param issue
	 * @throws SQLException 
	 */
	public void AddIssueFeedback(Issue issue) throws SQLException {
		// TODO Auto-generated method stub
		customerManagementDao.AddIssueFeedback(issue);
	}
}
