package com.pustot.studling.dto;

import java.util.List;

public class TrainingResultDto {
    private Long userId;
    private List<WordResult> results;

    // getter 和 setter 省略

    public static class WordResult {
        private Long wordId;
        private Boolean correct;

        // getter 和 setter 省略
    }
}
