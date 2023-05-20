package com.example.community.Member.Model;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "memberTest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

  @Transient
  public static final String SEQUENCE_NAME = "members_sequence";

  @Id
  private String id;
  private Long idx;

  @NotBlank(message = "아이디를 입력해주세요")
  private String userId;

  @NotBlank(message = "비밀번호를 입력해 주세요")
  private String password;

  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  private Boolean memberStatus;
  private Boolean userServiceYn;
  private Boolean userInformationYn;
  private LocalDate registerDt;
  private LocalDate changeDt;
  private Integer postCnt;

}
