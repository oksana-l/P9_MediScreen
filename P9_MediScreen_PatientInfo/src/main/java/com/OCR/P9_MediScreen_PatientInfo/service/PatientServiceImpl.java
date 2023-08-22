package com.OCR.P9_MediScreen_PatientInfo.service;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import com.OCR.P9_MediScreen_PatientInfo.model.Patient;
import com.OCR.P9_MediScreen_PatientInfo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(PatientDTO::new).toList();
    }

    @Override
    public PatientDTO getPatient(int id) {
        return new PatientDTO(patientRepository.findById(id).get());
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDto) {
        return new PatientDTO(patientRepository.save(new Patient(patientDto)));
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(int id, PatientDTO patientDto) {

        Patient patientForUpdate = patientRepository.findById(id).get();
        patientForUpdate.setFirstName(patientDto.getFirstName());
        patientForUpdate.setLastName(patientDto.getLastName());
        patientForUpdate.setDateOfBirth(patientDto.getDateOfBirth());
        patientForUpdate.setGender(patientDto.getGender());
        patientForUpdate.setAddress(patientDto.getAddress());
        patientForUpdate.setPhoneNumber(patientDto.getPhoneNumber());
        return new PatientDTO(patientRepository.save(patientForUpdate));
    }

    @Override
    public void  deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public boolean isPatientExists(int id) {
        return patientRepository.existsById(id);
    }

}
