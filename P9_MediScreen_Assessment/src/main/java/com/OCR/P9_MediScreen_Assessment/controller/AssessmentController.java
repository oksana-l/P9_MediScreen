package com.OCR.P9_MediScreen_Assessment.controller;

import com.OCR.P9_MediScreen_Assessment.model.dto.PatientRapportDto;
import com.OCR.P9_MediScreen_Assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    private AssessmentService assessmentService;
    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientRapportDto> getRapportAssessmentByPatientId(@PathVariable int id) {
        return ResponseEntity.ok(assessmentService.getRapportByPatientId(id));
    }
}
