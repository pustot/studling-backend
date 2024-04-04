package com.pustot.studling.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "your_index")
public class ElasticDocument {
    @Id
    private String id;
    private String title;
    private String content;

    public ElasticDocument(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    // getters and setters
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
}