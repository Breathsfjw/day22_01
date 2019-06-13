package it.cast.jiewen.user.servlet;

import it.cast.jiewen.cart.domain.Cart;
import it.cast.jiewen.user.domain.User;
import it.cast.jiewen.user.service.UserException;
import it.cast.jiewen.user.service.UserService;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	private UserService userservice = new UserService();

	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		return "r:/index.jsp";

	}

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userservice.login(form);
			request.getSession().setAttribute("session_user", user);
			request.getSession().setAttribute("cart", new Cart());
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}

	}

	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		try {
			userservice.action(code);
			request.setAttribute("msg", "恭喜你，激活成功，请登录");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());

		}
		return "f:/jsps/msg.jsp";
	}

	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
		Map<String, String> map = new HashMap<String, String>();
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			map.put("username", "用户名不能为空!");
		} else if (username.length() < 3 || username.length() > 20) {
			map.put("username", "用户名长度必须在3~20之间");
		}
		String password = form.getPassword();
		if (password == null || password.trim().isEmpty()) {
			map.put("password", "密码不能为空!");
		} else if (password.length() < 3 || password.length() > 20) {
			map.put("password", "密码长度必须在3~20之间");
		}
		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			map.put("email", "email不能为空!");
		} else if (email.length() < 3 || email.length() > 20) {
			map.put("email", "email长度必须在3~20之间");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			map.put("email", "Email格式错误！");
		}
		if (map.size() > 0) {
			request.setAttribute("map", map);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		try {
			userservice.regist(form);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader()
				.getResourceAsStream("email_template.properties"));
		String host = prop.getProperty("host");
		String uname = prop.getProperty("uname");
		String pwd = prop.getProperty("pwd");// 获取密码
		String from = prop.getProperty("from");// 获取发件人
		String to = form.getEmail();// 获取收件人
		String subject = prop.getProperty("subject");// 获取主题
		String content = prop.getProperty("content");// 获取邮件内容
		content = MessageFormat.format(content, form.getCode());// 替换{0}
		Session session = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from, to, subject, content);

		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
		}
		request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活");
		return "f:/jsps/msg.jsp";
	}
}
