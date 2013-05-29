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
import fengfei.ucm.entity.profile.CameraModel;
import fengfei.ucm.repository.CameraRepository;

public class SqlCameraRepository implements CameraRepository {

	@Override
	public boolean add(List<CameraModel> models) throws DataAccessException {
		try {
			for (final CameraModel m : models) {

				boolean updated = addOne(m);
			}

			return true;
		} catch (Exception e) {
			throw new DataAccessException("add camera error.", e);
		}

	}

	@Override
	public boolean addOne(final CameraModel m) throws DataAccessException {
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
	public List<CameraModel> select(final int idUser)
			throws DataAccessException {
		try {

			List<CameraModel> cms = Transactions.execute(UserUnitName,
					new Long(idUser), Function.Write,
					new TaCallback<List<CameraModel>>() {

						@Override
						public List<CameraModel> execute(ForestGrower grower,
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
	public List<CameraModel> selectForSorted(final int idUser)
			throws DataAccessException {
		try {

			List<CameraModel> cms = Transactions.execute(UserUnitName,
					new Long(idUser), Function.Write,
					new TaCallback<List<CameraModel>>() {

						@Override
						public List<CameraModel> execute(ForestGrower grower,
								String suffix) throws SQLException {
							suffix = "";
							return CameraDao.select(grower, suffix, idUser);
						}

					});
			Collections.sort(cms, new Comparator<CameraModel>() {

				@Override
				public int compare(CameraModel o1, CameraModel o2) {

					return o1.getType().compareToIgnoreCase(o2.getType());
				}
			});
			return cms;
		} catch (Exception e) {
			throw new DataAccessException("select camera error.", e);
		}
	}

	@Override
	public ListMultimap<String, CameraModel> selectGroup(final int idUser)
			throws DataAccessException {
		try {

			ListMultimap<String, CameraModel> cms = Transactions.execute(
					UserUnitName, new Long(idUser), Function.Write,
					new TaCallback<ListMultimap<String, CameraModel>>() {

						@Override
						public ListMultimap<String, CameraModel> execute(
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
