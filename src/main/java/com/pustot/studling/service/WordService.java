//package com.pustot.studling.service;
//
//import com.pustot.studling.model.Word;
//import com.pustot.studling.repository.WordRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WordService {
//    private final WordRepository wordRepository;
//
//    @Autowired
//    public WordService(WordRepository wordRepository) {
//        this.wordRepository = wordRepository;
//    }
//
//    public List<Word> getAllWords() {
//        return wordRepository.findAll();
//    }
//
//    // 添加更多服务方法，比如添加、删除、更新单词等
//}