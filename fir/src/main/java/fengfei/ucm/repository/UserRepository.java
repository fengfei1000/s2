package fengfei.ucm.repository;

import org.springframework.stereotype.Repository;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;

@Repository
public interface UserRepository extends UnitNames {
	int addUserPwd(UserPwd userPwd) throws DataAccessException;

	int saveUserPwd(UserPwd userPwd) throws DataAccessException;

	boolean isExists(UserPwd userPwd) throws DataAccessException;

	User getUser(int id) throws DataAccessException;

	UserPwd getUserPwd(int id) throws DataAccessException;

	UserPwd getUserPwd(String emailOrName, String pwd)
			throws DataAccessException;

	int saveUser(User user) throws DataAccessException;

	int updatePassword(int idUser, String oldPwd, String newPwd)
			throws DataAccessException;

	int updateUserById(User user) throws DataAccessException;

	int updateUserByEmail(User user) throws DataAccessException;

	int updateUserByUserName(User user) throws DataAccessException;

}
