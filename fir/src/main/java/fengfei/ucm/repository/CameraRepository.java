package fengfei.ucm.repository;

import java.util.List;

import com.google.common.collect.ListMultimap;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.profile.CameraModel;

public interface CameraRepository extends UnitNames {
	boolean add(List<CameraModel> models) throws DataAccessException;

	boolean addOne(CameraModel m) throws DataAccessException;

	List<CameraModel> select(int idUser) throws DataAccessException;

	List<CameraModel> selectForSorted(int idUser) throws DataAccessException;

	ListMultimap<String, CameraModel> selectGroup(int idUser)
			throws DataAccessException;
}
