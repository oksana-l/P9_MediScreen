package com.OCR.P9_MediScreen_Assessment.service;

import com.OCR.P9_MediScreen_Assessment.constants.DiabetesAssessment;
import com.OCR.P9_MediScreen_Assessment.constants.TriggerTerms;
import com.OCR.P9_MediScreen_Assessment.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientRapportDto;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroserviceNotesProxy;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroservicePatientsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final MicroservicePatientsProxy patientProxy;
    private final MicroserviceNotesProxy noteProxy;
    String GENDER_F = "F";
    String GENDER_M = "M";
    @Autowired
    public AssessmentServiceImpl(final MicroservicePatientsProxy patientProxy, final MicroserviceNotesProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }
    @Override
    public PatientDTO getPatientById(Integer id) {
        return patientProxy.getPatientById(id);
    }
    @Override
    public List<NoteDTO> notes(Integer patientId) {
        return noteProxy.listNotes(patientId);
    }
    @Override
    public long getPatientAge(PatientDTO patient) {
        return ChronoUnit.YEARS.between(patient.getDateOfBirth(), LocalDate.now());
    }
    @Override
    public PatientRapportDto getRapportByPatientId(Integer id) {
        PatientDTO patient = getPatientById(id);
        String riskCategory = assessRiskCategory(patient, notes(id));
        return new PatientRapportDto(patient.getFirstName(), patient.getLastName(),
                getPatientAge(patient), riskCategory);
    }
    @Override
    public String assessRiskCategory(PatientDTO patient, List<NoteDTO> notes) {
        String riskCategory = DiabetesAssessment.NONE.getRiskLevel();
        long age = getPatientAge(patient);
        long numberTriggerTerms = countTriggerTerms(notes);
        if (age > 30 && numberTriggerTerms >= 2) {
            riskCategory = DiabetesAssessment.BORDERLINE.getRiskLevel();
        } else if ((age > 30 && (numberTriggerTerms >= 6 && numberTriggerTerms <= 7)) ||
                (GENDER_F.equals(patient.getGender()) && age < 30 && (numberTriggerTerms >= 4 && numberTriggerTerms <= 6)) ||
                (GENDER_M.equals(patient.getGender()) && age < 30 && (numberTriggerTerms >= 3 && numberTriggerTerms <= 4))) {
            riskCategory = DiabetesAssessment.IN_DANGER.getRiskLevel();
        } else if ((age > 30 && numberTriggerTerms >= 8) ||
                (GENDER_F.equals(patient.getGender()) && age < 30 && numberTriggerTerms >= 7) ||
                (GENDER_M.equals(patient.getGender()) && age < 30 && numberTriggerTerms >= 5)) {
            riskCategory = DiabetesAssessment.EARLY_ONSET.getRiskLevel();
        }
        return riskCategory;
    }
    @Override
    public Integer countTriggerTerms(List<NoteDTO> notes) {
        int totalKeywordCount = 0;

        for (NoteDTO note : notes) {
            Set<String> wordSet = new HashSet<>();
            String getNote = note.getNote();
            String[] words = getNote.split("[\\s,]+");
            wordSet.addAll(Arrays.asList(words));

            for (String word : wordSet) {
                for (String term : TriggerTerms.TERMINOLOGY) {
                    if (term.equalsIgnoreCase(word)) {
                        totalKeywordCount++;
                    }
                }
            }
        }
        return totalKeywordCount;
    }
}
