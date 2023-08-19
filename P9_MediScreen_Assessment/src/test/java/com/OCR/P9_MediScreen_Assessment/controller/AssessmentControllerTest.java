package com.OCR.P9_MediScreen_Assessment.controller;

import com.OCR.P9_MediScreen_Assessment.constants.DiabetesAssessment;
import com.OCR.P9_MediScreen_Assessment.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientDTO;
import com.OCR.P9_MediScreen_Assessment.model.dto.PatientRapportDto;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroserviceNotesProxy;
import com.OCR.P9_MediScreen_Assessment.proxies.MicroservicePatientsProxy;
import com.OCR.P9_MediScreen_Assessment.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssessmentControllerTest {
    private MockMvc mockMvc;
    private AssessmentService assessmentService;
    private AssessmentController assessmentController;
    private MicroservicePatientsProxy patientProxy;
    private MicroserviceNotesProxy noteProxy;
    private PatientDTO patient1;
    private List<NoteDTO> notesPatient1 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        patientProxy = mock(MicroservicePatientsProxy.class);
        noteProxy = mock(MicroserviceNotesProxy.class);
        assessmentService = mock(AssessmentService.class);
        assessmentController = new AssessmentController(assessmentService);

        patient1 = new PatientDTO(1, "Test", "TestNone", LocalDate.of(1966, 12, 31), "F", "2 Warren Street" , "387-866-1399");
        notesPatient1.add(new NoteDTO("1r3yw8", 1, LocalDate.now().toString(),
                "Patient: TestNone Practitioner's notes/recommendations: " +
                        "Patient states that they are 'feeling terrific' " +
                        "Weight at or below recommended level"));

        mockMvc = MockMvcBuilders.standaloneSetup(assessmentController).build();
    }

    @Test
    public void testGetRapportAssessmentByPatientId() throws Exception {
        when(assessmentService.getRapportByPatientId(1))
                .thenReturn(new PatientRapportDto("Test", "TestNone", 67, DiabetesAssessment.NONE.getRiskLevel()));

        mockMvc.perform(get("/assessment/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("TestNone"))
                .andExpect(jsonPath("$.age").value(67))
                .andExpect(jsonPath("$.riskCategory").value(DiabetesAssessment.NONE.getRiskLevel()));
    }
}
