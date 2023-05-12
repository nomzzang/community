package com.example.community;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;


@RestController
@RequiredArgsConstructor
public class MongoRestController {

    private static final String COLLECTION = "members";
    private final MongoDatabase mongoDatabase;

    @GetMapping("/find")
    public void find() {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

        FindIterable<Document> doc = collection.find();

        Iterator itr = doc.iterator();

        while (itr.hasNext()) {
            System.out.println("==> findResultRow : " + itr.next());
        }
    }

    @GetMapping("/insert")
    public void insertOne(){
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

        Document document = new Document();

        document.append("name", "kyu");
        document.append("age", "33");

        InsertOneResult result = collection.insertOne(document);
        System.out.println("result : " + result.getInsertedId());
    }

    @GetMapping("/update")
    public void update(@RequestParam String name) {

        System.out.println("name = " + name);

        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

        Bson query = eq("name", name);

        Bson updates = Updates.combine(
                Updates.set("name", "test1"),
                Updates.currentTimestamp("lastUpdated"));

        UpdateResult result = collection.updateOne(query, updates);
        System.out.println("UpdateResult : " + result.getModifiedCount());

    }

    @GetMapping("/delete")
    public void delete(@RequestParam String name){

        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
        System.out.println("name = " + name);
        Bson query = eq("name", name);

        DeleteResult result = collection.deleteOne(query);
        System.out.println("Result = " + result.getDeletedCount());
    }
}

