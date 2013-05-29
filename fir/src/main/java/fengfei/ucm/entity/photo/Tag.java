package fengfei.ucm.entity.photo;

public class Tag {
	public long idTag;
	public long id;
	public String type;
	public String name;

	public Tag(long idTag, long id, String type, String name) {
		super();
		this.idTag = idTag;
		this.id = id;
		this.type = type;
		this.name = name;
	}

	public Tag(long id, String type, String name) {
		super();

		this.id = id;
		this.type = type;
		this.name = name;
	}

	public Tag() {
	}

	public long getIdTag() {
		return idTag;
	}

	public void setIdTag(long idTag) {
		this.idTag = idTag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
