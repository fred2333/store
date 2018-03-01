package cn.edu.zhk.jsj141.yin.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import cn.edu.zhk.jsj141.yin.dao.category.product.CategoryDao;
import cn.edu.zhk.jsj144.liao.entity.category.product.Category;

public class CategoryTest {
	private CategoryDao categoryDao = new CategoryDao();
	
	@Test
	public void testFindAll() {
		try {
			List<Category> list = categoryDao.findAll();
			for(Category c:list) {
				System.out.println(c.getCid()+" "+c.getCname()+" "+c.getDesc());
				for(Category c1:c.getChildren()) {
					System.out.println("  " +c1.getCid()+" "+c1.getCname()+" "+c1.getDesc());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindByParent() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindParents() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindChildrenCountByParent() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
