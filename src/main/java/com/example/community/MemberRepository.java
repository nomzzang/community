package com.example.community;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

  Member findByEmail(String email);

  Member findByUserId(String userId);

}
