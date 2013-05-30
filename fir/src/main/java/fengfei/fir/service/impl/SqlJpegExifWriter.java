package fengfei.fir.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;

import fengfei.fir.service.JpegExifWriter;
import fengfei.forest.database.dbutils.impl.ForestRunner.InsertResultSet;
import fengfei.ucm.entity.photo.Photo;
import fengfei.ucm.repository.PhotoRepository;
import fengfei.ucm.repository.impl.SqlPhotoRepository;

public class SqlJpegExifWriter implements JpegExifWriter {

	PhotoRepository repository = new SqlPhotoRepository();

	@Override
	public Map<String, Object> writeExif(String imageFile,
			Map<String, String> values, Map<String, String> contents)
			throws Exception {
		String title = FilenameUtils.getBaseName(imageFile);
		String userName = contents.get("username");
		Photo model = toPhotoModel(values, contents);
		InsertResultSet<Long> rs = repository.saveOne(model, userName);
		Map<String, Object> rv = new HashMap<>();
		rv.put(KeyId, rs.autoPk);
		rv.put(KeyResult, rs.rows);
		return rv;
	}

	public Photo toPhotoModel(Map<String, String> exifs,
			Map<String, String> contents) {
		// System.out.println(exifs);
		// System.out.println(contents);
		int idUser = MapUtils.getIntValue(contents, KeyIdUser);
		String title = MapUtils.getString(contents, "title");
		String description = MapUtils.getString(contents, "desc");
		String category = MapUtils.getString(contents, "category");
		String tags = MapUtils.getString(contents, "tags");
		int adult = MapUtils.getIntValue(contents, "adult");
		int copyright = MapUtils.getIntValue(contents, "copyright");
		//

		String make = MapUtils.getString(exifs, "make");
		String model = MapUtils.getString(exifs, "camera");
		String aperture = MapUtils.getString(exifs, "aperture");
		String shutter = MapUtils.getString(exifs, "shutter");
		String iso = MapUtils.getString(exifs, "iso");
		String lens = MapUtils.getString(exifs, "lens");
		String focus = MapUtils.getString(exifs, "focus");
		String ev = MapUtils.getString(exifs, "ev");
		String dateTimeOriginal = MapUtils.getString(exifs, "taken_at");

		long current = System.currentTimeMillis();
		int createAt = (int) (current / 1000);
		Timestamp createAtGmt = new Timestamp(current);

		long updateAt = current;
		Photo photoModel = new Photo(idUser, title, description,
				category, adult, copyright, tags, createAt, createAtGmt,
				updateAt, -1, make, model, aperture, shutter, iso, lens, focus,
				ev, dateTimeOriginal);
		return photoModel;
	}

}
