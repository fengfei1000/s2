package fengfei.ucm.service;

import java.util.ArrayList;
import java.util.List;

import fengfei.ucm.dao.DataAccessException;
import fengfei.ucm.dao.UnitNames;

public interface ReadFollowService extends UnitNames {

    List<Long> findTargets(ArrayList<Object> results, long sourceId) throws DataAccessException;

    int computeTargetCount(ArrayList<Object> results, long sourceId) throws DataAccessException;

    int countTarget(ArrayList<Object> results, long sourceId) throws DataAccessException;

    List<Long> findSources(ArrayList<Object> results, long targetId) throws DataAccessException;

    int computeSourceCount(ArrayList<Object> results, long targetId) throws DataAccessException;

    int countSource(ArrayList<Object> results, long targetId) throws DataAccessException;

    int[] count(ArrayList<Object> results, long sourceId) throws DataAccessException;

    int[] computeCount(ArrayList<Object> results, long sourceId) throws DataAccessException;

    boolean isFollow(ArrayList<Object> results,long sourceId, long targetId) throws DataAccessException;
}
