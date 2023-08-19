package com.OCR.P9_MediScreen_UI.proxies;

import com.OCR.P9_MediScreen_UI.model.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patient")
public interface MicroservicePatientsProxy {
    @GetMapping("/patients")
    List<PatientDTO> listPatients();

    @GetMapping("/patients/{id}")
    PatientDTO getPatientById(@PathVariable("id") final Integer id);

    @PostMapping("/patients")
    PatientDTO addPatient(@RequestBody PatientDTO patientDto);

    @PutMapping("/patients/{id}")
    PatientDTO updatePatient(@PathVariable("id") final Integer id,
                             @RequestBody PatientDTO patientDto);

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable("id") Integer id);
}

