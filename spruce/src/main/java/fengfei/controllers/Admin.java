package fengfei.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fengfei.ucm.entity.profile.User;

public class Admin {

	public final static String SESSION_LOGIN_KEY = "islogin";
	public final static String SESSION_USER_KEY = "USER";
	public final static String SESSION_USER_AUTH_KEY = "USER_AUTH";
	public final static String SESSION_USER_NAME_KEY = "USER_DISPLAY_NAME";
	public final static String COOKIE_EMAIL = "spruce_email";
	public final static String COOKIE_PASSWORD = "spruce_pwd";

	protected static Integer currentUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_USER_KEY);
		return user == null || user.idUser == null ? null : user.idUser;
	}
}
