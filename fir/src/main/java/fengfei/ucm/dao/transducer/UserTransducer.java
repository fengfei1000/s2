package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.profile.User;

public class UserTransducer implements Transducer<User> {

	@Override
	public User transform(ResultSet rs) throws SQLException {
		Integer idUser = rs.getInt("id_user");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String birthday = rs.getString("birthday");
		String sgender = rs.getString("gender");
		int gender = Integer.parseInt(sgender);
		String phoneNum = rs.getString("phone_num");
		String country = rs.getString("country");
		String state = rs.getString("state");
		String city = rs.getString("city");
		String about = rs.getString("about");
		String niceName = rs.getString("nice_name");

		User user= new User(idUser, null, null, firstName, lastName, birthday,
				gender, phoneNum, about, city, state, country);
		user.setNiceName(niceName);
		return user;
	}

}
