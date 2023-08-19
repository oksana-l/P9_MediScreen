package com.OCR.P9_MediScreen_PatientInfo.service;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatient(int id);
    PatientDTO createPatient(PatientDTO patientDto);
    PatientDTO updatePatient(int id, PatientDTO patientDto);
    boolean isPatientExists(int id);
    void deletePatientById(int id);
//    public void insertDataFromExcel(Resource resource) throws IOException;
}
