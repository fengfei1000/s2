package fengfei.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Admin {

    public final static String SESSION_LOGIN_KEY = "islogin";
    public final static String SESSION_USER_ID_KEY = "USER_ID";
    public final static String SESSION_USER_NAME_KEY = "USER_NAME";
    public final static String COOKIE_EMAIL = "spruce_email";
    public final static String COOKIE_PASSWORD = "spruce_pwd";

    protected static Integer currentUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sidUser = (String) session.getAttribute(SESSION_USER_ID_KEY);
        return sidUser == null || "".equals(sidUser) ? null : Integer.parseInt(sidUser);
    }
}
