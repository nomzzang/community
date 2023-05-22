package com.example.community.Comment.Controller;

import com.example.community.Comment.Model.Comment;
import com.example.community.Comment.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping("/createComment")
  public void createComment(@RequestBody Comment comment){
    commentService.createComment(comment);

  }

}
