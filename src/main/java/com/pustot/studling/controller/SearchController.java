//package com.pustot.studling.controller;
//
//import com.pustot.studling.model.ElasticDocument;
//import com.pustot.studling.service.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/search")
//public class SearchController {
//
//    private final SearchService searchService;
//
//    @Autowired
//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }
//
//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping
//    public List<ElasticDocument> search(@RequestParam("query") String query) { // 使用@RequestParam接收查询参数
//        System.out.println("Searching for: " + query);
//        return searchService.search(query); // 调用Service执行搜索，并返回结果
//    }
//}