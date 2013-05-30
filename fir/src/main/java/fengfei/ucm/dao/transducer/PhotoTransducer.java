package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.photo.Photo;

public class PhotoTransducer implements Transducer<Photo> {

	@Override
	public Photo transform(ResultSet rs) throws SQLException {

		long idPhoto = rs.getLong("id_photo");
		int idUser = rs.getInt("id_user");
		String title = rs.getString("title");
		String description = rs.getString("desc");
		String category = rs.getString("category");
		int adult = rs.getInt("adult");
		int copyright = rs.getInt("copyright");
		int createAt = rs.getInt("create_at");
		Timestamp createAtGmt = rs.getTimestamp("create_at_gmt");
		long updateAt = rs.getLong("update_at");
		int commentCount = rs.getInt("comment_count");
		//
		String make = rs.getString("make");
		String model = rs.getString("model");
		String aperture = rs.getString("aperture");
		String shutter = rs.getString("shutter");
		String iso = rs.getString("iso");
		String lens = rs.getString("lens");
		String focus = rs.getString("focus");
		String ev = rs.getString("ev");
		String tags = rs.getString("tags");
		String dateTimeOriginal = rs.getString("original_at");
		//

		Photo exifModel = new Photo(idPhoto, idUser, title,
				description, category, adult, copyright, tags, createAt,
				createAtGmt, updateAt, commentCount, make, model, aperture,
				shutter, iso, lens, focus, ev, dateTimeOriginal);
		return exifModel;
	}
}
