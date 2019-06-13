package it.cast.jiewen.user.dao;

import it.cast.jiewen.user.domain.User;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	public User findByname(String username) {
		String sql = "select * from tb_user where username=?";

		try {
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public User findByemail(String email) {
		String sql = "select * from tb_user where email=?";

		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public void addUser(User user) {
		String sql = "insert into tb_user values(?,?,?,?,?,?)";
		Object[] prams = { user.getUid(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getCode(),
				user.isState() };
		try {
			qr.update(sql, prams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public User findBycode(String code) {
		String sql = "select * from tb_user where code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public void updateState(String uid, boolean b) {
		// TODO Auto-generated method stub
		String sql = "update tb_user set state=? where uid=?";
		try {
			qr.update(sql, b, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
}
