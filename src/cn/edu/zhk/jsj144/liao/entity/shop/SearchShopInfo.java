package cn.edu.zhk.jsj144.liao.entity.shop;

/**
 * 搜索到的店铺
 */
public class SearchShopInfo {
	private String shopid = null; // 店铺ID
	private String shopname = null; // 店铺名
	private String busi = null; // 主营
	private String image1 = null; // 商品1
	private String image2 = null; // 商品2
	
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopName) {
		this.shopname = shopName;
	}
	public String getBusi() {
		return busi;
	}
	public void setBusi(String busi) {
		this.busi = busi;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
}
