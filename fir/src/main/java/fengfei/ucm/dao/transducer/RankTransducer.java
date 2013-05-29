package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.photo.Rank;

public class RankTransducer implements Transducer<Rank> {

    @Override
    public Rank transform(ResultSet rs) throws SQLException {
        int idUser = rs.getInt("id_user");
        long idPhoto = rs.getInt("id_Photo");
        long updateAt = rs.getLong("update_at");
        int viewCount = rs.getInt("view");
        int voteCount = rs.getInt("vote");
        int favoriteCount = rs.getInt("favorite");
        int commentCount = rs.getInt("comment");
        float score = rs.getFloat("score");

        return new Rank(
            idPhoto,
            idUser,
            viewCount,
            voteCount,
            favoriteCount,
            commentCount,
            score,
            updateAt);
    }

}
