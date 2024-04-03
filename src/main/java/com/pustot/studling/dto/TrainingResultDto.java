package com.pustot.studling.dto;

import java.util.List;

public class TrainingResultDto {
    private Long userId;
    private List<WordResult> results;

    // getter 和 setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<WordResult> getResults() {
        return results;
    }

    public void setResults(List<WordResult> results) {
        this.results = results;
    }

    public static class WordResult {
        private Long wordId;
        private Boolean correct;

        // getter 和 setter
        public Long getWordId() {
            return wordId;
        }

        public void setWordId(Long wordId) {
            this.wordId = wordId;
        }

        public Boolean isCorrect() {
            return correct;
        }

        public void setCorrect(Boolean correct) {
            this.correct = correct;
        }

        public boolean getCorrect() {
            return correct;
        }
    }
}
