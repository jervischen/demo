package com.example.demo.mogodbUtil;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author Chen Xiao
 * @since 2019-09-03 13:02
 */
public class MogoDBMain {
    //数据库名sc_live_amusement_task
    public static void main(String[] args) {
        //mongodb://172.17.6.61:20000
        MongoClient mongoClient = new MongoClient("172.17.6.61", 20000);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        Document document = new Document("name","张三")
                .append("sex", "男")
                .append("age", 18)
                .append("score",100);

        //插入一个文档
        collection.insertOne(document);
        System.out.println(collection.count());
    }
}
