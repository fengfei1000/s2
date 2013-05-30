package fengfei.ucm.repository.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.ListMultimap;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.slice.SliceResource.Function;
import fengfei.ucm.dao.CameraDao;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.Transactions;
import fengfei.ucm.dao.Transactions.TaCallback;
import fengfei.ucm.entity.profile.Camera;
import fengfei.ucm.repository.CameraRepository;

public class SqlCameraRepository implements CameraRepository {

	@Override
	public boolean add(List<Camera> models) throws DataAccessException {
		try {
			for (final Camera m : models) {

				boolean updated = addOne(m);
			}

			return true;
		} catch (Exception e) {
			throw new DataAccessException("add camera error.", e);
		}

	}

	@Override
	public boolean addOne(final Camera m) throws DataAccessException {
		try {

			long id = m.getIdUser();
			int updated = Transactions.execute(UserUnitName, new Long(id),
					Function.Write, new TaCallback<Integer>() {

						@Override
						public Integer execute(ForestGrower grower,
								String suffix) throws SQLException {
							suffix = "";
							return CameraDao.addOne(grower, suffix, m);
						}

					});

			return updated > 0;
		} catch (Exception e) {
			throw new DataAccessException("add camera error.", e);
		}

	}

	@Override
	public List<Camera> select(final int idUser)
			throws DataAccessException {
		try {

			List<Camera> cms = Transactions.execute(UserUnitName,
					new Long(idUser), Function.Write,
					new TaCallback<List<Camera>>() {

						@Override
						public List<Camera> execute(ForestGrower grower,
								String suffix) throws SQLException {
							suffix = "";
							return CameraDao.select(grower, suffix, idUser);
						}

					});

			return cms;
		} catch (Exception e) {
			throw new DataAccessException("select camera error.", e);
		}
	}

	@Override
	public List<Camera> selectForSorted(final int idUser)
			throws DataAccessException {
		try {

			List<Camera> cms = Transactions.execute(UserUnitName,
					new Long(idUser), Function.Write,
					new TaCallback<List<Camera>>() {

						@Override
						public List<Camera> execute(ForestGrower grower,
								String suffix) throws SQLException {
							suffix = "";
							return CameraDao.select(grower, suffix, idUser);
						}

					});
			Collections.sort(cms, new Comparator<Camera>() {

				@Override
				public int compare(Camera o1, Camera o2) {

					return o1.getType().compareToIgnoreCase(o2.getType());
				}
			});
			return cms;
		} catch (Exception e) {
			throw new DataAccessException("select camera error.", e);
		}
	}

	@Override
	public ListMultimap<String, Camera> selectGroup(final int idUser)
			throws DataAccessException {
		try {

			ListMultimap<String, Camera> cms = Transactions.execute(
					UserUnitName, new Long(idUser), Function.Write,
					new TaCallback<ListMultimap<String, Camera>>() {

						@Override
						public ListMultimap<String, Camera> execute(
								ForestGrower grower, String suffix)
								throws SQLException {
							suffix = "";
							return CameraDao
									.selectGroup(grower, suffix, idUser);
						}

					});

			return cms;
		} catch (Exception e) {
			throw new DataAccessException("select camera error.", e);
		}
	}

}
