package com.b5m.you.aop;

import java.util.HashMap;
import java.util.Map;

public class SayHell {

	Map<String, String> map = new HashMap<String, String>();

	@HelloWorld
	public void sayHello(String name) {
		String data = map.get("cache");
		if (data != null) {
			System.out.println(data);
		} else {
			map.put("cache", "yes is cache");
			System.out.println("no is not cache");
		}
	}
	
	public String name() {
		System.out.println("name：小明");
		return "小明";
	}

	public static void main(String[] args) throws Exception {
		ParseAnnotation parse = new ParseAnnotation();
		parse.parseMethod(SayHell.class);
		parse.parseType(SayHell.class);
	}
}
