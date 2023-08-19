package com.OCR.P9_MediScreen_Assessment.service;

import com.OCR.P9_MediScreen_Assessment.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientRapportDto;

import java.util.List;

public interface AssessmentService {
    PatientDTO getPatientById(Integer id);
    List<NoteDTO> notes(Integer patientId);
    long getPatientAge(PatientDTO patient);
    PatientRapportDto getRapportByPatientId(Integer id);
    String assessRiskCategory(PatientDTO patient, List<NoteDTO> notes);
    Integer countTriggerTerms(List<NoteDTO> notes);
}
