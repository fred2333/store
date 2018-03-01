package cn.edu.zhk.jsj144.liao.entity.shop;

public class ShopVerify {
	private String loginname = null;  // 登录名
	private String name = null;  // 真实姓名
	private String idcardNum = null;  // 身份证号码
	private String address = null;  // 常用地址
	private String phone = null;  // 联系电话
	private String bank = null;  // 开户银行
	private String bankcardNum = null;  // 银行卡号
	private String status = null;  // 开店审核状态
	private String reason = null;  // 开店申请拒绝理由
	private boolean notice = false;  // 显示开店审核结果通知
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcardNum() {
		return idcardNum;
	}
	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankcardNum() {
		return bankcardNum;
	}
	public void setBankcardNum(String bankcardNum) {
		this.bankcardNum = bankcardNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isNotice() {
		return notice;
	}
	public void setNotice(boolean notice) {
		this.notice = notice;
	}
	
}
