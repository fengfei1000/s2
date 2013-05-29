package fengfei.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Secure {
	public final static String SESSION_LOGIN_KEY = "islogin";
	public final static String SESSION_USER_ID_KEY = "USER_ID";
	public final static String COOKIE_EMAIL = "spruce_email";
	public final static String COOKIE_USER = "spruce_USER";
	public final static String COOKIE_PASSWORD = "spruce_pwd";

	public void checkAuthentification(HttpServletRequest request) {
		//
		String cpage = request.getRequestURL().toString();
		HttpSession session = request.getSession();
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
		if (session.getAttribute(SESSION_LOGIN_KEY) == null) {
			// Http.Cookie cookie = cookies.get(COOKIE_EMAIL);
			// Http.Cookie cookie = cookies.get(COOKIE_USER);
			// loginIndex();
			System.out.println("-----------------login1-----------------");
			// throw new JapidResult(new Login().render());
			String url = (cpage == null || "".equals(cpage)) ? "" : "?cpage="
					+ cpage;

			// redirect("/login" + url);

		}
	}

}
