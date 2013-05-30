package fengfei.ucm.repository;

import java.util.List;

import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.photo.Photo;

public interface PhotoRepository extends UnitNames {

    List<InsertResultSet<Long>> save(List<Photo> models, final String userName)
        throws DataAccessException;

    InsertResultSet<Long> saveOne(Photo m, final String userName) throws DataAccessException;

    Photo selectOne(long idExif, int idUser) throws DataAccessException;

    int vote(long idPhoto) throws DataAccessException;

    int favorite(long idPhoto, int idUser, final String ip) throws DataAccessException;

    int unfavorite(long idPhoto, int idUser ) throws DataAccessException;
}
