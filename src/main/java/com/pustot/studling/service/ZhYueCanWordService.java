package com.pustot.studling.service;

import com.pustot.studling.model.ZhYueCanWord;
import com.pustot.studling.repository.ZhYueCanWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZhYueCanWordService {

    @Autowired
    private ZhYueCanWordMapper zhYueCanWordMapper;

    public List<ZhYueCanWord> getRandomWords(int num) {
        List<Integer> allWordIds = zhYueCanWordMapper.getAllWordIds();
        Collections.shuffle(allWordIds);
        List<Integer> selectedIds = allWordIds.stream()
                .limit(num)
                .collect(Collectors.toList());
        return zhYueCanWordMapper.selectWordsByIds(selectedIds);
    }
}
