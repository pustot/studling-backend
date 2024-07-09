//package com.pustot.studling.model;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "training_records")
//public class TrainingRecord {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user; // 关联的用户
//
//    @ManyToOne
//    @JoinColumn(name = "word_id")
//    private Word word; // 关联的单词
//
//    @Column
//    private String comment;
//
//    @Column(name = "training_type")
//    private String trainingType;
//
//    @Column(name = "training_cnt")
//    private Integer trainingCnt;
//
//    @Column(name = "correct_cnt")
//    private Integer correctCnt;
//
//    @Column(name = "last_10_results")
//    private String last10Results;
//
//    @Column(name = "last_training_day")
//    private Date lastTrainingDay;
//
//    @Column(name = "day_cnt")
//    private Integer dayCnt;
//
//    @Column(name = "calculated_interval")
//    private Integer calculatedInterval;
//
//    public TrainingRecord(User user, Word word) {
//        this.user = user;
//        this.word = word;
//
//        this.trainingCnt = 0;
//        this.correctCnt = 0;
//        this.last10Results = "0000000000";
//    }
//
//    public TrainingRecord() {
//        this.trainingCnt = 0;
//        this.correctCnt = 0;
//        this.last10Results = "0000000000";
//    }
//
//    // Getters and Setters
//    public Integer getTrainingCnt() {
//        return trainingCnt;
//    }
//
//    public void setTrainingCnt(Integer trainingCnt) {
//        this.trainingCnt = trainingCnt;
//    }
//
//    public Integer getCorrectCnt() {
//        return correctCnt;
//    }
//
//    public void setCorrectCnt(Integer correctCnt) {
//        this.correctCnt = correctCnt;
//    }
//
//    public String getLast10Results() {
//        return last10Results;
//    }
//
//    public void setLast10Results(String last10Results) {
//        this.last10Results = last10Results;
//    }
//}
