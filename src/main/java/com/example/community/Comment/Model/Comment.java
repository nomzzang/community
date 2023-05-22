package com.example.community.Comment.Model;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "commentTest")
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME = "comment_sequence";

    @Id
    private String id;

    // 게시물에 들어갈것
    private String boardId;
    private String userId;


    @NotBlank(message = "제목를 입력해 주세요")
    private String content;

}
