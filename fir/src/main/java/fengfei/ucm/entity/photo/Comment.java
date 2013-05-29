package fengfei.ucm.entity.photo;

import java.sql.Timestamp;

public class Comment {
	public long idComment;
	public long idPhoto;
	public int idUser;
	public String content;
	public String ip;
	public int createAt;
	public Timestamp createAtGmt;
	public int disabled;
	public int idUserReply;
	public long idParent;

	public Comment() {
	}

	public Comment(long idPhoto, int idUser, String content, String ip,
			int createAt, Timestamp createAtGmt, int disabled, int idUserReply,
			long idParent) {
		super();
		this.idPhoto = idPhoto;
		this.idUser = idUser;
		this.content = content;
		this.ip = ip;
		this.createAt = createAt;
		this.createAtGmt = createAtGmt;
		this.disabled = disabled;
		this.idUserReply = idUserReply;
		this.idParent = idParent;
	}

	public Comment(long idComment, long idPhoto, int idUser, String content,
			String ip, int createAt, Timestamp createAtGmt, int disabled,
			int idUserReply, long idParent) {
		super();
		this.idComment = idComment;
		this.idPhoto = idPhoto;
		this.idUser = idUser;
		this.content = content;
		this.ip = ip;
		this.createAt = createAt;
		this.createAtGmt = createAtGmt;
		this.disabled = disabled;
		this.idUserReply = idUserReply;
		this.idParent = idParent;
	}

	public long getIdComment() {
		return idComment;
	}

	public void setIdComment(long idComment) {
		this.idComment = idComment;
	}

	public long getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(long idPhoto) {
		this.idPhoto = idPhoto;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCreateAt() {
		return createAt;
	}

	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}

	public Timestamp getCreateAtGmt() {
		return createAtGmt;
	}

	public void setCreateAtGmt(Timestamp createAtGmt) {
		this.createAtGmt = createAtGmt;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public int getIdUserReply() {
		return idUserReply;
	}

	public void setIdUserReply(int idUserReply) {
		this.idUserReply = idUserReply;
	}

	public long getIdParent() {
		return idParent;
	}

	public void setIdParent(long idParent) {
		this.idParent = idParent;
	}

}
