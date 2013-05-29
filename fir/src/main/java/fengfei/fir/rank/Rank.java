package fengfei.fir.rank;

import java.util.Set;

public interface Rank {

	public abstract void add(String id);

	public abstract Set<String> find(int offset, int count);

	public abstract long removeExpired();

}