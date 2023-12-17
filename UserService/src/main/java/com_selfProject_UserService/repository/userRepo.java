package com_selfProject_UserService.repository;

import com_selfProject_UserService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends MongoRepository<User,String> {
}
