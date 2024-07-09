package com.pustot.studling.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pustot.studling.model.ZhYueCanWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZhYueCanWordMapper extends BaseMapper<ZhYueCanWord> {

    @Select("SELECT word_id FROM zh_yue_can_words")
    List<Integer> getAllWordIds();

    @Select({
            "<script>",
            "SELECT * FROM zh_yue_can_words WHERE word_id IN",
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<ZhYueCanWord> selectWordsByIds(@Param("ids") List<Integer> ids);
}
