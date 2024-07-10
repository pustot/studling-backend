package com.pustot.studling.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pustot.studling.model.DailyTrainingStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DailyTrainingStatsMapper extends BaseMapper<DailyTrainingStats> {

    @Update({
            "<script>",
            "INSERT INTO daily_training_stats (user_id, language_code, training_date, total_attempts, correct_attempts, incorrect_attempts) ",
            "VALUES (#{userId}, #{languageCode}, CURDATE(), 1, ",
            "<choose>",
            "    <when test='isCorrect'>1</when>",
            "    <otherwise>0</otherwise>",
            "</choose>, ",
            "<choose>",
            "    <when test='isCorrect'>0</when>",
            "    <otherwise>1</otherwise>",
            "</choose>) ",
            "ON DUPLICATE KEY UPDATE ",
            "total_attempts = total_attempts + 1, ",
            "correct_attempts = correct_attempts + ",
            "<choose>",
            "    <when test='isCorrect'>1</when>",
            "    <otherwise>0</otherwise>",
            "</choose>, ",
            "incorrect_attempts = incorrect_attempts + ",
            "<choose>",
            "    <when test='isCorrect'>0</when>",
            "    <otherwise>1</otherwise>",
            "</choose>",
            "</script>"
    })
    void updateDailyStats(@Param("userId") int userId, @Param("languageCode") String languageCode, @Param("isCorrect") boolean isCorrect);

    // New method to get today's training stats
    @Select("SELECT * FROM daily_training_stats WHERE user_id = #{userId} AND language_code = #{languageCode} AND training_date = CURDATE()")
    DailyTrainingStats getTodayTrainingStats(@Param("userId") int userId, @Param("languageCode") String languageCode);
}
