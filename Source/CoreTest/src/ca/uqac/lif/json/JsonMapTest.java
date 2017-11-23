package ca.uqac.lif.json;

import org.junit.Test;

public class JsonMapTest {
	@Test
	public void cloneTest()
	{
		JsonMap map1 = new JsonMap();
		
		JsonMap map2 = new JsonMap();
		
		map2.put("astring", "thestring");
		map1.put("amap", map2);
		
		JsonMap map3 = new JsonMap();
		
		//map3 should be a copy of map1
		map3.putAll(map1);
		
		JsonMap map4 = (JsonMap)map3.get("amap");
		
		map4.put("astring", "differentstring");
		
		//Tests that without cloning the first map is also altered
		if(!map2.getString("astring").equals("differentstring"))
		{
			assert false;
		}
		
		JsonMap map5 = map1.clone();
		
		JsonMap map6 = (JsonMap)map5.get("amap");
		map6.put("astring", "anotherstring");
		
		//Tests that the first map isn't altered after the clone
		if(!map2.getString("astring").equals("differentstring") || 
				!map6.getString("astring").equals("anotherstring"))
		{
			assert false;
		}
	}
}
