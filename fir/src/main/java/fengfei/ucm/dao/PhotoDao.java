package fengfei.ucm.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.database.dbutils.StringTransducer;
import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.ucm.dao.transducer.PhotoTransducer;
import fengfei.ucm.entity.photo.PhotoModel;
import fengfei.ucm.entity.photo.Rank;
import fengfei.ucm.entity.photo.Refresh;

public class PhotoDao {

    final static String RefreshInsert = "INSERT INTO refresh(id_photo,title, id_user,user_name, at) VALUES (?, ?, ?, ?, ?)";

    public static List<InsertResultSet<Long>> save(
        ForestGrower grower,
        String suffix,
        String userName,
        List<PhotoModel> models) throws SQLException {

        List<InsertResultSet<Long>> insertResultSets = new ArrayList<>();
        for (PhotoModel exifModel : models) {
            InsertResultSet<Long> u = saveOne(grower, suffix, exifModel, userName);
            insertResultSets.add(u);
        }

        return insertResultSets;
    }

    public static int addRefresh(ForestGrower grower, String suffix, Refresh m)
        throws SQLException {
        int updated = grower.update(
            RefreshInsert,
            m.idPhoto,
            m.title,
            m.idUser,
            m.userName,
            m.updateAt);
        return updated;

    }

    public static InsertResultSet<Long> saveOne(
        ForestGrower grower,
        String suffix,
        PhotoModel m,
        String userName) throws SQLException {
        String sql = "SELECT  id_photo    FROM photos" + suffix + " WHERE id_photo=?";
        String id = grower.selectOne(sql, new StringTransducer(), m.idPhoto);
        InsertResultSet<Long> u = null;

        if (id == null || "".equals(id)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createAtGmt = sdf.format(m.createAtGmt);
            String insert = "INSERT INTO photos"
                    + suffix
                    + "( id_user, title, `desc`, category, adult,copyright,create_at, create_at_gmt, update_at, comment_count, original_at,"
                    + " make, model, aperture, shutter, iso, lens, focus,  ev)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            u = grower.insert(
                insert,
                m.idUser,
                m.title,
                m.description,
                m.category,
                m.adult,
                m.copyright,
                m.createAt,
                createAtGmt,
                m.updateAt,
                0,
                m.dateTimeOriginal,
                m.make,
                m.model,
                m.aperture,
                m.shutter,
                m.iso,
                m.lens,
                m.focus,
                m.ev);
            m.setIdPhoto(u.autoPk);
            int updated = addRefresh(grower, suffix, new Refresh(
                m.idPhoto,
                m.title,
                m.idUser,
                userName,
                m.createAt));
            PhotoAccessDao.addRank(grower, suffix, new Rank(
                m.idPhoto,
                m.idUser,
                0,
                0,
                0,
                0,
                0,
                m.createAt));

        } else {

            String update = "update photos"
                    + suffix
                    + " set original_at=?,make=?,model=?,aperture=?,shutter=?,iso=?,lens=?,focus=?,ev=?,"
                    + "title=?, `desc`=?,category=?,adult=?,copyright=?,update_at=? "
                    + " where id_photo=? and id_user=?";
            int updated = grower.update(
                update,
                m.dateTimeOriginal,
                m.make,
                m.model,
                m.aperture,
                m.shutter,
                m.iso,
                m.lens,
                m.focus,
                m.ev,
                m.title,
                m.description,
                m.category,
                m.adult,
                m.copyright,
                m.updateAt,
                m.idPhoto,
                m.idUser);
            u = new InsertResultSet<Long>(updated, null);
        }

        return u;
    }

    public static PhotoModel selectOne(ForestGrower grower, String suffix, long idPhoto)
        throws SQLException {
        String sql = "SELECT id_photo, id_user, title, `desc`, category,adult,copyright, create_at, create_at_gmt, update_at, comment_count, original_at, make, model, aperture, shutter, iso, lens, focus, tags, ev"
                + "FROM photos" + suffix + " WHERE id_photo=?";
        PhotoModel exifModel = grower.selectOne(sql, new PhotoTransducer(), idPhoto);
        return exifModel;

    }

}
