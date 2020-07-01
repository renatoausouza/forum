package com.renatosouza.forum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.renatosouza.forum.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
