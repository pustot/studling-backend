package com.pustot.studling.repository;

import com.pustot.studling.model.MongoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDocumentRepository extends MongoRepository<MongoDocument, String> {
    // 定义查询方法
}