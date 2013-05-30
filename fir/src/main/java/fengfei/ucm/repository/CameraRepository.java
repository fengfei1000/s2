package fengfei.ucm.repository;

import java.util.List;

import com.google.common.collect.ListMultimap;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.profile.Camera;

public interface CameraRepository extends UnitNames {
	boolean add(List<Camera> models) throws DataAccessException;

	boolean addOne(Camera m) throws DataAccessException;

	List<Camera> select(int idUser) throws DataAccessException;

	List<Camera> selectForSorted(int idUser) throws DataAccessException;

	ListMultimap<String, Camera> selectGroup(int idUser)
			throws DataAccessException;
}
