package com.pustot.studling.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import java.util.Date;
//import java.util.List;

@Document(collection = "mongo_documents")
public class MongoDocument {
    @Id
    private String id;

    private String title;
    private String content;

    public MongoDocument(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

//    private Date createdAt;
//    private List<String> tags;
//    private Author author; // 嵌套文档示例
//    private List<Comment> comments; // 文档数组示例

    // 构造函数、Getter 和 Setter 省略

//    // 内嵌的作者信息
//    public static class Author {
//        private String name;
//        private String email;
//        // 构造函数、Getter 和 Setter 省略
//    }
//
//    // 内嵌的评论信息
//    public static class Comment {
//        private String userId;
//        private String content;
//        private Date commentedAt;
//        // 构造函数、Getter 和 Setter 省略
//    }
}