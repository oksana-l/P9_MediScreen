package com.OCR.P9_MediScreen_PatientInfo.controller;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import com.OCR.P9_MediScreen_PatientInfo.service.PatientService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;
    private final ResourceLoader resourceLoader;
    @Autowired
    public PatientController(final PatientService patientService, final ResourceLoader resourceLoader) {
        this.patientService = patientService;
        this.resourceLoader = resourceLoader;
    }

    @ApiResponse(responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = PatientDTO.class))))
    @GetMapping
    public ResponseEntity<List<PatientDTO>> listPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody @NotNull @Valid PatientDTO patientDto) {
        PatientDTO patientAdded = patientService.createPatient(patientDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).body(patientAdded);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> showPatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatient(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable int id,
                                                    @RequestBody @NotNull @Valid PatientDTO patientDto) {
       // patientDto.setId(null);
        PatientDTO patientUpdated = patientService.updatePatient(id, patientDto);
        return ResponseEntity.ok(patientUpdated);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/import-excel")
//    public void importExcelData(@RequestParam String fileName) throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:" + fileName);
//        patientService.insertDataFromExcel(resource);
//    }
}
