package com.OCR.P9_MediScreen_Assessment.proxies;

import com.OCR.P9_MediScreen_Assessment.model.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "microservice-patient")
public interface MicroservicePatientsProxy {

    @GetMapping("/patients/{id}")
    PatientDTO getPatientById(@PathVariable("id") final Integer id);

}

