package com.example.community.Comment.Sequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentSequenceGeneratorService {

  private MongoOperations mongoOperations;

  public long generateSequence(String seqName) {
    CommentDatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
    new Update().inc("CommentSeq", 1), options().returnNew(true).upsert(true),
        CommentDatabaseSequence.class);

    return !Objects.isNull(counter) ? counter.getCommentSeq() : 1;

  }

}
