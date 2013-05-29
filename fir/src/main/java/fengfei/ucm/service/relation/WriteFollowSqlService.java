package fengfei.ucm.service.relation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.entity.relation.State;
import fengfei.ucm.repository.RelaionRepository;
import fengfei.ucm.service.WriteFollowService;

public class WriteFollowSqlService implements WriteFollowService {

	static Logger logger = LoggerFactory.getLogger(WriteFollowSqlService.class);
	private RelaionRepository repository;

	public WriteFollowSqlService(RelaionRepository repository) {
		super();
		this.repository = repository;

	}

	@Override
	public boolean add(ArrayList<Object> results, long sourceId, long targetId)
			throws DataAccessException {
		return repository.write(sourceId, targetId, State.Normal);
	}

	@Override
	public boolean remove(ArrayList<Object> results, long sourceId,
			long targetId) throws DataAccessException {
		return repository.write(sourceId, targetId, State.Removed);
	}

}
