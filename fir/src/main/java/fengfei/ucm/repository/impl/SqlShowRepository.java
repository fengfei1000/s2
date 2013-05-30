package fengfei.ucm.repository.impl;

import java.sql.SQLException;
import java.util.List;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.slice.SliceResource.Function;
import fengfei.ucm.dao.CameraDao;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.PhotoAccessDao;
import fengfei.ucm.dao.ShowDao;
import fengfei.ucm.dao.Transactions;
import fengfei.ucm.dao.Transactions.TaCallback;
import fengfei.ucm.entity.photo.PhotoAccess;
import fengfei.ucm.entity.photo.PhotoAccess.AccessType;
import fengfei.ucm.entity.photo.Photo;
import fengfei.ucm.entity.photo.Rank;
import fengfei.ucm.entity.photo.Refresh;
import fengfei.ucm.entity.profile.Camera;
import fengfei.ucm.repository.ShowRepository;

public class SqlShowRepository implements ShowRepository {

    @Override
    public List<Refresh> refreshed(final int offset, final int row) throws DataAccessException {
        try {

            List<Refresh> cms = Transactions.execute(
                UserUnitName,
                RefreshKey,
                Function.Write,
                new TaCallback<List<Refresh>>() {

                    @Override
                    public List<Refresh> execute(ForestGrower grower, String suffix)
                        throws SQLException {
                        suffix = "";
                        return ShowDao.pageRefreshPhoto(grower, suffix, offset, row);
                    }

                });

            return cms;
        } catch (Exception e) {
            throw new DataAccessException("select camera error.", e);
        }

    }

    @Override
    public Photo get(final long idPhoto) throws DataAccessException {
        try {

            Photo photo = Transactions.execute(
                UserUnitName,
                RefreshKey,
                Function.Write,
                new TaCallback<Photo>() {

                    @Override
                    public Photo execute(ForestGrower grower, String suffix)
                        throws SQLException {
                        suffix = "";
                        return ShowDao.getPhoto(grower, suffix, idPhoto);
                    }

                });

            return photo;
        } catch (Exception e) {
            throw new DataAccessException("select camera error.", e);
        }
    }

    @Override
    public Photo view(final long idPhoto, final Integer idUser, final String ip)
        throws DataAccessException {
        try {
            long current = System.currentTimeMillis();
            final int updateAt = (int) (current / 1000);
            Photo photo = Transactions.execute(
                UserUnitName,
                RefreshKey,
                Function.Write,
                new TaCallback<Photo>() {

                    @Override
                    public Photo execute(ForestGrower grower, String suffix)
                        throws SQLException {
                        suffix = "";
                        int updated = PhotoAccessDao.addPhotoAccess(
                            grower,
                            suffix,
                            new PhotoAccess(idPhoto, idUser, updateAt, 0, ip, AccessType.View));
                        if (updated > 0) {
                            PhotoAccessDao.updateRank(grower, suffix, idPhoto, AccessType.View, 1);
                        }
                        Rank rank = PhotoAccessDao.getRank(grower, suffix, idPhoto);
                        Photo photoModel = ShowDao.getPhoto(grower, suffix, idPhoto);
                        photoModel.rank = rank;
                        return photoModel;
                    }

                });

            return photo;
        } catch (Exception e) {
            throw new DataAccessException("select camera error.", e);
        }
    }

}
