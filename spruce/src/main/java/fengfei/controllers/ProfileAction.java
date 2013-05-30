package fengfei.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fengfei.fir.utils.BASE64;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.entity.profile.Camera;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;
import fengfei.ucm.service.CameraService;
import fengfei.ucm.service.UserService;
import fengfei.ucm.service.impl.CameraServiceImpl;
import fengfei.ucm.service.impl.UserServiceImpl;

@Controller
public class ProfileAction extends Admin {
	static Logger logger = LoggerFactory.getLogger(ProfileAction.class);
	UserService userService = new UserServiceImpl();
	CameraService cameraService = new CameraServiceImpl();

	@RequestMapping("/profile")
	public ModelAndView profile(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile/profile");
		Integer idUser = currentUserId(request);
		System.out.println("user: " + idUser);

		try {

			User user = userService.getUser(idUser);
			UserPwd up = userService.getUserPwd(idUser);
			user.setEmail(up.getEmail());
			user.setUserName(up.getUserName());
			user.userName = up.userName;
			user.email = up.email;
			mv.addObject("user", user);

		} catch (Exception e) {
			logger.error("profile index error.", e);
		}
		return mv;
	}

	@RequestMapping("/profile/done")
	public ModelAndView profileDone(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile/profile");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String niceName = request.getParameter("nicename");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String about = request.getParameter("about");
		String sgender = request.getParameter("gender");
		Integer gender = Integer.parseInt(sgender);
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		Integer idUser = currentUserId(request);
		User user = new User(idUser, userName, email, firstName, lastName,
				birthday, gender, phone, about, city, state, country);
		user.setNiceName(niceName);
		if (idUser == null) {
			mv.addObject("error", "Please login!");

		}
		mv.addObject("user", user);
		user.setIdUser(idUser);

		try {
			int updated = userService.saveUser(user);

		} catch (DataAccessException e) {
			logger.error("profile save error.", e);

		}
		return mv;

	}

	@RequestMapping("/password")
	public String password() {
		return "profile/password";
	}

	@RequestMapping("/password/done")
	public ModelAndView passwordDone(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile/password");
		String oldPwd = request.getParameter("oldPassword");
		String newPwd = request.getParameter("newPassword");
		String reNewPwd = request.getParameter("reNewPassword");
		if (!newPwd.equals(reNewPwd)) {
			mv.addObject("error", "Twice password mismatched, please re-type.");
		} else {
			Integer idUser = currentUserId(request);
			if (idUser == null) {
				mv.addObject("error", "Please login!");
				idUser = 1;
			}
			try {
				int updated = userService.updatePassword(idUser,
						BASE64.encrypt(oldPwd), BASE64.encrypt(newPwd));
				if (updated == -1) {
					mv.addObject("error", "Old password mismatched.");
				} else if (updated == 0) {
					mv.addObject("error", "New password didn't updated.");
				} else {
					mv.addObject("success", "New password has updated.");
				}

			} catch (Exception e) {
				mv.addObject("error", "New password didn't updated.");
				logger.error("password save error.", e);
			}
		}
		return mv;
	}

	@RequestMapping("/notification")
	public String notification() {
		return "notification";
	}

	@RequestMapping("/notification/done")
	public ModelAndView notificationDone() {
		ModelAndView mv = new ModelAndView("profile/notification");
		return mv;
	}

	@RequestMapping("/camera")
	public ModelAndView camera(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile/camera");
		Integer idUser = currentUserId(request);
		if (idUser == null) {
			mv.addObject("error", "Please login!");
			idUser = 1;
		}
		try {
			List<Camera> cameras = cameraService.selectForSorted(idUser);
			System.out.println("xxxx: " + cameras.size());
			mv.addObject("cameras", cameras);
		} catch (DataAccessException e) {
			mv.addObject("error", "Server error!");
			mv.addObject("cameras", new ArrayList<>());
			logger.error("camera error.", e);
		}
		return mv;

	}

	@RequestMapping("/camera/done")
	public ModelAndView cameraDone(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("profile/camera");
		String[] cameras = request.getParameterValues(Camera.TypeCamera);
		String[] lenses = request.getParameterValues(Camera.TypeLens);
		String[] tripods = request.getParameterValues(Camera.TypeTripod);
		String[] filters = request.getParameterValues(Camera.TypeFilter);

		return mv;
	}

	@RequestMapping("/account")
	public ModelAndView account() {
		ModelAndView mv = new ModelAndView("profile/camera");
		return mv;
	}

	@RequestMapping("/account/done")
	public ModelAndView accountDone() {
		ModelAndView mv = new ModelAndView("profile/camera");
		return mv;
	}
}
