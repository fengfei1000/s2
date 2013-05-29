package fengfei.ucm.dao;

import java.sql.SQLException;
import java.util.List;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.ucm.dao.transducer.CommentTransducer;
import fengfei.ucm.entity.photo.Comment;

public class CommentDao {

    final static String Insert = "INSERT INTO comments%s(id_photo, content, id_user, ip, create_at, "
            + "create_at_gmt, approved, disabled, id_user_reply, id_parent)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";

    public static int addOne(ForestGrower grower, String suffix, Comment m) throws SQLException {
        String insert = String.format(Insert, suffix);
        int updated = grower.update(
            insert,
            m.idPhoto,
            m.idUser,
            m.ip,
            m.createAt,
            m.createAtGmt,
            1,
            1,
            m.idUserReply,
            m.idParent);
        return updated;

    }

    final static String Select = "SELECT id_comment, id_photo, content, id_user, ip, create_at, create_at_gmt, approved, disabled, id_user_reply, id_parent "
            + " FROM comments%s where id_photo=?";

    public static List<Comment> select(
        ForestGrower grower,
        String suffix,
        long idPhoto,
        int offset,
        int row) throws SQLException {
        List<Comment> comments = grower.select(
            Select,
            new CommentTransducer(),
            idPhoto,
            offset,
            row);
        return comments;
    }
}
