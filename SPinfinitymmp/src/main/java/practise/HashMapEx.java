package practise;

import java.util.HashMap;
import java.util.Set;

public class HashMapEx {

	public static void main(String[] args) {
		// we are using hashmap in this example to store string key and string value so data type is String(name), String(James). so we first declare it like HashMap < String, String>
		
		HashMap <String, String> hMap = new HashMap<String, String>();// all class/collections is available in java.util so we need to add import statement if class were available in java.lang we neeedn't import
		hMap.put("name", "James");// key is name and value is james in stockdetails key will be stockname and value will name of stock
		hMap.put("address","New York");// for storing in hashmap it is put
		
		System.out.println(hMap.get("address"));// for fetching data from hash map it is get
		
		Set<String> keys = hMap.keySet();
		System.out.println("Printing the values from HashMap");
		for(String s:keys)
		{
			System.out.println("Key :::" + s);
			System.out.println("Value :::" + hMap.get(s));
		}
		

	}

}
