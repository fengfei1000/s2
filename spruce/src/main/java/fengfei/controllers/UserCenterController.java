package fengfei.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fengfei.fir.utils.BASE64;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;
import fengfei.ucm.service.UserService;
import fengfei.ucm.service.impl.UserServiceImpl;

@Controller
public class UserCenterController extends Admin {

    UserService userService = new UserServiceImpl();

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView("app/login");
        String e = request.getParameter("email");
        String p = request.getParameter("password");
        String rem = request.getParameter("remember");
        rem = "1";
        String cpage = request.getParameter("cpage");
        if (e == null || p == null) {
            mv.addObject("cpage", cpageUrl(request.getQueryString()));
            return mv;
        }

        // validation.required(e);
        // validation.required(p);

        if ("".equals(p) ||"".equals(e)) {
            mv.addObject("email", e);
            mv.addObject("password", e);
            return mv;

        }

        boolean isLogin = verify(session, e, p, rem);

        if (isLogin) {
            if (cpage == null || "".equals(cpage)) {
                return new ModelAndView("redirect:/");
            } else {
                return new ModelAndView("redirect:" + cpage);
            }

        } else {
            mv.addObject("error", "Email And Password mismatch.");
            mv.addObject("email", e);
            return new ModelAndView("redirect:" + request.getRequestURL());
        }

    }

    public String cpageUrl(String url) {
        if (url == null || "".equals(url)) {
            return "";
        } else {
            return "?" + url;
        }
    }

    @RequestMapping("/logout")
    public String logout(
        HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session) {
        session.invalidate();
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length; ++i) {
            if (cookies[i].getName().equals(COOKIE_EMAIL)) {
                // Cookie cookie = new Cookie("user", cookies[i].getValue());
                // cookie.setMaxAge(0);
                // response.addCookie(cookie);
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
                break;
            }
        }

        return "redirect:/";
    }

    @RequestMapping("/logoff")
    @ResponseBody
    public String logoff(
        HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session) {
        logout(request, response, session);
        return "success";
    }

    @RequestMapping("/logon")
    @ResponseBody
    public Map<String, Object> logon(HttpServletRequest request, HttpSession session) {
        boolean isLogin;
        Map<String, Object> rs = new HashMap<>();
        try {

            String e = request.getParameter("email");
            String p = request.getParameter("password");
            String rem = request.getParameter("remember");
            isLogin = verify(session, e, p, rem);
            System.out.println("ssssssssssss=:  " + isLogin);
            rs.put("isLogin", isLogin);
            rs.put("username", e);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put("isLogin", false);

        }
        return rs;
    }

    private boolean verify(HttpSession session, String email, String passoword, String remember) {
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
            boolean isLogin = session.getAttribute(SESSION_LOGIN_KEY) != null;
            if (!isLogin) {
                UserPwd user = userService.getUserPwd(email, md5_pwd);
                if (user != null) {

                    session.setAttribute(SESSION_LOGIN_KEY, true);
                    session.setAttribute(SESSION_USER_ID_KEY, user.getIdUser());
                    session.setAttribute(SESSION_USER_NAME_KEY, user.getUserName());
                    // if (remember != null && "1".equals(remember)) {
                    // response.setCookie(COOKIE_EMAIL, e, "30d");
                    // response.setCookie(COOKIE_PASSWORD, p, "30d");
                    // }
                    User info = userService.getUser(user.getIdUser());
                    if (info.getNiceName() != null && !"".equals(info.getNiceName())) {
                        session.setAttribute(SESSION_USER_NAME_KEY, info.getNiceName());
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

    @RequestMapping("/signup")
    public String signup() {
        return "app/signup";
    }

    @RequestMapping("/register")
    public ModelAndView register(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView("app/signup");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        String cpage = request.getParameter("cpage");
        if (!password.equals(confirm_password)) {
            mv.addObject("error", "Twice password mismatched.");
            mv.addObject("user", new UserPwd(email, username, null));
            return mv;
        }
        if (password.getBytes().length > 20) {
            mv.addObject("error", "password length<=20 char(or 6 chinese).");
            mv.addObject("user", new UserPwd(email, username, null));
            return mv;
        }

        try {
            String md5_pwd = BASE64.encrypt(password);
            UserPwd up = new UserPwd(email, username, md5_pwd);
            int updated = userService.addUserPwd(up);
            if (updated == -3) {
                mv.addObject("error", "Username and Email already existed.");
            } else if (updated == -2) {
                mv.addObject("error", "Username already existed.");
            } else if (updated == -1) {
                mv.addObject("error", "Email already existed.");
            } else if (updated > 0) {
                session.setAttribute(SESSION_LOGIN_KEY, true);
                session.setAttribute(SESSION_USER_ID_KEY, updated);
                session.setAttribute(SESSION_USER_NAME_KEY, up.getUserName());
                if (cpage == null || "".equals(cpage)) {
                    return new ModelAndView("redirect:/");
                } else {
                    return new ModelAndView("redirect:" + cpage);
                }
            } else {
                mv.addObject("error", "Signup error.");

            }
            mv.addObject("user", new UserPwd(email, username, null));
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("error", "Signup error.");
            mv.addObject("user", new UserPwd(email, username, null));
            return mv;
        }

    }

}
