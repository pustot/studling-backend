package com.pustot.studling;

import com.pustot.studling.model.MongoDocument;
import com.pustot.studling.model.User;
import com.pustot.studling.model.Word;
import com.pustot.studling.repository.MongoDocumentRepository;
import com.pustot.studling.repository.UserRepository;
import com.pustot.studling.repository.WordRepository;
import com.pustot.studling.service.DocumentSyncService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    private MongoDocumentRepository mongoDocumentRepository;
    private final DocumentSyncService documentSyncService;

    public DataInitializer(UserRepository userRepository, WordRepository wordRepository,
                           MongoDocumentRepository mongoDocumentRepository, DocumentSyncService documentSyncService) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;

        this.mongoDocumentRepository = mongoDocumentRepository;
        this.documentSyncService = documentSyncService;
    }


    @Override
    @Transactional
    public void run(String... args) {
        User user = new User("default1", "admin", "admin@example.com");
        User user2 = new User("c714ca08-7021-70f4-318d-ff9a51d36a89", "default2", "default2@example.com");
        userRepository.save(user);
        userRepository.save(user2);

        wordRepository.save(new Word("1", "请", "cing2", ""));
        wordRepository.save(new Word("2", "你", "nei5", ""));
        wordRepository.save(new Word("3", "好", "hou2", ""));
        wordRepository.save(new Word("4", "放", "fong3", ""));
        wordRepository.save(new Word("5", "低", "dai1", ""));

        mongoDocumentRepository.save(new MongoDocument(
                "id0101234567", "MyDocu", "Nidou jau houdo houdo noijung."
        ));
        mongoDocumentRepository.save(new MongoDocument(
                "id0101234110", "你好", "你好 jau jau jau houdo houdo noijung. jau"
        ));
        mongoDocumentRepository.save(new MongoDocument(
                "id0101234189", "jau sau aa nei", "fuk 你好 jau jau jau houdo houdo noijung. jau fong dai"
        ));
        mongoDocumentRepository.save(new MongoDocument(
                "id0101234985", "Dawn of a New Century", "Come with me to the gates of dawn."
        ));
        mongoDocumentRepository.save(new MongoDocument(
                "id0101234211", "粵語",
                "粵語Jyut6jyu5，又叫廣東話Gwong2dung1waa2、廣州話Gwong2zau1waa2／廣府話Gwong2fu2waa2、白話Baak6waa2，係廣東（舊稱「粵東」）、廣西（舊稱「粵西」）、海南三地嘅部份地區同埋香港、澳門講嘅語言；喺東南亞地區同埋一啲廣東人移民地方都有人講。\n"
        ));
        mongoDocumentRepository.save(new MongoDocument(
                "id0101231239", "人類所用嘅語言，可以分為聲調語言同埋非聲調語言。",
                "聲調語言嘅英文係tonal languages或者tone languages。呢類語言嘅特點，就係喺發同一個語音嘅時候，用唔同嘅長短、唔同高低嘅聲調，會構成出唔同意思（即語意或語義）嘅說話。同樣道理，非聲調語言就係指語音聲調同埋長短等，唔會影響到語意嘅語言。"
        ));

        documentSyncService.syncMongoDBToElasticsearch();
    }
}
