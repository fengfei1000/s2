package fengfei.controllers;

import play.mvc.Before;
import cn.bran.play.JapidController;

public class Secure extends JapidController {
	public final static String SESSION_LOGIN_KEY = "islogin";
	public final static String SESSION_USER_ID_KEY = "USER_ID";
	public final static String COOKIE_EMAIL = "spruce_email";
	public final static String COOKIE_USER = "spruce_USER";
	public final static String COOKIE_PASSWORD = "spruce_pwd";

	@Before(unless = { "UserCenter.login", "UserCenter.logout",
			"UserCenter.logon", "logoff", "UserCenter.signup",
			"UserCenter.register" })
	static void checkAuthentification() {
		//
		String cpage = request.url;

		// Map<String, Cookie> cookies = request.cookies;
		// for (Entry<String, Cookie> ck : cookies.entrySet()) {
		// System.out.printf("cookie key=%s, value=%s , age=%s \n",
		// ck.getKey(), ck.getValue().value, ck.getValue().maxAge);
		//
		// }
		//
		// System.out.println("session: " + session.all());
		// System.out.println("session: " + session.getId());
		// System.out.println();
		if (session.get(SESSION_LOGIN_KEY) == null) {
			// Http.Cookie cookie = cookies.get(COOKIE_EMAIL);
			// Http.Cookie cookie = cookies.get(COOKIE_USER);
			// loginIndex();
			System.out.println("-----------------login1-----------------");
			// throw new JapidResult(new Login().render());
			String url = (cpage == null || "".equals(cpage)) ? "" : "?cpage="
					+ cpage;

			redirect("/login" + url);

		}
	}

}
