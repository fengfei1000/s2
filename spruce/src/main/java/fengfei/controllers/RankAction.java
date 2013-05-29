package fengfei.controllers;

import play.mvc.With;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.registry.ChainExecuteProxy;
import fengfei.ucm.registry.ChainExecuteType;
import fengfei.ucm.repository.PhotoRepository;
import fengfei.ucm.repository.RelaionRepository;
import fengfei.ucm.repository.impl.SqlPhotoRepository;
import fengfei.ucm.repository.impl.SqlRelaionRepository;
import fengfei.ucm.service.PhotoService;
import fengfei.ucm.service.ReadFollowService;
import fengfei.ucm.service.WriteFollowService;
import fengfei.ucm.service.impl.PhotoServiceImpl;
import fengfei.ucm.service.relation.ReadFollowSqlService;
import fengfei.ucm.service.relation.WriteFollowSqlService;

@With(Secure.class)
public class RankAction extends Admin {

    static PhotoService photoService = new PhotoServiceImpl();

    public static void vote(String id) {
        long sourceId = currentUserId();
        try {
            int updated = photoService.vote(Long.parseLong(id));
        } catch (NumberFormatException | DataAccessException e) {
            e.printStackTrace();
            renderJSON("{success:false}");
        }

    }
}
