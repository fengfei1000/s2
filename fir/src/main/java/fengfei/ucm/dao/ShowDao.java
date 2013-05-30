package fengfei.ucm.dao;

import java.sql.SQLException;
import java.util.List;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.ucm.dao.transducer.PhotoTransducer;
import fengfei.ucm.dao.transducer.RefreshTransducer;
import fengfei.ucm.entity.photo.Photo;
import fengfei.ucm.entity.photo.Refresh;

public class ShowDao {
	final static String SelectRefresh = "SELECT id_photo,title, id_user,user_name, at FROM refresh%s order by at desc limit ?,?";
	final static String SelectRank = "SELECT id_photo, view, vote, favorite, comment, score, update_at FROM rank";
	final static String GetPhoto = "SELECT id_photo, id_user, title, `desc`, category, create_at, create_at_gmt, update_at, comment_count, adult, copyright, tags, make, model, lens, aperture, shutter, iso, focus, ev, original_at FROM photos%s where id_photo=?";

	public static List<Refresh> pageRefreshPhoto(ForestGrower grower,
			String suffix, int offset, int row) throws SQLException {

		List<Refresh> refreshes = grower.select(
				String.format(SelectRefresh, suffix), new RefreshTransducer(),
				offset, row);
		return refreshes;
	}

	public static Photo getPhoto(ForestGrower grower, String suffix,
			long idPhoto) throws SQLException {

		Photo photo = grower.selectOne(String.format(GetPhoto, suffix),
				new PhotoTransducer(), idPhoto);
		return photo;
	}
}
