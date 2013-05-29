package fengfei.ucm.entity.photo;

public class Rank {
	public long idPhoto;
	public int idUser;
	public int view;
	public int vote;
	public int favorite;
	public int comment;
	public float score;
	public long updateAt;

	public Rank() {
	}

	public Rank(long idPhoto, int idUser, int view, int vote,
			int favorite, int comment, float score, long updateAt) {
		super();
		this.idPhoto = idPhoto;
		this.idUser = idUser;
		this.view = view;
		this.vote = vote;
		this.favorite = favorite;
		this.comment = comment;
		this.score = score;
		this.updateAt = updateAt;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public long getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(long idPhoto) {
		this.idPhoto = idPhoto;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(long updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Rank [idPhoto=" + idPhoto + ", view=" + view
				+ ", vote=" + vote + ", favorite="
				+ favorite + ", comment=" + comment + ", score="
				+ score + ", updateAt=" + updateAt + "]";
	}

}
