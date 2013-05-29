package fengfei.controllers;

import japidviews.Application.photo.Upload;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.mvc.With;
import cn.bran.play.JapidResult;
import fengfei.fir.model.UploadDone;
import fengfei.fir.service.JpegExifWriter;
import fengfei.fir.service.LorryStorage;
import fengfei.sprucy.Spruce;
import fengfei.ucm.entity.photo.PhotoModel;

@With(Secure.class)
public class LorryAction extends Admin {
	public static LorryStorage lorryService = Spruce.getLorryStorage();

	public static void upload() {
		throw new JapidResult(new Upload().render());
	}

	public static void uploadDone(File file) {

		System.out.println("files length " + file.length());

		// System.out.println(new HashMap<>(params.allSimple()));
		System.out.println(params.allSimple());
		UploadDone done;
		try {
			Integer idUser = currentUserId();
			Map<String, String> contents = new HashMap<>(params.allSimple());
			String username=session.get(SESSION_USER_NAME_KEY);
			contents.put("username", username);
			contents.put(JpegExifWriter.KeyIdUser, idUser.toString());
			done = lorryService.writeFile(contents, contents, file);
			renderJSON(done);
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new JapidResult(new Upload().render());
	}

	public static void savePhoto() {

		String[] title = params.getAll("title");
		String[] description = params.getAll("description");
		String[] category = params.getAll("category");
		String[] tags = params.getAll("tags");
		String[] make = params.getAll("make");
		String[] taken_at = params.getAll("taken_at");
		String[] model = params.getAll("camera");
		String[] lens = params.getAll("lens");
		String[] focus = params.getAll("focus");
		String[] shutter = params.getAll("shutter");
		String[] aperture = params.getAll("aperture");
		String[] iso = params.getAll("iso");
		String[] ev = params.getAll("ev");
		String[] dateTimeOriginal = taken_at;// params.getAll("dateTimeOriginal");
		String[] ids = params.getAll("id");

		final List<PhotoModel> models = new ArrayList<>();
		Integer idUser = currentUserId();
		long currentTime = System.currentTimeMillis();
		int createAt = (int) currentTime / 1000;
		Date createAtGmt = new Date(currentTime);
		long updateAt = currentTime;

		for (int i = 0; i < ids.length; i++) {

			// models.add(new PhotoModel(idUser, title[i], description[i],
			// category[i], make[i], model[i], aperture[i], shutter[i],
			// iso[i], lens[i], focus[i], ev[i], dateTimeOriginal[i],
			// tags[i], createAt, createAtGmt, updateAt, -1));

		}

		// writeExif(values, jpegFile2);
		System.out.println("==================");
		// System.out.println(new HashMap<>(params.allSimple()));
		System.out.println(params.allSimple());

		// List<InsertResultSet<Long>> irs = Transactions
		// .execute(new TransactionCallback<List<InsertResultSet<Long>>>() {
		//
		// @Override
		// public List<ForestRunner.InsertResultSet<Long>> execute(
		// ForestGrower grower) throws SQLException {
		// return ExifDao.save(grower, "", models);
		// }
		// });
		// System.out.println("updated: size=" + irs.size());

	}
}
