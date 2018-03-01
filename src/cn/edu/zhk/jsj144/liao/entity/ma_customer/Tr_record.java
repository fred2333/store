package cn.edu.zhk.jsj144.liao.entity.ma_customer;

public class Tr_record {
	String loginname = null; // 登录名
	String orderNum = null; // 下单次数
	String ordertime = null; // 最近下单时间
	String price = null; // 最近90天消费金额
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
