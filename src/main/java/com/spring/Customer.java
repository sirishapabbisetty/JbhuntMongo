package com.spring;

import java.io.Serializable;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Customer implements Serializable
{
	@Id
	private String id;
	private String name;
	private String age;
	List<Document> data;
	public List<Document> getData() 
	{ 
				return data; 
	} 
		 
	public void setData(List<Document> data)
	{ 
		 		this.data = data; 
	} 

	public String getId()
	{
		return id;
	}
	public void setId(String id) {

		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) {

		this.name = name;
	}
	public String getAge() {

		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	
}
