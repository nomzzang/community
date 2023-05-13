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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


@RestController
@RequiredArgsConstructor
public class MongoRestController {

    private static final String COLLECTION = "members";
    private final MongoDatabase mongoDatabase;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/find")
    public List<Document> find() {
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

        FindIterable<Document> doc = collection.find();
        List<Document> results = new ArrayList<>();

        Iterator<Document> itr = doc.iterator();

        while (itr.hasNext()) {
            Document document = itr.next();
            System.out.println("==> findResultRow : " + document);
            results.add(document);
        }
        return results;
    }

//    @GetMapping("/find-id")
//    public void findById(@RequestParam String name){
//        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
//
//        Document doc = collection.find(eq("name");
//        System.out.println("doc = " + doc);
//    }



    @PostMapping("/insert")
    public void insert(@RequestBody @Valid Member member) {

        memberService.addMember(member);

    }

    @GetMapping("/update")
    public void update(@RequestParam String name, String rename) {

        System.out.println("name = " + name);
        System.out.println("rename = " + rename);

        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

        Bson query = eq("name", name);

        Bson updates = Updates.combine(
                Updates.set("name", rename),
                Updates.set("lastUpdated", LocalDateTime.now().minusHours(9)));
//                Updates.currentTimestamp("lastUpdated"));

        UpdateResult result = collection.updateOne(query, updates);
        System.out.println("UpdateResult : " + result.getModifiedCount());

    }

    @GetMapping("/delete")
    public void delete(@RequestParam String name) {

        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
        System.out.println("name = " + name);
        Bson query = eq("name", name);
        DeleteResult result = collection.deleteOne(query);
        System.out.println("Result = " + result.getDeletedCount());
    }
}

