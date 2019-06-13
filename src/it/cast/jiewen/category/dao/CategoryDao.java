package it.cast.jiewen.category.dao;

import it.cast.jiewen.category.domain.Category;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	QueryRunner qr = new TxQueryRunner();

	public List<Category> findAll() {
		String sql = "select * from category";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	// public List<Category> findByid() {
	// String sql = "select * from category";
	// try {
	// return qr.query(sql, new BeanListHandler<Category>(Category.class));
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// throw new RuntimeException(e);
	// }
	// }
}
