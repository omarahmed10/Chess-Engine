package pieces;

import java.util.HashMap;
import java.util.Map;

public class Try {
	public void referenceOrValue(HashMap<String, String> map1) {
		Map<String, String> map2 = new HashMap<String, String>(map1);
		
		map2.put("Hello" , "100");
		
		System.out.println(map1);
		System.out.println(map2);
		
		
		
		System.out.println(map1.get("Hi"));
		System.out.println(map2.get("Hi"));

	}
	
	public static void main(String[] args) {
		HashMap<String, String> map1 = new HashMap<String, String>();
		
		map1.put("Bye", "0");
		map1.put("Hi", "896");
		
		Try t = new Try();
		
		t.referenceOrValue(map1);
	}
}
