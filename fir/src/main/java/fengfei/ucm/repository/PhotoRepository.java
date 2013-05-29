package fengfei.ucm.repository;

import java.util.List;

import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.photo.PhotoModel;

public interface PhotoRepository extends UnitNames {

    List<InsertResultSet<Long>> save(List<PhotoModel> models, final String userName)
        throws DataAccessException;

    InsertResultSet<Long> saveOne(PhotoModel m, final String userName) throws DataAccessException;

    PhotoModel selectOne(long idExif, int idUser) throws DataAccessException;

    int vote(long idPhoto) throws DataAccessException;

    int favorite(long idPhoto, int idUser, final String ip) throws DataAccessException;

    int unfavorite(long idPhoto, int idUser ) throws DataAccessException;
}
