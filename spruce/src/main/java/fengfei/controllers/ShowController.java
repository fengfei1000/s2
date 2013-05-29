package fengfei.controllers;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fengfei.fir.rank.LastRank;
import fengfei.fir.rank.PopularRank;
import fengfei.fir.rank.TopRank;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.entity.photo.PhotoModel;
import fengfei.ucm.entity.photo.Refresh;
import fengfei.ucm.service.ReadFollowService;
import fengfei.ucm.service.ShowService;
import fengfei.ucm.service.impl.ShowServiceImpl;

@Controller
public class ShowController extends Admin {

    public final int Row = 20;
    public static ShowService show = new ShowServiceImpl();
    public static ReadFollowService readFollowService = null;
    public static LastRank last = new LastRank();
    public static PopularRank popular = new PopularRank();
    public static TopRank top = new TopRank();

    @RequestMapping("/show/{id}")
    public void show(HttpServletRequest request, @PathVariable String id) {

        try {
            String ip = request.getRemoteAddr();
            String host = request.getRemoteHost();
            System.out.println(ip);
            int sourceId = currentUserId(request);
            long idPhoto = Long.parseLong(id);
            PhotoModel photo = show.view(idPhoto, sourceId, ip);
            Map<String, String> exif = toMap(photo);
            boolean isFollow = readFollowService.isFollow(null, sourceId, photo.idUser);
            throw new JapidResult(new Show().render(photo, photo.rank, exif, isFollow));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> toMap(PhotoModel photo) {
        Map<String, String> exif = new LinkedHashMap<String, String>();
        put(exif, "Make", photo.make);
        put(exif, "Camera", photo.model);
        put(exif, "Aperture", photo.aperture);
        put(exif, "Shutter Speed", photo.shutter);
        put(exif, "Focal Length", photo.focus);
        put(exif, "ISO", photo.iso);
        put(exif, "Lens", photo.lens);
        put(exif, "Ev", photo.ev);
        put(exif, "Taken at", photo.dateTimeOriginal);

        return exif;
    }

    private void put(Map<String, String> exif, String key, String value) {
        if (value != null && !"".equals(value)) {
            exif.put(key, value);
        }

    }

    public void showUser(String id) {
        throw new JapidResult(new Index().render());
    }

    public void last(String id) {
        String o = params.get("offset");
        String c = params.get("count");

        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        Set<Long> ids = new HashSet<>();// .find(offset, count);
        try {
            List<Refresh> refreshs = show.refreshed(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

            throw new JapidResult(new Views().render(refreshs));
        } catch (DataAccessException e) {

            e.printStackTrace();
            throw new JapidResult(new Index().render());
        }

    }

    public void popular(String id) {
        String o = params.get("offset");
        String c = params.get("count");

        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? 0 : Integer.parseInt(c);
        Set<String> ids = popular.find(offset, count);
        flash.put("ids", ids);
        throw new JapidResult(new Index().render());
    }

    public void top(String id) {
        String o = params.get("offset");
        String c = params.get("count");

        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? 0 : Integer.parseInt(c);
        Set<String> ids = top.find(offset, count);
        flash.put("ids", ids);
        throw new JapidResult(new Index().render());
    }

    public void random(String id) {
        throw new JapidResult(new Index().render());
    }

    public void catalog(String id) {
        throw new JapidResult(new Index().render());
    }
}
