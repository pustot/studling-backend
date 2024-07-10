package com.pustot.studling.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pustot.studling.model.ZhYueCanMastery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ZhYueCanMasteryMapper extends BaseMapper<ZhYueCanMastery> {

    @Select("SELECT * FROM zh_yue_can_masteries WHERE user_id = #{userId} AND word_id = #{wordId}")
    ZhYueCanMastery findByUserIdAndWordId(@Param("userId") int userId, @Param("wordId") int wordId);

    @Update("UPDATE zh_yue_can_masteries SET recent_results = #{recentResults}, accuracy = #{accuracy}, last_attempt_date = CURRENT_TIMESTAMP WHERE user_id = #{userId} AND word_id = #{wordId}")
    void updateTrainingResult(@Param("userId") int userId, @Param("wordId") int wordId, @Param("recentResults") String recentResults, @Param("accuracy") float accuracy);

    @Insert("INSERT INTO zh_yue_can_masteries (user_id, word_id, recent_results, accuracy, last_attempt_date) VALUES (#{userId}, #{wordId}, #{recentResults}, #{accuracy}, CURRENT_TIMESTAMP)")
    void insertTrainingResult(@Param("userId") int userId, @Param("wordId") int wordId, @Param("recentResults") String recentResults, @Param("accuracy") float accuracy);
}
