package com.example.community;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Document(collection = "member")
public class Member {

    @Id
    @NotBlank(message = "아이디를 입력해주세요")
    private String id;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String mbr_pwd;

    @Email(message = "이메일 형식이 아닙니다.")
    private String mbr_email;

    private String mbr_stat;
    private Boolean mbr_svc_use_pcy_agmt_yn;
    private Boolean mbr_ps_info_proc_agmt_yn;
    private LocalDateTime reg_dt;
    private LocalDateTime chg_dt;
    private int mbr_count;
}
