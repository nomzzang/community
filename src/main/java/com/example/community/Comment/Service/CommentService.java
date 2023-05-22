package com.example.community.Comment.Service;

import com.example.community.Comment.Model.Comment;
import com.example.community.Comment.Sequence.CommentSequenceGeneratorService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
  private static final String COLLECTION = "commentTest";
  private final MongoDatabase mongoDatabase;
  private final CommentSequenceGeneratorService commentSequenceGeneratorService;

  public void createComment(Comment comment) {
    MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);

    Document doc = new Document()
        .append("_id", commentSequenceGeneratorService.generateSequence(comment.getId()))
        .append("postId", comment.getBoardId())
        .append("userId", comment.getUserId())
        .append("content", comment.getContent());

      collection.insertOne(doc);

  }
}

