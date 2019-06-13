package it.cast.jiewen.user.service;

import it.cast.jiewen.user.dao.UserDao;
import it.cast.jiewen.user.domain.User;

public class UserService {
	private UserDao userdao = new UserDao();

	public void regist(User form) throws UserException {
		User user = userdao.findByname(form.getUsername());
		if (user != null)
			throw new UserException("�û����ѱ�ע�ᣡ");
		user = userdao.findByemail(form.getEmail());
		if (user != null)
			throw new UserException("email�ѱ�ע�ᣡ");
		userdao.addUser(form);
	}

	public void action(String code) throws UserException {
		User user = userdao.findBycode(code);
		if (user == null)
			throw new UserException("��������Ч��");
		/*
		 * 3. У���û���״̬�Ƿ�Ϊδ����״̬������Ѽ��˵���Ƕ��μ���׳��쳣
		 */
		if (user.isState())
			throw new UserException("���Ѿ�������ˣ���Ҫ�ټ����ˣ�������������");

		/*
		 * 4. �޸��û���״̬
		 */
		userdao.updateState(user.getUid(), true);
	}

	public User login(User form) throws UserException {
		User user = userdao.findByname(form.getUsername());
		if (user == null)
			throw new UserException("�㻹δע����ע�ᣬ��ע��");
		if (!form.getPassword().equals(user.getPassword()))
			throw new UserException("�����������������");
		if (!user.isState())
			throw new UserException("����δ����뼤��");
		return user;
	}
}
