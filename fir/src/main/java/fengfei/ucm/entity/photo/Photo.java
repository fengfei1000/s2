package fengfei.ucm.entity.photo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    public long idPhoto;
    public int idUser;
    public String title;
    public String description;
    public String category;
    public int adult;
    public int copyright;
    public String tags;
    public int createAt;
    public Timestamp createAtGmt;
    public long updateAt;
    public int commentCount;
    //
    public String make;
    public String model;
    public String aperture;
    public String shutter;
    public String iso;
    public String lens;
    public String focus;
    public String ev;
    public String dateTimeOriginal;
    //
    public Rank rank;
    public String path;

    public Photo() {
    }

    public Photo(
        int idUser,
        String title,
        String description,
        String category,
        int adult,
        int copyright,
        String tags,
        int createAt,
        Timestamp createAtGmt,
        long updateAt,
        int commentCount,
        String make,
        String model,
        String aperture,
        String shutter,
        String iso,
        String lens,
        String focus,
        String ev,
        String dateTimeOriginal) {
        super();
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.category = category;
        this.adult = adult;
        this.copyright = copyright;
        this.tags = tags;
        this.createAt = createAt;
        this.createAtGmt = createAtGmt;
        this.updateAt = updateAt;
        this.commentCount = commentCount;
        this.make = make;
        this.model = model;
        this.aperture = aperture;
        this.shutter = shutter;
        this.iso = iso;
        this.lens = lens;
        this.focus = focus;
        this.ev = ev;
        this.dateTimeOriginal = dateTimeOriginal;
    }

    public Photo(
        long idPhoto,
        int idUser,
        String title,
        String description,
        String category,
        int adult,
        int copyright,
        String tags,
        int createAt,
        Timestamp createAtGmt,
        long updateAt,
        int commentCount,
        String make,
        String model,
        String aperture,
        String shutter,
        String iso,
        String lens,
        String focus,
        String ev,
        String dateTimeOriginal) {
        super();
        this.idPhoto = idPhoto;
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.category = category;
        this.adult = adult;
        this.copyright = copyright;
        this.tags = tags;
        this.createAt = createAt;
        this.createAtGmt = createAtGmt;
        this.updateAt = updateAt;
        this.commentCount = commentCount;
        this.make = make;
        this.model = model;
        this.aperture = aperture;
        this.shutter = shutter;
        this.iso = iso;
        this.lens = lens;
        this.focus = focus;
        this.ev = ev;
        this.dateTimeOriginal = dateTimeOriginal;
    }

    public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public String getShutter() {
        return shutter;
    }

    public void setShutter(String shutter) {
        this.shutter = shutter;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getEv() {
        return ev;
    }

    public void setEv(String ev) {
        this.ev = ev;
    }

    public String getDateTimeOriginal() {
        return dateTimeOriginal;
    }

    public void setDateTimeOriginal(String dateTimeOriginal) {
        this.dateTimeOriginal = dateTimeOriginal;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "PhotoModel [idPhoto=" + idPhoto + ", idUser=" + idUser + ", title=" + title
                + ", description=" + description + ", category=" + category + ", make=" + make
                + ", model=" + model + ", aperture=" + aperture + ", shutter=" + shutter
                + ", iso=" + iso + ", lens=" + lens + ", focus=" + focus + ", ev=" + ev
                + ", dateTimeOriginal=" + dateTimeOriginal + ", tags=" + tags + ", createAt="
                + createAt + ", createAtGmt=" + createAtGmt + ", updateAt=" + updateAt
                + ", commentCount=" + commentCount + "]";
    }

}
