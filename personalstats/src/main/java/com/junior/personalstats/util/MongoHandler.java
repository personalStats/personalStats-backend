package com.junior.personalstats.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoHandler {

	private MongoDatabase database;

	public MongoCollection<Document> getCollection(String name) {
		return database.getCollection(name);
	}

	protected MongoClient createMongoConnection() {
		return new MongoClient("localhost", 27017);
	}

	protected MongoTemplate createMongoTemplate() {
		return new MongoTemplate(createMongoConnection(), "personalStats");
	}
}
