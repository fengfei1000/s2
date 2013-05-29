package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.photo.Tag;

public class TagTransducer implements Transducer<Tag> {

	@Override
	public Tag transform(ResultSet rs) throws SQLException {
		long idTag = rs.getLong("id_tag");
		long id = rs.getLong("id");
		String type = rs.getString("type");
		String name = rs.getString("name");
		return new Tag(idTag, id, type, name);

	}
}
