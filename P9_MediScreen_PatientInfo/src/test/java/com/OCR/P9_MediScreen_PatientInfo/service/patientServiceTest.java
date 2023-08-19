package com.OCR.P9_MediScreen_PatientInfo.service;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import com.OCR.P9_MediScreen_PatientInfo.model.Patient;
import com.OCR.P9_MediScreen_PatientInfo.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class patientServiceTest {
    private PatientService patientService;
    private PatientRepository patientRepository;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;
    private Patient patient4;
    private List<Patient> patients = new ArrayList<Patient>();

    @BeforeEach
    public void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientServiceImpl(patientRepository);
        patient1 = new Patient(1, "Lucas", "Ferguson", LocalDate.of(1968, 6, 22), "M", "2 Warren Street" , "387-866-1399");
        patient2 = new Patient(2, "Pippa" , "Rees", LocalDate.of(1952, 9, 27), "F", "745 West Valley Farms Drive", "628-423-0993");
        patient3 = new Patient(3, "Edward", "Arnold ", LocalDate.of(1952, 11, 11), "M", "599 East Garden Ave", "123-727-2779");
        patient4 = new Patient(4, "Anthony" , "Sharp", LocalDate.of(1946, 11, 26), "M", "894 Hall Street", "451-761-8383");
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);
    }

    @Test
    public void shouldGetAllPatientsTest() {
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTO> foundPatients = patientService.getAllPatients();

        Assertions.assertEquals(4, foundPatients.size());
        Assertions.assertEquals("Ferguson", foundPatients.get(0).getLastName());
        Assertions.assertEquals(LocalDate.of(1968, 6, 22), foundPatients.get(0).getDateOfBirth());
    }

    @Test
    public void shouldGetPatientTest() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient1));

        PatientDTO foundPatient = patientService.getPatient(1);

        Assertions.assertEquals("Lucas", foundPatient.getFirstName());
    }

    @Test
    public void shouldCreatePatientTest() {
        when(patientRepository.save(any())).thenReturn(patient2);

        PatientDTO createdPatient = patientService.createPatient(new PatientDTO(patient2));

        Assertions.assertEquals("Rees", createdPatient.getLastName());
    }

    @Test
    public void shouldUpdatePatientTest() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient1));
        when(patientRepository.save(any())).thenReturn(patient2);

        PatientDTO updatedPatient = patientService.updatePatient(1, new PatientDTO(patient1));

        Assertions.assertEquals("Pippa", updatedPatient.getFirstName());
    }

    @Test
    public void shouldDeletePatientByIdTest() {
        patientService.deletePatientById(1);
        verify(patientRepository).deleteById(1);
    }

}
