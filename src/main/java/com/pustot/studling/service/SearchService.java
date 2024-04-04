package com.pustot.studling.service;

import com.pustot.studling.model.ElasticDocument;
import com.pustot.studling.repository.ElasticDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final ElasticDocumentRepository repository;

    @Autowired
    public SearchService(ElasticDocumentRepository repository) {
        this.repository = repository;
    }

    public List<ElasticDocument> search(String text) {
        List<ElasticDocument> res = repository.findByTitleOrContent(text);
        System.out.println(res);
        if (!res.isEmpty()) System.out.println(res.getFirst().getTitle());
        return res;
        // return repository.findByTitleOrContent(text);
    }
}
