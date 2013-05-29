package fengfei.ucm.entity.photo;

public class Refresh {

	public long idPhoto;
	public String title;
	public long idUser;
	public String userName;
	public int updateAt;

	public Refresh() {
	}

	public Refresh(long idPhoto, String title, long idUser, String userName,
			int updateAt) {
		super();
		this.idPhoto = idPhoto;
		this.title = title;
		this.idUser = idUser;
		this.userName = userName;
		this.updateAt = updateAt;
	}

	public long getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(long idPhoto) {
		this.idPhoto = idPhoto;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public int getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(int updateAt) {
		this.updateAt = updateAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Refresh [idPhoto=" + idPhoto + ", idUser=" + idUser
				+ ", updateAt=" + updateAt + "]";
	}

}
