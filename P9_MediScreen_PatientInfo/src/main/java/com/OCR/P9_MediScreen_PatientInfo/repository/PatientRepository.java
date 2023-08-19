package com.OCR.P9_MediScreen_PatientInfo.repository;

import com.OCR.P9_MediScreen_PatientInfo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findAll();
    Optional<Patient> findById(int id);
    Patient save(Patient patient);
}
