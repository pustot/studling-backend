package com.pustot.studling;

import com.pustot.studling.model.User;
import com.pustot.studling.model.Word;
import com.pustot.studling.repository.UserRepository;
import com.pustot.studling.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    public DataInitializer(UserRepository userRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }


    @Override
    @Transactional
    public void run(String... args) {
        User user = new User("admin", "admin@example.com");
        userRepository.save(user);

        wordRepository.save(new Word("请", "cing2", ""));
        wordRepository.save(new Word("你", "nei5", ""));
        wordRepository.save(new Word("好", "hou2", ""));
        wordRepository.save(new Word("放", "fong3", ""));
        wordRepository.save(new Word("低", "dai1", ""));
    }
}
