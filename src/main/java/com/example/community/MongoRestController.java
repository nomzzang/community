package com.example.community;

import java.time.LocalDate;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MongoRestController {

  private final MemberService memberService;
  private final MemberRepository memberRepository;

  @GetMapping("/findAll")
  public void findAll(Member member) {
    memberService.findAllMember(member);
  }

  @GetMapping("/find-id")
  public void findId(@RequestBody Member member) {
    memberService.findMemberId(member);
  }
  @GetMapping("/insert")
  public void insert(@RequestBody @Valid Member member) {
    memberService.addMember(member);
  }

  // RDBMS 이용한 사용자 입력
  @PostMapping("/insert2")
  public Member insertRepository(@RequestBody @Valid Member member) {

    Member findUserId = memberRepository.findByUserId(member.getUserId());
    if (findUserId != null) {
      throw new IllegalStateException("이미 가입된 아이디입니다.");
    }

    Member findMember = memberRepository.findByEmail(member.getEmail());
    if (findMember != null) {
      throw new IllegalStateException("이미 가입된 이메일입니다.");
    }

    Member createmember = Member.builder()
        .userId(member.getUserId())
        .password(member.getPassword())
        .email(member.getEmail())
        .status(member.getStatus())
        .userServiceYn(member.getUserServiceYn())
        .userInformationYn(member.getUserInformationYn())
        .registerDt(LocalDate.now())
        .changeDt(LocalDate.now())
        .postCnt(member.getPostCnt())
        .build();

    return memberRepository.save(createmember);

  }

  @GetMapping("/update")
  public void update(@RequestBody Member member) {

    memberService.update(member);

  }

  @GetMapping("/delete")
  public void delete(@RequestBody Member member) {

    memberService.delete(member);
  }

  @GetMapping("/login")
  public void login(@RequestBody Member member) {
    memberService.login(member);
  }
}

