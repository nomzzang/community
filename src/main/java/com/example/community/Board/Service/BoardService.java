package com.example.community.Board.Service;


import static com.mongodb.client.model.Filters.eq;

import com.example.community.Board.Model.Board;
import com.example.community.Board.Sequence.BoardSequenceGeneratorService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import java.util.Iterator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class BoardService {

  private static final String COLLECTION = "boardTest";
  private final MongoDatabase mongoDatabase;
  private final BoardSequenceGeneratorService boardSequenceGeneratorService;

  public void addBoard(@RequestBody @Valid Board board) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
    Document document = new Document();

    document.append("_id", boardSequenceGeneratorService.generateSequence(board.getId()));
    document.append("title", board.getTitle());
    document.append("content", board.getContent());
    document.append("boardStatus", board.getBoardStatus());
    document.append("memberId", board.getMemberId());
    document.append("boardRegisterDt", LocalDateTime.now());
    document.append("boardChangeDt", LocalDateTime.now());
    document.append("changeDt", LocalDateTime.now());
    document.append("boardCnt", 0);

    collection.insertOne(document);
  }
  public void findAllBoard() {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    FindIterable<Document> doc = collection.find();

    Iterator itr = doc.iterator();

    while (itr.hasNext()) {
      System.out.println("==> findResultRow : " + itr.next());
    }
  }

  public void findBoard(@RequestBody Board board) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Document doc = collection.find(eq("title", board.getTitle())).first();
    System.out.println("doc = " + doc);
  }
  public void updateBoard(@RequestBody Board board) {

    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Bson query = eq("title", board.getTitle());
    Bson updates = Updates.combine(
        Updates.set("content", board.getContent()),
        Updates.set("changeDt", LocalDateTime.now().minusHours(9)));

    collection.updateOne(query, updates);
    System.out.println("수정이 완료되었습니다. ");
  }
  public void deleteBoard(@RequestBody Board board){
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Bson query = eq("title", board.getTitle());
    collection.deleteOne(query);
    System.out.println(board.getTitle() + "삭제 완료 되었습니다.");
  }
}
