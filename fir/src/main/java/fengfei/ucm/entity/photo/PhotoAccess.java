package fengfei.ucm.entity.photo;

public class PhotoAccess {

    /**
     * 访问类型1=view,2=vote,3=favorite
     * 
     * @author
     * 
     */
    public static enum AccessType {
            View(1),
            Vote(2),
            Favorite(3),
            Comment(4),
            Score(5);

        private int type;

        private AccessType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }

    public PhotoAccess() {
    }

    public PhotoAccess(
        long id,
        long idPhoto,
        long idUser,
        int updateAt,
        int cancel,
        String ip,
        AccessType type) {
        super();
        this.id = id;
        this.idPhoto = idPhoto;
        this.idUser = idUser;
        this.updateAt = updateAt;
        this.cancel = cancel;
        this.ip = ip;
        this.type = type;
    }

    public PhotoAccess(
        long idPhoto,
        long idUser,
        int updateAt,
        int cancel,
        String ip,
        AccessType type) {
        super();
        this.idPhoto = idPhoto;
        this.idUser = idUser;
        this.updateAt = updateAt;
        this.cancel = cancel;
        this.ip = ip;
        this.type = type;
    }

    public long id;
    public long idPhoto;
    public long idUser;
    public int updateAt;
    public int cancel = 0;
    public String ip;
    public AccessType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccessType getType() {
        return type;
    }

    public void setType(AccessType type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        return "Vote [idPhoto=" + idPhoto + ", idUser=" + idUser + ", updateAt=" + updateAt
                + ", cancel=" + cancel + "]";
    }

}
