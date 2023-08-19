package com.OCR.P9_MediScreen_UI.proxies;

import com.OCR.P9_MediScreen_UI.model.AssessmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-assessment")
public interface MicroserviceAssessmentProxy {
    @GetMapping("/assessment/{id}")
    AssessmentDTO getAssessmentByPatientId(@PathVariable("id") final Integer id);
}
