package com.example.community;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private static final String COLLECTION = "test";
  private final MongoDatabase mongoDatabase;

  public void notFoundUserId(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
    Document checkUserId = new Document("userId", member.getUserId());
    Document checkUserIdResult = collection.find(checkUserId).first();
    if (checkUserIdResult == null) {
      throw new IllegalStateException("존재하지않는 아이디입니다.");
    }
  }

  public void checkUserId(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
    Document checkUserId = new Document("userId", member.getUserId());
    Document checkUserIdResult = collection.find(checkUserId).first();
    if (checkUserIdResult != null) {
      throw new IllegalStateException("가입된 아이디입니다.");
    }
  }

  public void checkEmail(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
    Document checkEmail = new Document("email", member.getEmail());
    Document checkEmailResult = collection.find(checkEmail).first();
    if (checkEmailResult != null) {
      throw new IllegalStateException("가입된 이메일입니다.");
    }
  }

  //Document 를 이용한 회원 등록
  public void addMember(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
    Document document = new Document();

    checkUserId(member);
    checkEmail(member);

    Document checkEmail = new Document("email", member.getEmail());
    Document checkEmailResult = collection.find(checkEmail).first();
    if (checkEmailResult != null) {
      throw new IllegalStateException("가입된 이메일입니다.");
    }

    document.append("userId", member.getUserId());
    document.append("password", member.getPassword());
    document.append("email", member.getEmail());
    document.append("status", member.getStatus());
    document.append("userInformationYn", member.getUserInformationYn());
    document.append("userServiceYn", member.getUserServiceYn());
    document.append("registerDt", LocalDateTime.now());
    document.append("changeDt", LocalDateTime.now());
    document.append("postCnt", 0);

    collection.insertOne(document);
//        System.out.println("result : " + result.getInsertedId());
  }

  public void findAllMember(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    FindIterable<Document> doc = collection.find();

    Iterator itr = doc.iterator();

    while (itr.hasNext()) {
      System.out.println("==> findResultRow : " + itr.next());
    }
  }

  public void findMemberId(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Document doc = collection.find(eq("userId", member.getUserId())).first();
    System.out.println("doc = " + doc);
  }

  public void update(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Bson query = eq("userId", member.getUserId());
    Bson updates = Updates.combine(
        Updates.set("password", member.getPassword()),
        Updates.set("changeDt", LocalDateTime.now().minusHours(9)));

    collection.updateOne(query, updates);
    System.out.println("수정이 완료되었습니다. ");

  }

  public void delete(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Bson query = eq("userId", member.getUserId());
    collection.deleteOne(query);
    System.out.println(member.getUserId() + "삭제 완료 되었습니다.");
  }

  public void login(Member member) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    notFoundUserId(member);

    Bson filter = eq("userId", member.getUserId());
    Document result = collection.find(filter).first();
    String password = result.getString("password");
    if (password.equals(member.getPassword())) {
      System.out.println("로그인이 완료되었습니다.");
    } else {
      throw new IllegalStateException("비밀번호가 일치하지 않습니다. ");
    }
  }
}
