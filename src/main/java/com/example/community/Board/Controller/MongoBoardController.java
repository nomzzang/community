package com.example.community.Board.Controller;

import static com.mongodb.client.model.Filters.eq;

import com.example.community.Board.Model.Board;
import com.example.community.Board.Sequence.BoardSequenceGeneratorService;
import com.example.community.Board.Service.BoardService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MongoBoardController {

  private static final String COLLECTION = "boardTest";
  private final MongoDatabase mongoDatabase;
  private final BoardSequenceGeneratorService boardSequenceGeneratorService;
  private final BoardService boardService;


  @GetMapping("/addBoard")
  public void addBoard(@RequestBody @Valid Board board) {

    boardService.addBoard(board);
  }


  @GetMapping("/findAllBoard")
  public void findAllBoard() {

    boardService.findAllBoard();
  }

  @GetMapping("/findBoard")
  public void findBoard(@RequestBody Board board) {

    boardService.findBoard(board);
  }

  @GetMapping("/updateBoard")
  public void updateBoard(@RequestBody Board board) {

    boardService.updateBoard(board);
  }

  @GetMapping("/deleteBoard")
  public void deleteBoard(@RequestBody Board board) {
    boardService.deleteBoard(board);
  }



}

