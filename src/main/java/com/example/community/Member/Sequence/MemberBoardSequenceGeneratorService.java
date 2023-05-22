package com.example.community.Member.Sequence;

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
public class MemberBoardSequenceGeneratorService {

  private MongoOperations mongoOperations;

  public long generateSequence(String seqName) {
    MemberDatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
    new Update().inc("memberSeq", 1), options().returnNew(true).upsert(true),
        MemberDatabaseSequence.class);

    return !Objects.isNull(counter) ? counter.getMemberSeq() : 1;

  }

}
