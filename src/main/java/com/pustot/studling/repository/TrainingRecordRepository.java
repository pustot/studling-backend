package com.pustot.studling.repository;

import com.pustot.studling.model.TrainingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRecordRepository extends JpaRepository<TrainingRecord, Long> {
    // 这里可以根据需要添加自定义的数据库操作方法
}
