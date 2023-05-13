package com.example.community;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private static final String COLLECTION = "members";
    private final MongoDatabase mongoDatabase;
    private final MemberRepository memberRepository;

    private void validateDuplicateMember(Member member){
        Optional<Member> findMember = memberRepository.findById(member.getId());

        System.out.println("실행");

        if(findMember.isPresent()){
            System.out.println("중복 검사");
        }
        if(findMember!=null){
            System.out.println("중복 검사22222");
            System.out.println(findMember);
        }
    }

    public void addMember(Member member){
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
        Document document = new Document();

        validateDuplicateMember(member);

        document.append("id", member.getId());
        document.append("mbr_stat", "NORMAL");
        document.append("mbr_pwd", member.getMbr_pwd());
        document.append("mbr_email", member.getMbr_email());
        document.append("mbr_svc_use_pcy_agmt_yn", member.getMbr_svc_use_pcy_agmt_yn());
        document.append("mbr_ps.info_proc_agmt_yn", member.getMbr_ps_info_proc_agmt_yn());
        document.append("reg_dt", LocalDateTime.now());
        document.append("chg_dt", LocalDateTime.now());
        document.append("mbr_count", 0);

        InsertOneResult result = collection.insertOne(document);
        System.out.println("result : " + result.getInsertedId());

    }
}
