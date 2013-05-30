package fengfei.ucm.repository.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.forest.slice.SliceResource.Function;
import fengfei.forest.slice.database.PoolableDatabaseResource;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.PhotoAccessDao;
import fengfei.ucm.dao.PhotoDao;
import fengfei.ucm.dao.ShowDao;
import fengfei.ucm.dao.Transactions;
import fengfei.ucm.dao.Transactions.TaCallback;
import fengfei.ucm.dao.Transactions.TransactionCallback;
import fengfei.ucm.entity.photo.PhotoAccess;
import fengfei.ucm.entity.photo.PhotoAccess.AccessType;
import fengfei.ucm.entity.photo.Canceled;
import fengfei.ucm.entity.photo.Photo;
import fengfei.ucm.entity.photo.Refresh;
import fengfei.ucm.repository.PhotoRepository;

@Repository
public class SqlPhotoRepository implements PhotoRepository {

    @Override
    public List<InsertResultSet<Long>> save(List<Photo> models, final String userName)
        throws DataAccessException {
        List<InsertResultSet<Long>> insertResultSets = new ArrayList<>();
        for (Photo exifModel : models) {
            InsertResultSet<Long> u = saveOne(exifModel, userName);
            insertResultSets.add(u);
        }

        return insertResultSets;
    }

    @Override
    public InsertResultSet<Long> saveOne(final Photo m, final String userName)
        throws DataAccessException {
        TransactionCallback<InsertResultSet<Long>> callback = new TransactionCallback<InsertResultSet<Long>>() {

            @Override
            public InsertResultSet<Long> execute(
                ForestGrower grower,
                PoolableDatabaseResource resource) throws SQLException {
                String suffix = resource.getAlias();
                suffix = "";
                InsertResultSet<Long> u = PhotoDao.saveOne(grower, suffix, m, userName);

                return u;
            }
        };
        return Transactions.execute(UserUnitName, new Long(m.idUser), Function.Write, callback);

    }

    @Override
    public Photo selectOne(final long idPhoto, final int idUser) throws DataAccessException {

        TransactionCallback<Photo> callback = new TransactionCallback<Photo>() {

            @Override
            public Photo execute(ForestGrower grower, PoolableDatabaseResource resource)
                throws SQLException {
                String suffix = resource.getAlias();
                suffix = "";
                return PhotoDao.selectOne(grower, suffix, idPhoto);
            }
        };
        return Transactions.execute(UserUnitName, new Long(idUser), Function.Read, callback);
    }

    @Override
    public int vote(final long idPhoto) throws DataAccessException {
        try {

            Integer updated = Transactions.execute(
                UserUnitName,
                idPhoto,
                Function.Write,
                new TaCallback<Integer>() {

                    @Override
                    public Integer execute(ForestGrower grower, String suffix) throws SQLException {
                        suffix = "";
                        return PhotoAccessDao.updateRank(
                            grower,
                            suffix,
                            idPhoto,
                            AccessType.Vote,
                            1);
                    }

                });

            return updated;
        } catch (Exception e) {
            throw new DataAccessException("vote error.", e);
        }
    }

    @Override
    public int favorite(final long idPhoto, final int idUser, final String ip)
        throws DataAccessException {
        try {
            long current = System.currentTimeMillis();
            final int updateAt = (int) (current / 1000);
            Integer updated = Transactions.execute(
                UserUnitName,
                idPhoto,
                Function.Write,
                new TaCallback<Integer>() {

                    @Override
                    public Integer execute(ForestGrower grower, String suffix) throws SQLException {
                        suffix = "";
                        PhotoAccessDao.addPhotoAccess(grower, suffix, new PhotoAccess(
                            idPhoto,
                            idUser,
                            updateAt,
                            0,
                            ip,
                            AccessType.Favorite));
                        return PhotoAccessDao.updateRank(
                            grower,
                            suffix,
                            idPhoto,
                            AccessType.Favorite,
                            1);
                    }

                });

            return updated;
        } catch (Exception e) {
            throw new DataAccessException("favorite error.", e);
        }
    }

    @Override
    public int unfavorite(final long idPhoto, final int idUser) throws DataAccessException {
        try {
            Integer updated = Transactions.execute(
                UserUnitName,
                idPhoto,
                Function.Write,
                new TaCallback<Integer>() {

                    @Override
                    public Integer execute(ForestGrower grower, String suffix) throws SQLException {
                        suffix = "";
                        PhotoAccessDao.cancelPhotoAccess(
                            grower,
                            suffix,
                            idPhoto,
                            idUser,
                            AccessType.Favorite);
                        return PhotoAccessDao.updateRank(
                            grower,
                            suffix,
                            idPhoto,
                            AccessType.Favorite,
                            -1);
                    }

                });

            return updated;
        } catch (Exception e) {
            throw new DataAccessException("favorite error.", e);
        }
    }
}
