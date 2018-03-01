package cn.edu.zhk.jsj144.liao.entity.issue;

public class Issue {
	String issueid; // 问题ID
	String loginname; // 登录名
	String issue_title; // 问题标题
	String issue_con; // 问题内容
	String datetime; // 发布日期
	String email; // 回复邮箱地址
	
	public String getIssueid() {
		return issueid;
	}
	public void setIssueid(String issueid) {
		this.issueid = issueid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getIssue_title() {
		return issue_title;
	}
	public void setIssue_title(String issue_title) {
		this.issue_title = issue_title;
	}
	public String getIssue_con() {
		return issue_con;
	}
	public void setIssue_con(String issue_con) {
		this.issue_con = issue_con;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
