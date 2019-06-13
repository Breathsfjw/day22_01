package it.cast.jiewen.user.service;

import it.cast.jiewen.user.dao.UserDao;
import it.cast.jiewen.user.domain.User;

public class UserService {
	private UserDao userdao = new UserDao();

	public void regist(User form) throws UserException {
		User user = userdao.findByname(form.getUsername());
		if (user != null)
			throw new UserException("用户名已被注册！");
		user = userdao.findByemail(form.getEmail());
		if (user != null)
			throw new UserException("email已被注册！");
		userdao.addUser(form);
	}

	public void action(String code) throws UserException {
		User user = userdao.findBycode(code);
		if (user == null)
			throw new UserException("激活码无效！");
		/*
		 * 3. 校验用户的状态是否为未激活状态，如果已激活，说明是二次激活，抛出异常
		 */
		if (user.isState())
			throw new UserException("您已经激活过了，不要再激活了，除非你想死！");

		/*
		 * 4. 修改用户的状态
		 */
		userdao.updateState(user.getUid(), true);
	}

	public User login(User form) throws UserException {
		User user = userdao.findByname(form.getUsername());
		if (user == null)
			throw new UserException("你还未注册请注册，请注册");
		if (!form.getPassword().equals(user.getPassword()))
			throw new UserException("密码错误，请重新输入");
		if (!user.isState())
			throw new UserException("您还未激活，请激活");
		return user;
	}
}
