package fengfei.fir.mongo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class GridFsUtils {

	/**
	 * @param args
	 * @throws MongoException
	 * @throws IOException
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)
			throws MongoException,
			IOException,
			InterruptedException,
			IM4JavaException {
		ConvertCmd cmd = new ConvertCmd(true);
		// or
		Mongo m = new Mongo("localhost", 27017);
		// or, to connect to a replica set, supply a seed list of members
		// Mongo m = new Mongo(Arrays.asList(
		// new ServerAddress("localhost", 27017),
		// new ServerAddress("localhost", 27018),
		// new ServerAddress("localhost", 27019)));
		File imageFile = new File("C:\\Users\\tietang\\Desktop\\3\\37.jpg");
		InputStream in = new FileInputStream(imageFile);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Pipe pipeIn = new Pipe(in, null);
		Pipe pipeOut = new Pipe(null, out);
		int mwoh = 400;
		// create the operation, add images and operators/options

		IMOperation op = new IMOperation();
		op.addImage("-");
		op.resize(mwoh);
		op.addImage("-");

		cmd.setInputProvider(pipeIn);
		cmd.setOutputConsumer(pipeOut);
		cmd.run(op);
		byte[] data = out.toByteArray();
		in.close();
		out.close();
		//
		System.out.println(data.length);
		DB db = m.getDB("image");
		DBCollection coll = db.getCollection("photo1");
		BasicDBObject doc = new BasicDBObject();

		doc.put("name", "img");
		doc.put("data", data);

		coll.insert(doc);
		DBObject myDoc = coll.findOne();

		System.out.println(myDoc);

	}

}
