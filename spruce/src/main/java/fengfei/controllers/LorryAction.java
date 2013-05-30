package fengfei.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fengfei.fir.model.UploadDone;
import fengfei.fir.service.JpegExifWriter;
import fengfei.fir.service.LorryStorage;
import fengfei.sprucy.Spruce;
import fengfei.ucm.entity.photo.Photo;

public class LorryAction extends Admin {

    public LorryStorage lorryService = Spruce.getLorryStorage();

    @RequestMapping("/upload")
    public String upload() {
        return "/app/photo/upload";

    }

    @RequestMapping("/upload/done")
    @ResponseBody
    public UploadDone uploadDone(
        HttpServletRequest request,
        @RequestParam("file") MultipartFile file) {
        HttpSession session = request.getSession();
        System.out.println("files length " + file.getSize());

        UploadDone done = new UploadDone(0, "0");
        try {
            Integer idUser = currentUserId(request);
            Map<String, String> contents = new HashMap<>(requestToMap(request));
            String username = (String) session.getAttribute(SESSION_USER_NAME_KEY);
            contents.put("username", username);
            contents.put(JpegExifWriter.KeyIdUser, idUser.toString());
            done = lorryService.writeFile(contents, contents, file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return done;
    }

    Map<String, String> requestToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();
        for (Entry<String, String[]> entry : params.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values.length > 0) {
                map.put(key, values[0]);
            }
        }
        return map;
    }

    @RequestMapping("/upload/done")
    @ResponseBody
    public UploadDone savePhoto(HttpServletRequest request) {

        String[] title = request.getParameterValues("title");
        String[] description = request.getParameterValues("description");
        String[] category = request.getParameterValues("category");
        String[] tags = request.getParameterValues("tags");
        String[] make = request.getParameterValues("make");
        String[] taken_at = request.getParameterValues("taken_at");
        String[] model = request.getParameterValues("camera");
        String[] lens = request.getParameterValues("lens");
        String[] focus = request.getParameterValues("focus");
        String[] shutter = request.getParameterValues("shutter");
        String[] aperture = request.getParameterValues("aperture");
        String[] iso = request.getParameterValues("iso");
        String[] ev = request.getParameterValues("ev");
        String[] dateTimeOriginal = taken_at;// request.getParameterValues("dateTimeOriginal");
        String[] ids = request.getParameterValues("id");

        final List<Photo> models = new ArrayList<>();
        Integer idUser = currentUserId(request);
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
        return null;
    }
}
