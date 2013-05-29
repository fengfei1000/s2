package fengfei.ucm.service.relation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.forest.slice.SliceResource.Function;
import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.Transactions;
import fengfei.ucm.dao.Transactions.TaCallback;
import fengfei.ucm.entity.relation.State;
import fengfei.ucm.repository.RelaionRepository;
import fengfei.ucm.service.ReadFollowService;

public class ReadFollowSqlService implements ReadFollowService {

    static Logger logger = LoggerFactory.getLogger(ReadFollowSqlService.class);
    private RelaionRepository repository;

    public ReadFollowSqlService(RelaionRepository repository) {
        super();
        this.repository = repository;

    }

    @Override
    public List<Long> findTargets(ArrayList<Object> results, long sourceId)
        throws DataAccessException {
        return repository.findTargets(sourceId, State.Normal);
    }

    @Override
    public int computeTargetCount(ArrayList<Object> results, long sourceId)
        throws DataAccessException {
        return repository.computeTargetCount(sourceId, State.Normal);
    }

    @Override
    public int countTarget(ArrayList<Object> results, long sourceId) throws DataAccessException {
        return repository.countTarget(sourceId, State.Normal);
    }

    @Override
    public List<Long> findSources(ArrayList<Object> results, long targetId)
        throws DataAccessException {
        return repository.findSources(targetId, State.Normal);
    }

    @Override
    public int computeSourceCount(ArrayList<Object> results, long targetId)
        throws DataAccessException {
        return repository.computeSourceCount(targetId, State.Normal);
    }

    @Override
    public int countSource(ArrayList<Object> results, long targetId) throws DataAccessException {
        return repository.countSource(targetId, State.Normal);
    }

    @Override
    public int[] count(ArrayList<Object> results, long sourceId) throws DataAccessException {
        return repository.count(sourceId);

    }

    @Override
    public int[] computeCount(ArrayList<Object> results, long sourceId) throws DataAccessException {
        return repository.computeCount(sourceId);
    }

    @Override
    public boolean isFollow(ArrayList<Object> results,long sourceId, long targetId) throws DataAccessException {
        return repository.isFollow(sourceId, targetId, State.Normal);
    }

}
