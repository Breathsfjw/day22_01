package it.cast.jiewen.category.service;

import it.cast.jiewen.category.dao.CategoryDao;
import it.cast.jiewen.category.domain.Category;

import java.util.List;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();

	public List<Category> findAll() {
		return categoryDao.findAll();
	}
}
