package api.commons;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ConstructPayload {

	public String constructJSON(String filePath) throws IOException {
		// creating a file object to read a template file
		File file = new File(System.getProperty("user.dir") + File.separator + "src//main//resources//data//appName"
				+ File.separator + filePath);
		String requestbody = FileUtils.readFileToString(file, "UTF-8").toString();
		return requestbody;
	}
}
