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
import org.springframework.web.servlet.ModelAndView;

import fengfei.fir.rank.LastRank;
import fengfei.fir.rank.PopularRank;
import fengfei.fir.rank.TopRank;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.entity.photo.Photo;
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
    public ModelAndView show(HttpServletRequest request, @PathVariable String id) {
        ModelAndView mv = new ModelAndView("app/photo/show");
        try {
            String ip = request.getRemoteAddr();
            String host = request.getRemoteHost();
            System.out.println(ip);
            int sourceId = currentUserId(request);
            long idPhoto = Long.parseLong(id);
            Photo photo = show.view(idPhoto, sourceId, ip);
            Map<String, String> exif = toMap(photo);
            boolean isFollow = readFollowService.isFollow(null, sourceId, photo.idUser);

            mv.addObject("photo", photo);
            mv.addObject("rank", photo.rank);
            mv.addObject("exif", exif);
            mv.addObject("isFollow", isFollow);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    private Map<String, String> toMap(Photo photo) {
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

    @RequestMapping("/last")
    public ModelAndView last(HttpServletRequest request) {
        String o = request.getParameter("offset");
        String c = request.getParameter("count");
        ModelAndView mv = new ModelAndView("app/photo/last");
        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        Set<Long> ids = new HashSet<>();// .find(offset, count);
        try {
            List<Refresh> refreshs = show.refreshed(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

        } catch ( Exception e) {

            e.printStackTrace();
        }
        return mv;
    }
    
    @RequestMapping("/popular")
    public ModelAndView popular(HttpServletRequest request) {
        String o = request.getParameter("offset");
        String c = request.getParameter("count");
        ModelAndView mv = new ModelAndView("app/photo/popular");
        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        try { 
            Set<String> ids = popular.find(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

        } catch ( Exception e) {

            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping("/top")
    public ModelAndView top(HttpServletRequest request) {
        String o = request.getParameter("offset");
        String c = request.getParameter("count");
        ModelAndView mv = new ModelAndView("app/photo/top");
        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        try { 
            Set<String> ids = top.find(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

        } catch ( Exception e) {

            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping("/random")
    public ModelAndView random(HttpServletRequest request) {
        String o = request.getParameter("offset");
        String c = request.getParameter("count");
        ModelAndView mv = new ModelAndView("app/photo/random");
        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        try { 
//            Set<String> ids = random.find(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

        } catch ( Exception e) {

            e.printStackTrace();
        }
        return mv;
    }
    @RequestMapping("/catalog")
    public ModelAndView catalog(HttpServletRequest request) {
        String o = request.getParameter("offset");
        String c = request.getParameter("count");
        ModelAndView mv = new ModelAndView("app/photo/catalog");
        int offset = o == null || "".equals(o) ? 0 : Integer.parseInt(o);
        int count = c == null || "".equals(c) ? Row : Integer.parseInt(c);

        try { 
//            Set<String> ids = random.find(offset, count);
            // for (Refresh refresh : rfs) {
            // ids.add(refresh.idPhoto);
            // }

        } catch ( Exception e) {

            e.printStackTrace();
        }
        return mv;
    }

 
 
  
}
