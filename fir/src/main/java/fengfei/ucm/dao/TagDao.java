package fengfei.ucm.dao;

import java.sql.SQLException;
import java.util.List;

import fengfei.forest.database.dbutils.ForestGrower;
import fengfei.ucm.dao.transducer.TagTransducer;
import fengfei.ucm.entity.photo.Tag;

public class TagDao {

	public static int saveTags(ForestGrower grower, String suffix,
			long idContent, String type, String... tags) throws SQLException {
		String delete = "DELETE FROM tags WHERE id = ? AND type = ?";
		String insert = "INSERT INTO tags(id, name, type) VALUES (?,?,?)";
		int deleted = grower.update(delete, idContent, type);
		Object[][] params = new Object[tags.length][];
		for (int i = 0; i < tags.length; i++) {
			params[i] = new Object[] { idContent, type, tags[i] };
		}
		int[] inserted = grower.batchUpdate(insert, params);

		return 1;
	}

	public static List<Tag> find(ForestGrower grower, String suffix,
			String name, String type) throws SQLException {
		String sql = "SELECT id_tag, id, name, type FROM tags where name = ? and type=?";
		List<Tag> tags = grower.select(sql, new TagTransducer(), name, type);
		return tags;
	}
}
