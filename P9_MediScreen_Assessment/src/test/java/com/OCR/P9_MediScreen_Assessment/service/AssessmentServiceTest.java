package com.OCR.P9_MediScreen_Assessment.service;

import com.OCR.P9_MediScreen_Assessment.constants.DiabetesAssessment;
import com.OCR.P9_MediScreen_Assessment.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientRapportDto;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroserviceNotesProxy;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroservicePatientsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssessmentServiceTest {
    private AssessmentService assessmentService;
    private MicroservicePatientsProxy patientProxy;
    private MicroserviceNotesProxy noteProxy;
    private PatientDTO patient1;
    private PatientDTO patient2;
    private PatientDTO patient3;
    private PatientDTO patient4;
    private List<NoteDTO> notesPatient1 = new ArrayList<>();
    private List<NoteDTO> notesPatient2 = new ArrayList<>();
    private List<NoteDTO> notesPatient3 = new ArrayList<>();
    private List<NoteDTO> notesPatient4 = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        patientProxy = mock(MicroservicePatientsProxy.class);
        noteProxy = mock(MicroserviceNotesProxy.class);
        assessmentService = new AssessmentServiceImpl(patientProxy, noteProxy);

        patient1 = new PatientDTO(1, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "2 Warren Street" , "387-866-1399");
        patient2 = new PatientDTO(2, "Test", "TestBorderline", LocalDate.of(1945, 06, 24), "M", "2 Warren Street" , "387-866-1399");
        patient3 = new PatientDTO(3, "Test", "TestInDanger", LocalDate.of(2004, 06, 18), "M", "2 Warren Street" , "387-866-1399");
        patient4 = new PatientDTO(4, "Test", "TestEarlyOnset", LocalDate.of(2002, 06, 28), "F", "2 Warren Street" , "387-866-1399");


        notesPatient1.add(new NoteDTO("1r3yw8", 1, LocalDate.now().toString(),
                "Patient: TestNone Practitioner's notes/recommendations: " +
                        "Patient states that they are 'feeling terrific' " +
                        "Weight at or below recommended level"));
        notesPatient2.add(new NoteDTO(null, 2, LocalDate.now().toString(),
                "Tests de laboratoire indiquant une Microalbumine élevée" +
                        "Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé " +
                        "Le patient déclare qu’il fume depuis longtemps "));
        notesPatient2.add(new NoteDTO("3jwhs5ws", 2, LocalDate.now().toString(),
                "Patient: TestBorderline Practitioner's notes/recommendations: " +
                        "Patient states that they have had a Reaction to medication within last 3 months " +
                        "Patient also complains that their hearing continues to be problematic"));
        notesPatient3.add(new NoteDTO(null, 3, LocalDate.now().toString(),
                "Patient: TestInDanger Practitioner's notes/recommendations: " +
                        "Patient states that they are short term Smoker "));
        notesPatient3.add(new NoteDTO(null, 3, LocalDate.now().toString(),
                "Patient: TestInDanger Practitioner's notes/recommendations: " +
                        "Patient states that they quit within last year " +
                        "Patient also complains that of Abnormal breathing spells " +
                        "Lab reports Cholesterol LDL high"));
        notesPatient4.add(new NoteDTO(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that walking up stairs has become difficult " +
                        "Patient also complains that they are having shortness of breath " +
                        "Lab results indicate Antibodies present elevated Reaction to medication"));
        notesPatient4.add(new NoteDTO(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that they are experiencing back pain when seated for a long time"));
        notesPatient4.add(new NoteDTO(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction"));
    }

    @Test
    public void shouldGetPatientByIdTest() {
        when(patientProxy.getPatientById(1)).thenReturn(patient1);

        PatientDTO patientTest = assessmentService.getPatientById(1);

        assertEquals(patient1.getLastName(), patientTest.getLastName());
    }

    @Test
    public void shouldNotesTest() {
        when(noteProxy.listNotes(2)).thenReturn(notesPatient2);

        List<NoteDTO> listNotesTest = assessmentService.notes(2);

        assertEquals(notesPatient2.size(), listNotesTest.size());
    }

    @Test
    public void shouldGetPatientAgeTest() {
    // TO DO
        long age1 = assessmentService.getPatientAge(patient1);
        long age2 = assessmentService.getPatientAge(patient2);
        long age3 = assessmentService.getPatientAge(patient3);
        long age4 = assessmentService.getPatientAge(patient4);

        long expectedAge1 = ChronoUnit.YEARS.between(patient1.getDateOfBirth(), LocalDate.now());
        long expectedAge2 = ChronoUnit.YEARS.between(patient2.getDateOfBirth(), LocalDate.now());
        long expectedAge3 = ChronoUnit.YEARS.between(patient3.getDateOfBirth(), LocalDate.now());
        long expectedAge4 = ChronoUnit.YEARS.between(patient4.getDateOfBirth(), LocalDate.now());

        assertEquals(expectedAge1, age1);
        assertEquals(expectedAge2, age2);
        assertEquals(expectedAge3, age3);
        assertEquals(expectedAge4, age4);
    }

    @Test
    public void shouldGetRapportByPatientIdTest() {
        when(patientProxy.getPatientById(1)).thenReturn(patient1);
        when(patientProxy.getPatientById(2)).thenReturn(patient2);
        when(patientProxy.getPatientById(3)).thenReturn(patient3);
        when(patientProxy.getPatientById(4)).thenReturn(patient4);

        when(noteProxy.listNotes(1)).thenReturn(notesPatient1);
        when(noteProxy.listNotes(2)).thenReturn(notesPatient2);
        when(noteProxy.listNotes(3)).thenReturn(notesPatient3);
        when(noteProxy.listNotes(4)).thenReturn(notesPatient4);

        PatientRapportDto rapportPatient1 = assessmentService.getRapportByPatientId(1);
        PatientRapportDto rapportPatient2 = assessmentService.getRapportByPatientId(2);
        PatientRapportDto rapportPatient3 = assessmentService.getRapportByPatientId(3);
        PatientRapportDto rapportPatient4 = assessmentService.getRapportByPatientId(4);

        assertEquals(patient1.getFirstName(), rapportPatient1.getFirstName());
        assertEquals(patient1.getLastName(), rapportPatient1.getLastName());
        assertEquals(ChronoUnit.YEARS.between(patient1.getDateOfBirth(), LocalDate.now()), rapportPatient1.getAge());
        assertEquals(DiabetesAssessment.NONE.getRiskLevel(), rapportPatient1.getRiskCategory());

        assertEquals(patient2.getFirstName(), rapportPatient2.getFirstName());
        assertEquals(patient2.getLastName(), rapportPatient2.getLastName());
        assertEquals(ChronoUnit.YEARS.between(patient2.getDateOfBirth(), LocalDate.now()), rapportPatient2.getAge());
        assertEquals(DiabetesAssessment.BORDERLINE.getRiskLevel(), rapportPatient2.getRiskCategory());

        assertEquals(patient3.getFirstName(), rapportPatient3.getFirstName());
        assertEquals(patient3.getLastName(), rapportPatient3.getLastName());
        assertEquals(ChronoUnit.YEARS.between(patient3.getDateOfBirth(), LocalDate.now()), rapportPatient3.getAge());
        assertEquals(DiabetesAssessment.IN_DANGER.getRiskLevel(), rapportPatient3.getRiskCategory());

        assertEquals(patient4.getFirstName(), rapportPatient4.getFirstName());
        assertEquals(patient4.getLastName(), rapportPatient4.getLastName());
        assertEquals(ChronoUnit.YEARS.between(patient4.getDateOfBirth(), LocalDate.now()), rapportPatient4.getAge());
        assertEquals(DiabetesAssessment.EARLY_ONSET.getRiskLevel(), rapportPatient4.getRiskCategory());

    }

    @Test
    public void shouldAssessRiskCategoryTest() {
        String riskCategory1 = assessmentService.assessRiskCategory(patient1, notesPatient1);
        String riskCategory2 = assessmentService.assessRiskCategory(patient2, notesPatient2);
        String riskCategory3 = assessmentService.assessRiskCategory(patient3, notesPatient3);
        String riskCategory4 = assessmentService.assessRiskCategory(patient4, notesPatient4);

        assertEquals(DiabetesAssessment.NONE.getRiskLevel(), riskCategory1);
        assertEquals(DiabetesAssessment.BORDERLINE.getRiskLevel(), riskCategory2);
        assertEquals(DiabetesAssessment.IN_DANGER.getRiskLevel(), riskCategory3);
        assertEquals(DiabetesAssessment.EARLY_ONSET.getRiskLevel(), riskCategory4);
    }

    @Test
    public void shouldCountTriggerTermsTest() {
        List<NoteDTO> notesTest = notesPatient4;

        Integer countTest = assessmentService.countTriggerTerms(notesTest);

        assertEquals(7, countTest);
    }
}
