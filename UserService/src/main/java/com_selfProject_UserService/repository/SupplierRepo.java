package com_selfProject_UserService.repository;

import com_selfProject_UserService.domain.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends MongoRepository<Supplier, String> {
}
