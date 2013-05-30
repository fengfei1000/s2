package fengfei.ucm.repository;

import java.util.List;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.photo.Photo;
import fengfei.ucm.entity.photo.Refresh;

public interface ShowRepository extends UnitNames {

    public final static long RefreshKey = 1l;

    List<Refresh> refreshed(int offset, int row) throws DataAccessException;

    Photo get(long idPhoto) throws DataAccessException;

    Photo view(long idPhoto, int idUser, String ip) throws DataAccessException;
}
