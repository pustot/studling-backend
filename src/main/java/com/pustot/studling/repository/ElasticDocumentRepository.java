//package com.pustot.studling.repository;
//
//import com.pustot.studling.model.ElasticDocument;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface ElasticDocumentRepository extends ElasticsearchRepository<ElasticDocument, String> {
//    @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": \"?0\"}}, {\"match\": {\"content\": \"?0\"}}]}}")
//    List<ElasticDocument> findByTitleOrContent(String text);
//}
