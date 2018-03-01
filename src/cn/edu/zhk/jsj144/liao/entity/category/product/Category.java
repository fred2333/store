package cn.edu.zhk.jsj144.liao.entity.category.product;

import java.util.List;

/**
 * 类别实体
 * @author ele
 *
 */
public class Category {
	private String cid = null;   // 主键
	private String cname = null;   // 类别名称
	private Category parent = null;   // 父类别
	private String desc = null;   // 类别描述
	private List<Category> children = null;   // 子类别

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}
}
