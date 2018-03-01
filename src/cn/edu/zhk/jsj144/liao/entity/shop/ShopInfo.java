package cn.edu.zhk.jsj144.liao.entity.shop;

public class ShopInfo {
	private String shopid = null; // 店铺id
	private String sellerid = null; // 卖家id
	private String shopName = null; // 店铺名
	private String busi = null; // 主营
	private String descr = null; // 店铺简介
	private String pro_src = null; // 主要货源
	private boolean phy_store = false; // 是否有实体店
	private boolean fac_rep = false; // 是否有工厂或仓库
	private String sellerName = null; // 卖家姓名
	private String telNumber = null; // 电话号码
	private String address = null;  // 发货地址
	private String postcode = null;  // 邮政编码
	
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getBusi() {
		return busi;
	}
	public void setBusi(String busi) {
		this.busi = busi;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getPro_src() {
		return pro_src;
	}
	public void setPro_src(String pro_src) {
		this.pro_src = pro_src;
	}
	public boolean isPhy_store() {
		return phy_store;
	}
	public void setPhy_store(boolean phy_store) {
		this.phy_store = phy_store;
	}
	public boolean isFac_rep() {
		return fac_rep;
	}
	public void setFac_rep(boolean fac_rep) {
		this.fac_rep = fac_rep;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
