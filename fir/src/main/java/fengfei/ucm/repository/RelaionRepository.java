package fengfei.ucm.repository;

import java.util.ArrayList;
import java.util.List;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;
import fengfei.ucm.entity.relation.State;

public interface RelaionRepository extends UnitNames {

    boolean write(long sourceId, long targetId, State state) throws DataAccessException;

    List<Long> findTargets(long sourceId, State state) throws DataAccessException;

    List<Long> findSources(long targetId, State state) throws DataAccessException;

    int computeSourceCount(long targetId, State state) throws DataAccessException;

    int computeTargetCount(long sourceId, State state) throws DataAccessException;

    int countTarget(long sourceId, State state) throws DataAccessException;

    int countSource(long targetId, State state) throws DataAccessException;

    boolean isFollow(final long sourceId, final long targetId, final State state)
        throws DataAccessException;

    int[] count(long sourceId) throws DataAccessException;

    int[] computeCount(long sourceId) throws DataAccessException;
}
