package fengfei.controllers;

import japidviews.Application.Profile.Account;
import japidviews.Application.Profile.Camera;
import japidviews.Application.Profile.Notification;
import japidviews.Application.Profile.Password;
import japidviews.Application.Profile.Profile;

import java.util.ArrayList;
import java.util.List;

import play.mvc.With;
import cn.bran.play.JapidResult;
import fengfei.fir.utils.BASE64;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.entity.profile.CameraModel;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;
import fengfei.ucm.service.CameraService;
import fengfei.ucm.service.UserService;
import fengfei.ucm.service.impl.CameraServiceImpl;
import fengfei.ucm.service.impl.UserServiceImpl;

@With(Secure.class)
public class ProfileAction extends Admin {
	static UserService userService = new UserServiceImpl();
	static CameraService cameraService = new CameraServiceImpl();

	public static void profile() {
		Integer idUser = currentUserId();
		System.out.println("user: " + idUser);
		try {

			User user = userService.getUser(idUser);
			UserPwd up = userService.getUserPwd(idUser);
			user.setEmail(up.getEmail());
			user.setUserName(up.getUserName());
			user.userName = up.userName;
			user.email = up.email;
			throw new JapidResult(new Profile().render(user));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new JapidResult(new Profile().render(new User()));
		}

	}

	public static void profileDone() {
		String userName = params.get("username");
		String email = params.get("email");
		String niceName = params.get("nicename");
		String firstName = params.get("firstname");
		String lastName = params.get("lastname");
		String country = params.get("country");
		String state = params.get("state");
		String city = params.get("city");
		String about = params.get("about");
		String sgender = params.get("gender");
		Integer gender = Integer.parseInt(sgender);
		String birthday = params.get("birthday");
		String phone = params.get("phone");
		Integer idUser = currentUserId();
		User user = new User(idUser, userName, email, firstName, lastName,
				birthday, gender, phone, about, city, state, country);
		user.setNiceName(niceName);
		if (idUser == null) {
			flash.put("error", "Please login!");
			throw new JapidResult(new Profile().render(user));
		}

		user.setIdUser(idUser);

		try {

			int updated = userService.saveUser(user);
			throw new JapidResult(new Profile().render(user));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new JapidResult(new Profile().render(new User()));

		}

	}

	public static void password() {
		throw new JapidResult(new Password().render());
	}

	public static void passwordDone() {
		String oldPwd = params.get("oldPassword");
		String newPwd = params.get("newPassword");
		String reNewPwd = params.get("reNewPassword");
		if (!newPwd.equals(reNewPwd)) {
			flash.put("error", "Twice password mismatched, please re-type.");
		} else {
			Integer idUser = currentUserId();
			if (idUser == null) {
				flash.put("error", "Please login!");
				idUser = 1;
			}
			try {
				int updated = userService.updatePassword(idUser,
						BASE64.encrypt(oldPwd), BASE64.encrypt(newPwd));
				if (updated == -1) {
					flash.put("error", "Old password mismatched.");
				} else if (updated == 0) {
					flash.put("error", "New password didn't updated.");
				} else {
					flash.put("success", "New password has updated.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				flash.put("error", "New password didn't updated.");
			}
		}
		throw new JapidResult(new Password().render());
	}

	public static void notification() {
		throw new JapidResult(new Notification().render());
	}

	public static void notificationDone() {
		throw new JapidResult(new Notification().render());
	}

	public static void camera() {
		Integer idUser = currentUserId();
		if (idUser == null) {
			flash.put("error", "Please login!");
			idUser = 1;
		}
		try {
			List<CameraModel> cameras = cameraService.selectForSorted(idUser);
			System.out.println("xxxx: " + cameras.size());
			throw new JapidResult(new Camera().render(cameras));
		} catch (DataAccessException e) {
			flash.put("error", "Server error!");
			e.printStackTrace();
			throw new JapidResult(
					new Camera().render(new ArrayList<CameraModel>()));
		}

	}

	public static void cameraDone() {
		String[] cameras = params.getAll(CameraModel.TypeCamera);
		String[] lenses = params.getAll(CameraModel.TypeLens);
		String[] tripods = params.getAll(CameraModel.TypeTripod);
		String[] filters = params.getAll(CameraModel.TypeFilter);

		throw new JapidResult(new Camera().render());
	}

	public static void account() {
		throw new JapidResult(new Account().render());
	}

	public static void accountDone() {
		throw new JapidResult(new Account().render());
	}
}
