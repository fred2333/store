package cn.edu.zhk.jsj144.liao.service.admin;

import java.sql.SQLException;
import java.util.Map;

import cn.edu.zhk.jsj141.yin.dao.admin.AdminDao;
import cn.edu.zhk.jsj144.liao.entity.admin.Admin;

/**
 * 管理员帐号的业务层
 * @author ele
 *
 */
public class AdminService {

	private AdminDao adminDao = new AdminDao();
	
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 * @throws ErrorUsernameException
	 * @throws ErrorPasswordException
	 * @throws SQLException
	 */
	public String login(Admin admin) throws ErrorUsernameException, ErrorPasswordException, SQLException {
		// TODO Auto-generated method stub
		Map<String,Object> realAdmin = adminDao.getAdmin(admin.getAdminname());
		if(realAdmin == null) {
			throw new ErrorUsernameException();
		} else {
			if(realAdmin.get("adminpwd").equals(admin.getAdminpwd())) {
				return admin.getAdminname();
			} else {
				throw new ErrorPasswordException();
			}
		}
	}

	/**
	 * 管理员修改密码
	 * @param admin
	 * @throws SQLException
	 */
	public void changePassword(Admin admin) throws SQLException {
		// TODO Auto-generated method stub
		adminDao.changePassword(admin);
	}

}
