package fengfei.ucm.dao;

import java.sql.SQLException;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.database.dbutils.LongTransducer;
import fengfei.ucm.dao.transducer.RankTransducer;
import fengfei.ucm.entity.photo.Canceled;
import fengfei.ucm.entity.photo.PhotoAccess;
import fengfei.ucm.entity.photo.Rank;
import fengfei.ucm.entity.photo.PhotoAccess.AccessType;

public class PhotoAccessDao {

    final static String InsertRank = "INSERT INTO rank%s("
            + "id_photo, id_user, view, vote, favorite, comment, score, update_at)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    final static String UpdateViewCount = "UPDATE rank%s "
            + "SET view = view+?, update_at = ? where id_photo=? ";
    final static String UpdateVoteCount = "UPDATE rank%s "
            + "SET vote = vote+?, update_at = ? where id_photo=? ";
    final static String UpdateFavoriteCount = "UPDATE rank%s "
            + "SET favorite = favorite+?, update_at = ? where id_photo=? ";
    final static String UpdateCommentCount = "UPDATE rank%s "
            + "SET comment = comment+?, update_at = ? where id_photo=? ";
    final static String UpdateScoreCount = "UPDATE rank%s "
            + "SET score = ?, update_at = ? where id_photo=? ";
    final static String SelectRank = "SELECT id_photo, id_user, view, vote, favorite, comment, score, update_at FROM rank%s where id_photo=?";

    public static int addRank(ForestGrower grower, String suffix, Rank m) throws SQLException {
        String insert = String.format(InsertRank, suffix);
        int updated = grower.update(
            insert,
            m.idPhoto,
            m.idUser,
            m.view,
            m.vote,
            m.favorite,
            m.comment,
            m.score,
            m.updateAt);
        return updated;

    }

    public static Rank getRank(ForestGrower grower, String suffix, long idPhoto)
        throws SQLException {
        String sql = String.format(SelectRank, suffix);
        Rank rank = grower.selectOne(sql, new RankTransducer(), idPhoto);
        return rank;

    }

    public static int updateRank(
        ForestGrower grower,
        String suffix,
        long idPhoto,
        AccessType type,
        int value) throws SQLException {
        String updateSQL = null;
        switch (type) {
        case View:
            updateSQL = UpdateViewCount;
            break;
        case Vote:
            updateSQL = UpdateVoteCount;
            break;
        case Favorite:
            updateSQL = UpdateFavoriteCount;
            break;
        case Comment:
            updateSQL = UpdateCommentCount;
            break;
        case Score:
            updateSQL = UpdateScoreCount;
            break;

        default:
            break;
        }
        long current = System.currentTimeMillis();
        String update = String.format(updateSQL, suffix);
        int updated = 0;

        updated = grower.update(update, value, current, idPhoto);

        return updated;

    }

    // **************************************//
    final static String InsertPhotoAccess = "INSERT INTO photo_access%s(id_photo, id_user, at, cancel, ip, type)"
            + "VALUES (?,?,?,?,?,?)";
    final static String UpdatePhotoAccess = "UPDATE photo_access%s"
            + "SET at = ?, cancel = ?, ip = ? " + "WHERE id_photo=? and id_user=? and type=?";
    final static String CancelPhotoAccess = "UPDATE photo_access%s"
            + "SET   cancel = ? WHERE id_photo=? and id_user=? and type=?";
    final static String SelectPhotoAccess = "SELECT id FROM photo_access%s where id_photo=? and ( id_user=? or ip=?)";

    public static int updatePhotoAccess(ForestGrower grower, String suffix, PhotoAccess m)
        throws SQLException {

        String update = String.format(UpdatePhotoAccess, suffix);
        int updated = grower.update(
            update,
            m.updateAt,
            m.cancel,
            m.ip,
            m.idPhoto,
            m.idUser,
            m.type.getType());
        return updated;

    }

    public static int cancelPhotoAccess(
        ForestGrower grower,
        String suffix,
        long idPhoto,
        int idUser,
        AccessType type) throws SQLException {

        String update = String.format(CancelPhotoAccess, suffix);
        int updated = grower.update(update, Canceled.Yes, idPhoto, idUser, type.getType());
        return updated;

    }

    public static int addPhotoAccess(ForestGrower grower, String suffix, PhotoAccess m)
        throws SQLException {
        Long id = grower.selectOne(
            String.format(SelectPhotoAccess, suffix),
            new LongTransducer(),
            m.idPhoto,
            m.idUser,
            m.ip);
        if (id == null) {
            String insert = String.format(InsertPhotoAccess, suffix);
            int updated = grower.update(
                insert,
                m.idPhoto,
                m.idUser,
                m.updateAt,
                m.cancel,
                m.ip,
                m.type.getType());
            return updated;

        } else {
            return 0;

        }

    }
}
