package cn.edu.zhk.jsj144.liao.entity.product;

import cn.edu.zhk.jsj144.liao.entity.category.product.Category;

public class Product {
	private String productid = null;   // 商品ID
	private String shopid = null;   // 店铺ID
	private String productName = null;   // 商品名称
	private String image_b = null;   // 小图片
	private String image_w = null;   // 大图片
	private float price = 0;   // 定价
	private float currPrice = 0;   // 促销价
	private String brand = null;   // 品牌
	private int productNum = 0;   // 库存数量
	private int salesNum = 0;  // 销量
	private String proDate = null;   // 生产日期
	private String purDate = null;   // 进货日期
	private String cid = null;   // 商品种类ID
	private String productDesc = null;   // 商品说明
	private Category category;//所属分类
	
	public String getProductid() {
		return productid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public String getImage_w() {
		return image_w;
	}
	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(float currPrice) {
		this.currPrice = currPrice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public int getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
	public String getProDate() {
		return proDate;
	}
	public void setProDate(String proDate) {
		this.proDate = proDate;
	}
	public String getPurDate() {
		return purDate;
	}
	public void setPurDate(String purDate) {
		this.purDate = purDate;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", shopid=" + shopid
				+ ", productName=" + productName + ", image_b=" + image_b
				+ ", image_w=" + image_w + ", price=" + price + ", currPrice="
				+ currPrice + ", brand=" + brand + ", productNum=" + productNum
				+ ", proDate=" + proDate + ", purDate=" + purDate + ", cid="
				+ cid + ", productDesc=" + productDesc + "]";
	}
	
}
