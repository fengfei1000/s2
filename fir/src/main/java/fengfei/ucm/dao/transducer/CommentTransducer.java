package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.photo.Comment;
import fengfei.ucm.entity.photo.PhotoModel;

public class CommentTransducer implements Transducer<Comment> {

    @Override
    public Comment transform(ResultSet rs) throws SQLException {

        long idPhoto = rs.getLong("id_photo");
        long idComment = rs.getLong("id_comment");
        int idUser = rs.getInt("id_user");
        String content = rs.getString("content");
        String ip = rs.getString("ip");
        int idUserReply = rs.getInt("idUserReply");
        int idParent = rs.getInt("idParent");
        int disabled = rs.getInt("disabled");

        int createAt = rs.getInt("create_at");
        Timestamp createAtGmt = rs.getTimestamp("create_at_gmt");

        //

        Comment comment = new Comment(
            idComment,
            idPhoto,
            idUser,
            content,
            ip,
            createAt,
            createAtGmt,
            disabled,
            idUserReply,
            idParent);
        return comment;
    }
}
