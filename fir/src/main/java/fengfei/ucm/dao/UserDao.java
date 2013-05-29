package fengfei.ucm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.database.dbutils.LongTransducer;
import fengfei.forest.database.dbutils.StringTransducer;
import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.ucm.dao.transducer.UserPwdTransducer;
import fengfei.ucm.dao.transducer.UserTransducer;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;

public class UserDao {

	public static int saveUserPwd(ForestGrower grower, String suffix,
			UserPwd userPwd) throws SQLException {

		String insert = "INSERT INTO user_pwd" + suffix
				+ "( email, username, password) VALUES ( ?,?,?)";
		int updated = grower.update(insert, userPwd.getEmail(),
				userPwd.getUserName(), userPwd.getPassword());

		return updated;
	}

	public static InsertResultSet<Integer> addUserPwd(ForestGrower grower,
			String suffix, UserPwd userPwd) throws SQLException {

		String insert = "INSERT INTO user_pwd" + suffix
				+ "( email, username, password) VALUES ( ?,?,?)";

		InsertResultSet<Integer> rs = grower.insertForInt(insert,
				userPwd.getEmail(), userPwd.getUserName(),
				userPwd.getPassword());
		return rs;
	}

	public static int updatePassword(ForestGrower grower, String suffix,
			int idUser, String newPwd) throws SQLException {
		String update = "update user_pwd" + suffix
				+ " set password=?   where   id_user=?";
		int updated = grower.update(update, newPwd, idUser);
		return updated;
	}

	public static boolean isExists(ForestGrower grower, String suffix,
			UserPwd userPwd) throws SQLException {
		String sql = "select id_user from user_pwd" + suffix
				+ " where  username=? or email=?  ";
		String id = grower.selectOne(sql, new StringTransducer(),
				userPwd.getEmail(), userPwd.getUserName());
		return id != null && !"".equals(id);
	}

	public static boolean isExistsForEmail(ForestGrower grower, String suffix,
			String email) throws SQLException {
		String sql = "select id_user from user_pwd" + suffix
				+ " where    email=?  ";
		String id = grower.selectOne(sql, new StringTransducer(), email);
		return id != null && !"".equals(id);
	}

	public static boolean isExistsForUserName(ForestGrower grower,
			String suffix, String username) throws SQLException {
		String sql = "select id_user from user_pwd" + suffix
				+ " where username=?  ";
		String id = grower.selectOne(sql, new StringTransducer(), username);
		return id != null && !"".equals(id);
	}

	public static User getUser(ForestGrower grower, String suffix, int idUser)
			throws SQLException {
		String sql = "SELECT id_user, first_name, last_name,nice_name, birthday, gender, phone_num, country, state, city, about  FROM user"
				+ suffix + " where id_user=?";
		User user = grower.selectOne(sql, new UserTransducer(), idUser);

		return user;
	}

	public static UserPwd getUserPwd(ForestGrower grower, String suffix,
			int idUser) throws SQLException {
		String sql = "SELECT id_user, email, username FROM user_pwd" + suffix
				+ " where id_user=?";
		UserPwd user = grower.selectOne(sql, new UserPwdTransducer(), idUser);
		return user;
	}

	public static Map<String, Object> getFullUserPwd(ForestGrower grower,
			String suffix, int idUser) throws SQLException {
		String sql = "SELECT id_user, email, username,password FROM user_pwd"
				+ suffix + " where id_user=?";
		Map<String, Object> user = grower.selectOne(sql, idUser);
		return user;
	}

	public static UserPwd getUserPwd(ForestGrower grower, String suffix,
			String emailOrName, String pwd) throws SQLException {
		String sql = "SELECT id_user, email, username FROM user_pwd" + suffix
				+ " where (email=? or username=?) and password=?";
		UserPwd user = grower.selectOne(sql, new UserPwdTransducer(),
				emailOrName, emailOrName, pwd);
		return user;
	}

	// //
	public static int saveUser(ForestGrower grower, String suffix, User user)
			throws SQLException {

		String insert = "INSERT INTO user"
				+ suffix
				+ "(id_user,first_name, last_name,nice_name, birthday, gender, phone_num, country, state, city, about)  VALUES (?,?, ?,?,?,?,?,?,?,?,?)";

		int updated = grower.update(insert, user.getIdUser(),
				user.getFirstName(), user.getLastName(), user.getNiceName(),
				user.getBirthday(), user.getGender(), user.getPhoneNum(),
				user.getCountry(), user.getState(), user.getCity(),
				user.getAbout());

		return updated;
	}

	public static int updateUser(ForestGrower grower, String suffix, User user)
			throws SQLException {
		String update = "UPDATE user"
				+ suffix
				+ " SET first_name = ? , last_name = ?,nice_name=?, birthday = ?, "
				+ "gender = ?, phone_num = ?, country = ?, state = ?, city = ?, about = ? where id_user=? ";

		int updated = grower.update(update, user.getFirstName(),
				user.getLastName(), user.getNiceName(), user.getBirthday(),
				user.getGender(), user.getPhoneNum(), user.getCountry(),
				user.getState(), user.getCity(), user.getAbout(),
				user.getIdUser());

		return updated;
	}

	public static int updateUserById(ForestGrower grower, String suffix,
			User user) throws SQLException {

		String update = "UPDATE user"
				+ suffix
				+ " SET first_name = ? , last_name = ?,nice_name=?, birthday = ?, "
				+ "gender = ?, phone_num = ?, country = ?, state = ?, city = ?, about = ? where id_user=? ";

		int updated = grower.update(update, user.getFirstName(),
				user.getLastName(), user.getNiceName(), user.getBirthday(),
				user.getGender(), user.getPhoneNum(), user.getCountry(),
				user.getState(), user.getCity(), user.getAbout(),
				user.getIdUser());
		return updated;
	}

	public static int updateUserByEmail(ForestGrower grower, String suffix,
			User user) throws SQLException {
		String sql = "select id_user from user_pwd" + suffix
				+ " where email=?  ";
		String update = "update user_pwd"
				+ suffix
				+ "SET first_name = '? , last_name =?, nice_name=?,birthday =?, gender = ?, phone_num = ?, country = ?, state = ?, city = ?, about = ? "
				+ " where id_user=(" + sql + ")";
		int updated = grower.update(update, user.getFirstName(),
				user.getLastName(), user.getNiceName(), user.getBirthday(),
				user.getGender(), user.getPhoneNum(), user.getCountry(),
				user.getState(), user.getCity(), user.getAbout(),
				user.getEmail());
		return updated;
	}

	public static int updateUserByUserName(ForestGrower grower, String suffix,
			User user) throws SQLException {
		String sql = "select id_user from user_pwd" + suffix
				+ " where  username=?  ";
		String update = "update user_pwd"
				+ suffix
				+ "SET first_name = '? , last_name =?, nice_name=?,birthday =?, gender = ?, phone_num = ?, country = ?, state = ?, city = ?, about = ? "
				+ " where username=(" + sql + ") ";
		int updated = grower.update(update, user.getFirstName(),
				user.getLastName(), user.getNiceName(), user.getBirthday(),
				user.getGender(), user.getPhoneNum(), user.getCountry(),
				user.getState(), user.getCity(), user.getAbout(),
				user.getUserName());
		return updated;
	}



}
