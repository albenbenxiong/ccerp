package com.ccerp.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtils{
	private static Gson gson;
	static{
		gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	}
	public static  String objectToJson(Object obj){
		return gson.toJson(obj);
	}
	public static  <T> T  jsonToObject(String json,Class<T> cls){
		return  gson.fromJson(json,cls);
	}
	public static  <T>List<T>   jsonToObjectList(String json,Class<T> cls){
		return  gson.fromJson(json,new TypeToken<List<T>>(){}.getType());
	}
	@SuppressWarnings("unchecked")
	public static  Map<String, String>  jsonToMap(String json){
		return  gson.fromJson(json,Map.class);
	}
	
	
}
