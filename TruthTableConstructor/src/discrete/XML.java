package discrete;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XML {
	ArrayList<ArrayList<Integer>> databaseInfo;
	String path;

	public XML(ArrayList<ArrayList<Integer>> databaseInfo) {
		this.databaseInfo = databaseInfo;
	}

	public void serializeToXML(String path) throws IOException {
		this.path = path;
		FileOutputStream fos = new FileOutputStream(path + ".xml");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		XMLEncoder encoder = new XMLEncoder(bos);
		encoder.writeObject(databaseInfo);
		encoder.close();
		fos.close();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<Integer>> deserializeFromXML() throws IOException {
		FileInputStream fis = new FileInputStream(path + ".xml");
		BufferedInputStream bos = new BufferedInputStream(fis);
		XMLDecoder decoder = new XMLDecoder(bos);
		ArrayList<ArrayList<Integer>> loadedTable = (ArrayList<ArrayList<Integer>>) decoder.readObject();
		decoder.close();
		fis.close();
		return loadedTable;
	}

}