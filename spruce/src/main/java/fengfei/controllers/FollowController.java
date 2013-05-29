package fengfei.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fengfei.models.Done;
import fengfei.models.Done.Status;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.service.ReadFollowService;
import fengfei.ucm.service.WriteFollowService;

@Controller
public class FollowController extends Admin {

    static Logger logger = LoggerFactory.getLogger(FollowController.class);
    public static WriteFollowService write = null;
    public static ReadFollowService read = null;

    @RequestMapping("/follow/{toid}")
    @ResponseBody
    public Done follow(HttpServletRequest request, @PathVariable String toid) {
        long sourceId =19;// currentUserId(request);
        try {
            boolean followed = write.add(null, sourceId, Long.parseLong(toid));
            return new Done(Status.Success);
        } catch (NumberFormatException | DataAccessException e) {
            e.printStackTrace();
            logger.error("follow error.", e);
            return new Done(Status.Error);
        }

    }

    @RequestMapping("/unfollow/{toid}")
    @ResponseBody
    public Done unfollow(HttpServletRequest request, String toid) {
        long sourceId = currentUserId(request);
        try {
            boolean followed = write.remove(null, sourceId, Long.parseLong(toid));
            return new Done(Status.Success);
        } catch (NumberFormatException | DataAccessException e) {
            logger.error("unfollow error.", e);
            return new Done(Status.Error);
        }

    }

    @RequestMapping("/follows/count")
    @ResponseBody
    public Done getFollow(HttpServletRequest request) {
        long sourceId = currentUserId(request);
        try {
            int follow[] = read.count(null, sourceId);
            Done done = new Done(Status.Success);
            done.put("following", follow[0]);
            done.put("followed", follow[1]);
            return done;
        } catch (NumberFormatException | DataAccessException e) {
            logger.error("follow count error.", e);
            Done done = new Done(Status.Error);
            done.put("following", 0);
            done.put("followed", 0);
            return done;
        }
    }

}
