package com.example.community.Board.Model;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "boardTest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EnableAutoConfiguration
public class Board {
  @Transient
  public static final String SEQUENCE_NAME = "board_sequence";

  @Id
  private String id;
  private Long idx;

  @NotBlank(message = "제목를 입력해 주세요")
  private String title;

  @NotBlank(message = "내용를 입력해 주세요")
  private String content;
  private Boolean boardStatus;
  private String memberId;
  private LocalDate boardRegisterDt;
  private LocalDate boardChangeDt;
  private Integer boardCnt;

}

