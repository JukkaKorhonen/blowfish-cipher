package fi.jukkakorhonen.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import fi.jukkakorhonen.data.PasswordData;

public class DataParser {
	
	/**
	 * Parse from JSON data to List<PasswordData>
	 * syntax expected
	 * @param string
	 * @return List<PasswordData>
	 */
	public static List<PasswordData> parseFromJson(String jsondata) {
		Gson gson = new Gson();
		PasswordData[] data = gson.fromJson(jsondata, PasswordData[].class);
		List<PasswordData> list = new ArrayList<PasswordData>();
		if (data != null) {
			list = Arrays.asList(data);
		}
		return list;
	}
	
	/**
	 * Parse from List<PasswordData> to JSON
	 * @param datalist List<PasswordData>
	 * @return JSON data
	 */
	public static String parseToJson(List<PasswordData> datalist) {
		return new Gson().toJson(datalist);
	}

}
