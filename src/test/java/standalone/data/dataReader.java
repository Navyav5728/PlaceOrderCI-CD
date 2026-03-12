package standalone.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class dataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//read Json to string
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//standalone//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		//to convert json content to hasmap we need jackson binding dependency
		//String to hasmap
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		
	});
	return data;
		
	}

}
