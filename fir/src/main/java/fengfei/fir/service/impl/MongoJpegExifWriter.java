package fengfei.fir.service.impl;

import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

import fengfei.fir.service.JpegExifWriter;
import fengfei.fir.service.SpruceConstants;

public class MongoJpegExifWriter implements JpegExifWriter {

	public DB db;

	@Override
	public Map<String, Object>  writeExif(String imageFile, Map<String, String> values,
			Map<String, String> contents) throws Exception {
		String id = FilenameUtils.getBaseName(imageFile);
		DBCollection coll = db
				.getCollection(SpruceConstants.PhotoCollectionName);
		BasicDBObject photo = new BasicDBObject();
		photo.put("_id", Integer.parseInt(id));
		photo.putAll(values);
		WriteResult wr = coll.insert(photo);
		return null;
	}

}
