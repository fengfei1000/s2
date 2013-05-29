package fengfei.ucm.repository;

import java.util.List;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.photo.PhotoModel;
import fengfei.ucm.entity.photo.Refresh;

public interface ShowRepository extends UnitNames {

    public final static long RefreshKey = 1l;

    List<Refresh> refreshed(int offset, int row) throws DataAccessException;

    PhotoModel get(long idPhoto) throws DataAccessException;

    PhotoModel view(long idPhoto, int idUser, String ip) throws DataAccessException;
}
