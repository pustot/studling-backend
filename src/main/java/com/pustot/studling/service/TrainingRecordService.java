package com.pustot.studling.service;

import com.pustot.studling.dto.TrainingResultDto;
import com.pustot.studling.model.TrainingRecord;
import com.pustot.studling.repository.TrainingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TrainingRecordService {

    private final TrainingRecordRepository trainingRecordRepository;

    @Autowired
    public TrainingRecordService(TrainingRecordRepository trainingRecordRepository) {
        this.trainingRecordRepository = trainingRecordRepository;
    }

    @Transactional
    public void updateTrainingResults(TrainingResultDto trainingResultDto) {
        System.out.println("Recieeshita");
//        for (TrainingResultDto.WordResult result : trainingResultDto.getResults()) {
//            // 这里简化了查找和更新逻辑，实际应用中可能需要考虑用户和单词的关联
//            Optional<TrainingRecord> recordOpt = trainingRecordRepository.findByUserIdAndWordId(trainingResultDto.getUserId(), result.getWordId());
//            TrainingRecord record = recordOpt.orElse(new TrainingRecord());
//            // 更新或初始化记录
//            record.setTrainingTimes(record.getTrainingTimes() + 1);
//            if (result.getCorrect()) {
//                record.setCorrectTimes(record.getCorrectTimes() + 1);
//            }
//            trainingRecordRepository.save(record);
//        }
    }
}
