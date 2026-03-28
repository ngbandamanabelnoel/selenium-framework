package SeleniumLearning.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataMap(String pathFile) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + pathFile),
				StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> dataMap = new HashMap<String, String>();
//		dataMap.put("email", "abelngbandaman@gmail.com");
//		dataMap.put("password", "Mba@2026");
//		dataMap.put("productName", "iphone 13 pro");
//		dataMap.put("countryName", "india");
//
//		HashMap<String, String> dataMap1 = new HashMap<String, String>();
//		dataMap1.put("email", "abelngbandaman1@gmail.com");
//		dataMap1.put("password", "Mba@2026");
//		dataMap1.put("productName", "iphone 13 pro");
//		dataMap1.put("countryName", "india");
//
//		return new Object[][] { { dataMap }, { dataMap1 } };
//	}
}
