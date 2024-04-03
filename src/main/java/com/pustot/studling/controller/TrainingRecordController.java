package com.pustot.studling.controller;

import com.pustot.studling.dto.TrainingResultDto;
import com.pustot.studling.service.TrainingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training")
public class TrainingRecordController {

    private final TrainingRecordService trainingRecordService;

    @Autowired
    public TrainingRecordController(TrainingRecordService trainingRecordService) {
        this.trainingRecordService = trainingRecordService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/results")
    public ResponseEntity<?> saveTrainingResults(@RequestBody TrainingResultDto trainingResult) {
        trainingRecordService.updateTrainingResults(trainingResult);
        return ResponseEntity.ok().build();
    }
}
