package com.OCR.P9_MediScreen_PatientInfo.controller;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import com.OCR.P9_MediScreen_PatientInfo.model.Patient;
import com.OCR.P9_MediScreen_PatientInfo.service.PatientService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@WebMvcTest(PatientController.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Patient patient1;
    private Patient patient2;
    private List<PatientDTO> mockPatients;

    @MockBean
    private PatientService patientService;

//    @InjectMocks
//    private PatientController patientController;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
        patient1 = new Patient(1, "Lucas", "Ferguson",
                LocalDate.of(1968, 6, 22), "M",
                "2 Warren Street", "387-866-1399");
        patient2 = new Patient(2, "Pippa", "Rees",
                LocalDate.of(1952, 9, 27), "F",
                "745 West Valley Farms Drive", "628-423-0993");
        mockPatients = new ArrayList<>();
        mockPatients.add(new PatientDTO(patient1));
        mockPatients.add(new PatientDTO(patient2));
    }

    @Test
    public void shouldGetListPatientsTest() throws Exception {
        when(patientService.getAllPatients()).thenReturn(mockPatients);

        MvcResult result = mockMvc.perform(get("/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldAddPatientTest() throws Exception {
        PatientDTO mockPatient = new PatientDTO(patient1);

        when(patientService.createPatient(any(PatientDTO.class))).thenReturn(mockPatient);

        MvcResult result = mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"firstName\": \"Lucas\"," +
                                "\"lastName\": \"Ferguson\"," +
                                "\"dateOfBirth\": \"22/06/1968\"," +
                                "\"gender\": \"M\"," +
                                "\"address\": \"2 Warren Street\"," +
                                "\"phoneNumber\": \"387-866-1399\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldAddPatientFormTest() throws Exception {
        PatientDTO mockPatient = new PatientDTO(patient1);

        when(patientService.createPatient(any(PatientDTO.class))).thenReturn(mockPatient);

        MvcResult result = mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName","Lucas")
                        .param("lastName", "Ferguson")
                        .param("dateOfBirth","1968-06-28")
                        .param("gender","M")
                        .param("address", "2 Warren Street")
                        .param("phoneNumber","387-866-1399"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isCreated())
                        .andReturn();
        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldShowPatientTest() throws Exception {
        int patientId = 1;
        PatientDTO mockPatient = new PatientDTO(patient1);

        when(patientService.getPatient(patientId)).thenReturn(mockPatient);

        MvcResult result = mockMvc.perform(get("/patients/" + patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldUpdatePatientTest() throws Exception {
        int patientId = 1;
        PatientDTO mockPatient = new PatientDTO(patient1);

        when(patientService.updatePatient(1, new PatientDTO(patient2))).thenReturn(mockPatient);

        MvcResult result = mockMvc.perform(put("/patients/" + patientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"firstName\": \"Lucas\"," +
                                "\"lastName\": \"Ferguson\"," +
                                "\"dateOfBirth\": \"22/06/1968\"," +
                                "\"gender\": \"M\"," +
                                "\"address\": \"2 Warren Street\"," +
                                "\"phoneNumber\": \"387-866-1399\"" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void testDeletePatient() throws Exception {
        int patientId = 1;
        doNothing().when(patientService).deletePatientById(patientId);

        mockMvc.perform(delete("/patients/" + patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
