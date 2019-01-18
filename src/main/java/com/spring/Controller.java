package com.spring;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Slf4j
@RestController
public class Controller
{
	@Autowired
	private Customer customer;

//	private Controller controller;
	public void setController(Customer con)
	{
		this.customer=con;
	}
	@GetMapping("/fields")
	public String fields(){
		return customer.getId()+""+customer.getName()+""+customer.getAge();
	}



	//loading YAML properties through value annotation
	@Value("${mongodb.databasename:#{null}}")
	private String DATABASE;
	@Value("${mongodb.collectionname:#{null}}")
	private String COLLECTION;

	//Insering data
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<String> insert(@RequestBody Customer cust)
	{

//		Logger logger;

		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(DATABASE);
		MongoCollection<Document> collection = database.getCollection(COLLECTION);
		String id = cust.getId();
		String name = cust.getName();
		String age = cust.getAge();
		Document doc=new Document();
		doc.put("id", id);
		doc.put("name", name);
		doc.put("age", age);
		collection.insertOne(doc);
		log.info("Inserting values into db---------");
		log.debug("request{} ",id);
		log.debug("request{} ",name);
		log.debug("request{} ",age);
		String response="+id";
		log.debug("response{}",response);
//		String response=" hi " +name+ " welcome to jbhunt project";
//
//		log.debug("response{}", response);



		return new ResponseEntity<>("details inserted sucessfully", HttpStatus.OK);
	}
	
	//Retrieving data 
	
	@GetMapping(value="/get/{name}/")
	@ResponseBody
	//@ApiResponse(code = 200, message = "OK", response = Customer.class)
	public ResponseEntity<Customer> get(@PathVariable String name)
	{
		System.out.println("DATABASE==="+DATABASE);
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(DATABASE);
		MongoCollection<Document> collection = database.getCollection(COLLECTION);
		
		Document doc=new Document("name",name);
		FindIterable<Document> cursor=collection.find(doc);
		MongoCursor<Document> mongocursor=cursor.iterator();
		
		Document query=new Document();
		while(mongocursor.hasNext())
		{
			query=mongocursor.next();
			customer.setId(query.get("_id").toString());
			customer.setName(query.get("name").toString());
			customer.setAge(query.get("age").toString());
		}
		log.info("Inserting values into db---------");

		log.debug("request{} ",name);

		String response="+name";
		log.debug("response{}",response);
		mongocursor.close();
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);			
	}
	
	//Deleting data
	@DeleteMapping(value = "/delete/{name}")
@ResponseBody
	public ResponseEntity<String> delete(@PathVariable String name)
	{

		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(DATABASE);
		MongoCollection<Document> collection = database.getCollection(COLLECTION);
		
		Document doc=new Document("name",name);
		FindIterable<Document> cursor=collection.find(doc);
		MongoCursor<Document> mongocursor=cursor.iterator();

		Document query=new Document();
		while(mongocursor.hasNext())
		{
			query=mongocursor.next();
			collection.deleteOne(query);
		
		}
		customer.setId(name);

		return new ResponseEntity<>("Deleted sucessfully", HttpStatus.OK);			
	
	
}
	
	//Retrieving all data

	@GetMapping(value = "/showAll")
	@ResponseBody
	public ResponseEntity<Customer> getall()
	{
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(DATABASE);
		MongoCollection<Document> collection = database.getCollection(COLLECTION);
		
		//Document doc=new Document("name",name);
		FindIterable<Document> cursor=collection.find();
		MongoCursor<Document> mongocursor=cursor.iterator();
		
		List<Document> doc1 = new ArrayList<Document>();
		Document docu = new Document();
		
		while (mongocursor.hasNext())
		{
		docu = mongocursor.next();
		doc1.add(docu);
		}
		customer.setData(doc1);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);			
	}
	
}
