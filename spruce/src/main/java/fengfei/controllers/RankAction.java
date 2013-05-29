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
import fengfei.ucm.service.PhotoService;
import fengfei.ucm.service.impl.PhotoServiceImpl;

@Controller
public class RankAction extends Admin {
	static Logger logger = LoggerFactory.getLogger(RankAction.class);
	public static PhotoService photoService = new PhotoServiceImpl();

	@RequestMapping("/vote/{id}")
	@ResponseBody
	public Done vote(HttpServletRequest request, @PathVariable String id) {

		try {
			int updated = photoService.vote(Long.parseLong(id));
			return updated > 0 ? new Done(Status.Success) : new Done(
					Status.Fail);
		} catch (NumberFormatException | DataAccessException e) {

			logger.error("vote error for photo id: " + id, e);
			return new Done(Status.Error);
		}

	}
}
