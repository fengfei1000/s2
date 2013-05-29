package fengfei.ucm.dao.transducer;

import java.sql.ResultSet;
import java.sql.SQLException;

import fengfei.forest.database.dbutils.Transducer;
import fengfei.ucm.entity.profile.CameraModel;

/**
 * 
 * @author tietang
 * 
 */
public class CameraTransducer implements Transducer<CameraModel> {

	@Override
	public CameraModel transform(ResultSet rs) throws SQLException {
		int idUser = rs.getInt("id_user");
		long idCamera = rs.getLong("id_camera");
		String equipment = rs.getString("equipment");
		String type = rs.getString("type");
		return new CameraModel(idUser, idCamera, equipment, type);
	}

}
