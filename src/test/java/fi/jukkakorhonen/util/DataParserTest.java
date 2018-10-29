package fi.jukkakorhonen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.jukkakorhonen.data.PasswordData;
import fi.jukkakorhonen.util.DataParser;

public class DataParserTest {

	private String jsonData = "[{\"id\":1,\"service\":\"service\",\"username\":\"keke@gmail.com\",\"password\":\"passu1\"},{\"id\":2,\"service\":\"service2\",\"username\":\"keke@gmail.com\",\"password\":\"passu2\"}]";
	
	@Test
	public void parseFromJson_test() {
		List<PasswordData> data = DataParser.parseFromJson(jsonData);
		assertTrue(data != null);
		
		assertEquals(1, data.get(0).getId());
		assertEquals(2, data.get(1).getId());
		
		assertEquals("service", data.get(0).getService());
		assertEquals("service2", data.get(1).getService());
		
		assertEquals("keke@gmail.com", data.get(0).getUsername());
		assertEquals("keke@gmail.com", data.get(1).getUsername());
	
		assertEquals("passu1", data.get(0).getPassword());
		assertEquals("passu2", data.get(1).getPassword());
	}
	
	@Test(expected=Exception.class)
	public void parseFromJsonFail_test() {
		DataParser.parseFromJson("FAILDATA");
	}
	
	@Test
	public void parseToJson_test() {
		PasswordData d = new PasswordData();
		d.setId(1);
		d.setPassword("passu1");
		d.setService("service");
		d.setUsername("keke@gmail.com");
		
		PasswordData d2 = new PasswordData();
		d2.setId(2);
		d2.setPassword("passu2");
		d2.setService("service2");
		d2.setUsername("keke@gmail.com");
		
		List<PasswordData> datalist = new ArrayList<PasswordData>();
		datalist.add(d);
		datalist.add(d2);
		
		String data = DataParser.parseToJson(datalist);
		//System.out.println(data);
		assertTrue(data != null);
		assertEquals("[{\"id\":1,\"service\":\"service\",\"username\":\"keke@gmail.com\",\"password\":\"passu1\"},{\"id\":2,\"service\":\"service2\",\"username\":\"keke@gmail.com\",\"password\":\"passu2\"}]", data);
	}
}
