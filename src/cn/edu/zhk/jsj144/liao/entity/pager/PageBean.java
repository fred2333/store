package cn.edu.zhk.jsj144.liao.entity.pager;

import java.util.List;

public class PageBean<T> {
	private List<T> bean;    // 存放实体类集合
    
    private int currentPage;    // 当前页
    private int pageSize;        // 每页显示的条数
    private int totalCount;    // 总条数
    @SuppressWarnings("unused")
	private int totalPage;   // 总页数
    private String url; // 链接地址
    
    public List<T> getBean() {
        return bean ;
    }
    
    public void setBean(List<T> bean) {
        this.bean = bean ;
    }
    
    public int getCurrentPage() {
        return currentPage ;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage ;
    }
    
    public int getPageSize() {
        return pageSize ;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize ;
    }
    
    public int getTotalPage() {
        return (totalCount + pageSize - 1) / pageSize ;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount ;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
