package fengfei.web.app.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fengfei.controllers.FollowController;
import fengfei.controllers.ShowController;
import fengfei.ucm.registry.ChainExecuteProxy;
import fengfei.ucm.registry.ChainExecuteType;
import fengfei.ucm.repository.RelaionRepository;
import fengfei.ucm.repository.impl.SqlRelaionRepository;
import fengfei.ucm.service.ReadFollowService;
import fengfei.ucm.service.WriteFollowService;
import fengfei.ucm.service.relation.ReadFollowSqlService;
import fengfei.ucm.service.relation.WriteFollowSqlService;

public class AppInitPlugin {

	static Logger logger = LoggerFactory.getLogger(AppInitPlugin.class);

	public void onApplicationStart() {
		initFollowService();
	}

	private void initFollowService() {

		RelaionRepository repository = new SqlRelaionRepository();
		//
		ChainExecuteProxy<WriteFollowService> writeChainExecuteProxy = new ChainExecuteProxy<>(
				WriteFollowService.class);

		writeChainExecuteProxy.register(1,
				new WriteFollowSqlService(repository),
				ChainExecuteType.NonReturnValue);
		WriteFollowService write = writeChainExecuteProxy.newInstance();
		//
		ChainExecuteProxy<ReadFollowService> readChainExecuteProxy = new ChainExecuteProxy<>(
				ReadFollowService.class);
		readChainExecuteProxy.register(1, new ReadFollowSqlService(repository),
				ChainExecuteType.ReturnValue);
		ReadFollowService read = readChainExecuteProxy.newInstance();

		//
		FollowController.read = read;
		FollowController.write = write;
		ShowController.readFollowService = read;

	}
}
