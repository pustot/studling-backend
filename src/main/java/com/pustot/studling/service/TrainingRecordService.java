package com.pustot.studling.service;

import com.pustot.studling.dto.TrainingResultDto;
import com.pustot.studling.model.TrainingRecord;
import com.pustot.studling.model.User;
import com.pustot.studling.model.Word;
import com.pustot.studling.repository.TrainingRecordRepository;
import com.pustot.studling.repository.UserRepository;
import com.pustot.studling.repository.WordRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Transactional
    public void updateTrainingResults(TrainingResultDto trainingResultDto) {
        System.out.println("Recieeshita");
        System.out.println(trainingResultDto.getUserId());
        System.out.println(trainingResultDto.getResults().getFirst().getWordId());
        System.out.println(userRepository.findById(trainingResultDto.getUserId()));

        for (TrainingResultDto.WordResult result : trainingResultDto.getResults()) {
            Optional<TrainingRecord> recordOpt = trainingRecordRepository.findByUserIdAndWordId(
                    trainingResultDto.getUserId(), result.getWordId());

            // 从仓库中查找User和Word实体
            User user = userRepository.findById(trainingResultDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            Word word = wordRepository.findById(result.getWordId()).orElseThrow(() -> new RuntimeException("Word not found"));

            TrainingRecord record = recordOpt.orElseGet(() -> new TrainingRecord(user, word));

            // 更新记录
            record.setTrainingCnt(record.getTrainingCnt() + 1);
            if (result.getCorrect()) {
                record.setCorrectCnt(record.getCorrectCnt() + 1);
            }

            // 更新最近10次训练结果
            String last10Results = record.getLast10Results() != null ? record.getLast10Results() : "";
            last10Results = updateLast10Results(last10Results, result.getCorrect());
            record.setLast10Results(last10Results);

            // 保存更新的记录
            trainingRecordRepository.save(record);
        }
    }

    private String updateLast10Results(String last10Results, boolean correct) {
        if (last10Results.length() >= 10) {
            last10Results = last10Results.substring(1);
        }
        last10Results += correct ? "1" : "0";
        return last10Results;
    }

    private TrainingRecord convertDtoToEntity(TrainingResultDto dto) {
        // 转换逻辑
        TrainingRecord record = new TrainingRecord();
        // 填充record的属性与dto的属性相对应
        return record;
    }
}
