package fengfei.ucm.repository.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.forest.slice.SliceResource.Function;
import fengfei.forest.slice.database.PoolableDatabaseResource;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.Transactions;
import fengfei.ucm.dao.Transactions.TaCallback;
import fengfei.ucm.dao.Transactions.TransactionCallback;
import fengfei.ucm.dao.UserDao;
import fengfei.ucm.entity.profile.User;
import fengfei.ucm.entity.profile.UserPwd;
import fengfei.ucm.repository.UserRepository;

@Repository
public class SqlUserRepository implements UserRepository {

	@Override
	public int addUserPwd(final UserPwd userPwd) throws DataAccessException {

		TransactionCallback<Integer> callback = new TransactionCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower,
					PoolableDatabaseResource resource) throws SQLException {
				String suffix = resource.getAlias();
				suffix = "";
				boolean isUserNamed = UserDao.isExistsForUserName(grower,
						suffix, userPwd.getUserName());
				boolean isEmailed = UserDao.isExistsForEmail(grower, suffix,
						userPwd.getEmail());
				if (isUserNamed && isEmailed) {
					return -3;
				}
				if (isUserNamed) {
					return -2;
				}
				if (isEmailed) {
					return -1;
				}
				InsertResultSet<Integer> rs = UserDao.addUserPwd(grower,
						suffix, userPwd);
				if (rs.rows > 0) {
					return rs.autoPk;
				} else {
					return 0;
				}

			}
		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	@Override
	public int saveUserPwd(final UserPwd userPwd) throws DataAccessException {

		TransactionCallback<Integer> callback = new TransactionCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower,
					PoolableDatabaseResource resource) throws SQLException {
				String suffix = resource.getAlias();
				suffix = "";
				return UserDao.saveUserPwd(grower, suffix, userPwd);
			}
		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	@Override
	public boolean isExists(final UserPwd userPwd) throws DataAccessException {

		TransactionCallback<Boolean> callback = new TransactionCallback<Boolean>() {

			@Override
			public Boolean execute(ForestGrower grower,
					PoolableDatabaseResource resource) throws SQLException {
				String suffix = resource.getAlias();
				suffix = "";
				return UserDao.isExists(grower, suffix, userPwd);
			}
		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	@Override
	public UserPwd getUserPwd(final int idUser) throws DataAccessException {

		TaCallback<UserPwd> callback = new TaCallback<UserPwd>() {

			@Override
			public UserPwd execute(ForestGrower grower, String suffix)
					throws SQLException {
				suffix = "";
				return UserDao.getUserPwd(grower, suffix, idUser);
			}

		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	public UserPwd getUserPwd(final String emailOrName, final String pwd)
			throws DataAccessException {

		TaCallback<UserPwd> callback = new TaCallback<UserPwd>() {

			@Override
			public UserPwd execute(ForestGrower grower, String suffix)
					throws SQLException {
				suffix = "";
				return UserDao.getUserPwd(grower, suffix, emailOrName, pwd);
			}

		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	@Override
	public int updatePassword(final int idUser, final String oldPwd,
			final String newPwd) throws DataAccessException {

		TaCallback<Integer> callback = new TaCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower, String suffix)
					throws SQLException {
				suffix = "";
				Map<String, Object> user = UserDao.getFullUserPwd(grower,
						suffix, idUser);
				String pwd = user.get("password").toString();
				System.out.printf("old=%s,new=%s,pwd=%s,up=%s\n", oldPwd,
						newPwd, pwd, user.toString());

				if (user == null || !oldPwd.equals(pwd)) {
					return -1;
				}
				return UserDao.updatePassword(grower, suffix, idUser, newPwd);
			}

		};
		return Transactions.execute(UserUnitName, UserPwdSliceId,
				Function.Write, callback);
	}

	// -------------------------------------User
	@Override
	public User getUser(final int idUser) throws DataAccessException {

		TaCallback<User> callback = new TaCallback<User>() {

			@Override
			public User execute(ForestGrower grower, String suffix)
					throws SQLException {
				suffix = "";
				User user = UserDao.getUser(grower, suffix, idUser);

				return user;
			}

		};
		return Transactions.execute(UserUnitName, new Long(idUser),
				Function.Write, callback);
	}

	@Override
	public int saveUser(final User user) throws DataAccessException {

		TaCallback<Integer> callback = new TaCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower, String suffix)
					throws SQLException {
				suffix = "";
				User u = UserDao.getUser(grower, suffix, user.getIdUser());
				if (u == null) {
					return UserDao.saveUser(grower, suffix, user);
				} else {
					return UserDao.updateUser(grower, suffix, user);
				}

			}

		};
		return Transactions.execute(UserUnitName, new Long(user.idUser),
				Function.Write, callback);
	}

	@Override
	public int updateUserById(final User user) throws DataAccessException {

		TaCallback<Integer> callback = new TaCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower, String suffix)
					throws SQLException {
				return UserDao.updateUserById(grower, suffix, user);
			}

		};
		return Transactions.execute(PhotoUnitName, user.idUser, Function.Write,
				callback);
	}

	@Override
	public int updateUserByEmail(final User user) throws DataAccessException {

		TaCallback<Integer> callback = new TaCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower, String suffix)
					throws SQLException {
				return UserDao.updateUserByEmail(grower, suffix, user);
			}

		};
		return Transactions.execute(PhotoUnitName, user.idUser, Function.Write,
				callback);
	}

	@Override
	public int updateUserByUserName(final User user) throws DataAccessException {

		TaCallback<Integer> callback = new TaCallback<Integer>() {

			@Override
			public Integer execute(ForestGrower grower, String suffix)
					throws SQLException {
				return UserDao.updateUserByUserName(grower, suffix, user);
			}

		};
		return Transactions.execute(PhotoUnitName, user.idUser, Function.Write,
				callback);
	}

}
