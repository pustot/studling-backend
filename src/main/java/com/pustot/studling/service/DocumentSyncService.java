package com.pustot.studling.service;

import com.pustot.studling.model.ElasticDocument;
import com.pustot.studling.model.MongoDocument;
import com.pustot.studling.repository.ElasticDocumentRepository;
import com.pustot.studling.repository.MongoDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentSyncService {
    @Autowired
    private MongoDocumentRepository mongoDocumentRepository;

    @Autowired
    private ElasticDocumentRepository elasticDocumentRepository;

    public void syncMongoDBToElasticsearch() {
        // 从 MongoDB 获取数据
        List<MongoDocument> mongoDocuments = mongoDocumentRepository.findAll();

        // 将 MongoDB 实体转换为 Elasticsearch 实体
        List<ElasticDocument> elasticDocuments = mongoDocuments.stream()
                .map(mongoDocument -> new ElasticDocument(mongoDocument.getId(), mongoDocument.getTitle(), mongoDocument.getContent()))
                .collect(Collectors.toList());

        // 保存到 Elasticsearch
        elasticDocumentRepository.saveAll(elasticDocuments);
    }
}
