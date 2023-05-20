package com.example.community.Board.Sequence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Data
public class BoardDatabaseSequence {

  @Id
  private String id;

  private long seq;

}
