package fengfei.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fengfei.fir.utils.BASE64;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;
import fengfei.ucm.service.UserService;
import fengfei.ucm.service.impl.UserServiceImpl;

@Controller
public class UserCenter extends Admin {

	UserService userService = new UserServiceImpl();

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		String e = request.getParameter("email");
		String p =  request.getParameter("password");
		String rem =  request.getParameter("remember");
		rem = "1";
		String cpage =  request.getParameter("cpage");
		if (e == null || p == null) {
			flash.put("cpage", cpageUrl(request.querystring));
			System.out.println(flash.get("cpage"));
			throw new JapidResult(new Login().render());
		}
		validation.required(e);
		validation.required(p);

		if (validation.hasErrors()) {
			flash.put("email", e);
			flash.put("password", e);
			redirect(request.url);

		}

		boolean isLogin = verify(e, p, rem);

		if (isLogin) {
			if (cpage == null || "".equals(cpage)) {
				Application.index();
			} else {
				redirect(cpage);
			}

		} else {
			flash.put("error", "Email And Password mismatch.");
			flash.put("email", e);
			redirect(request.url);
			// throw new JapidResult(new Login().render());
		}

	}

	public String cpageUrl(String url) {
		if (url == null || "".equals(url)) {
			return "";
		} else {
			return "?" + url;
		}
	}

	public void logout() {
		session.clear();
		response.removeCookie(COOKIE_EMAIL);
		response.removeCookie(COOKIE_PASSWORD);
		throw new JapidResult(new Login().render());
	}

	public void logon() {
		boolean isLogin;
		Map<String, Object> rs = new HashMap<>();
		try {

			String e =  request.getParameter("email");
			String p =  request.getParameter("password");
			String rem =  request.getParameter("remember");
			isLogin = verify(e, p, rem);
			System.out.println("ssssssssssss=:  " + isLogin);
			rs.put("isLogin", isLogin);
			rs.put("username", e);

		} catch (Exception e) {
			e.printStackTrace();
			rs.put("isLogin", false);

		}
		renderJSON(rs);
	}

	public void logoff() {
		try {
			session.clear();
			response.removeCookie(COOKIE_EMAIL);
			response.removeCookie(COOKIE_PASSWORD);
			renderText(true);
		} catch (Exception e) {
			renderText(false);
			e.printStackTrace();
		}
	}

	private boolean verify(String email, String passoword, String remember) {
		try {
			String e = email.trim();
			String p = passoword.trim();

			// if (e == null) {
			// Http.Cookie cookie = request.cookies.get(COOKIE_EMAIL);
			// e = cookie == null ? e : cookie.value;
			// }
			// if (p == null) {
			// Http.Cookie cookie = request.cookies.get(COOKIE_PASSWORD);
			// p = cookie == null ? p : cookie.value;
			// }
			String md5_pwd = BASE64.encrypt(p);
			boolean isLogin = session.get(SESSION_LOGIN_KEY) != null;
			if (!isLogin) {
				UserPwd user = userService.getUserPwd(email, md5_pwd);
				if (user != null) {
					session.put(SESSION_LOGIN_KEY, true);
					session.put(SESSION_USER_ID_KEY, user.getIdUser());
					session.put(SESSION_USER_NAME_KEY, user.getUserName());
					// if (remember != null && "1".equals(remember)) {
					// response.setCookie(COOKIE_EMAIL, e, "30d");
					// response.setCookie(COOKIE_PASSWORD, p, "30d");
					// }
					User info = userService.getUser(user.getIdUser());
					if (info.getNiceName() != null
							&& !"".equals(info.getNiceName())) {
						session.put(SESSION_USER_NAME_KEY, info.getNiceName());
					}

					return true;
				}
			} else {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void signup() {
		throw new JapidResult(new Signup().render(new UserPwd()));
	}

	public void register() {
		System.out.println(params.allSimple());
		String username =  request.getParameter("username");
		String email =  request.getParameter("email");
		String password =  request.getParameter("password");
		String confirm_password =  request.getParameter("confirm_password");
		String cpage = request.getParameter("cpage");
		if (!password.equals(confirm_password)) {
			flash.put("error", "Twice password mismatched.");
			throw new JapidResult(new Signup().render(new UserPwd(email,
					username, null)));
		}
		if (password.getBytes().length > 20) {
			flash.put("error", "password length<=20 char(or 6 chinese).");
			throw new JapidResult(new Signup().render(new UserPwd(email,
					username, null)));
		}

		try {
			String md5_pwd = BASE64.encrypt(password);
			UserPwd up = new UserPwd(email, username, md5_pwd);
			int updated = userService.addUserPwd(up);
			if (updated == -3) {
				flash.put("error", "Username and Email already existed.");
			} else if (updated == -2) {
				flash.put("error", "Username already existed.");
			} else if (updated == -1) {
				flash.put("error", "Email already existed.");
			} else if (updated > 0) {
				session.put(SESSION_LOGIN_KEY, true);
				session.put(SESSION_USER_ID_KEY, updated);
				session.put(SESSION_USER_NAME_KEY, up.getUserName());
				if (cpage == null || "".equals(cpage)) {
					redirect("/");
				} else {
					redirect(cpage);
				}
			} else {
				flash.put("error", "Signup error.");

			}
			throw new JapidResult(new Signup().render(new UserPwd(email,
					username, null)));
		} catch (Exception e) {
			e.printStackTrace();
			flash.put("error", "Signup error.");
			throw new JapidResult(new Signup().render(new UserPwd(email,
					username, null)));
		}

	}

}
