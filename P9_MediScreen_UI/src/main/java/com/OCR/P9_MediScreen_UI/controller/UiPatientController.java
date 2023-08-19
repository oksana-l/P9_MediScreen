package com.OCR.P9_MediScreen_UI.controller;

import com.OCR.P9_MediScreen_UI.model.PatientDTO;
import com.OCR.P9_MediScreen_UI.proxies.MicroserviceAssessmentProxy;
import com.OCR.P9_MediScreen_UI.proxies.MicroserviceNotesProxy;
import com.OCR.P9_MediScreen_UI.proxies.MicroservicePatientsProxy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UiPatientController {
    @Autowired
    private final MicroservicePatientsProxy patientProxy;
    @Autowired
    private final MicroserviceNotesProxy notesProxy;
    @Autowired
    private final MicroserviceAssessmentProxy assessmentProxy;
    public UiPatientController(MicroservicePatientsProxy patientProxy, final MicroserviceNotesProxy notesProxy, final MicroserviceAssessmentProxy assessmentProxy) {
        this.patientProxy = patientProxy;
        this.notesProxy = notesProxy;
        this.assessmentProxy = assessmentProxy;
    }

    @ModelAttribute("patientDto")
    public PatientDTO patientDto() {
        return new PatientDTO();
    }

    @GetMapping("/")
    public String allPatients(Model model) {
        List<PatientDTO> patients = patientProxy.listPatients();
        model.addAttribute("patients", patients);
        return "patient/listPatients";
    }

    @GetMapping("/infoPatient/{id}")
    public String patient(@PathVariable Integer id, Model model) {
        model.addAttribute("patient", patientProxy.getPatientById(id));
        return "patient/infoPatient";
    }

    @GetMapping("/createPatient")
    public String createPatient() {
        return "patient/createPatient";
    }

    @PostMapping("/createPatient")
    public String addPatient(@Valid PatientDTO patientDto) {
        patientProxy.addPatient(patientDto);
        return "redirect:/";
    }

    @GetMapping("/infoPatient/{id}/edit")
    public String updatePatient(@PathVariable Integer id,
                                Model model) {
        model.addAttribute("patientDto", patientProxy.getPatientById(id));
        return "patient/updatePatient";
    }

    @PostMapping("/infoPatient/{id}/edit")
    public String updatePatient(@PathVariable Integer id,
                                @Valid PatientDTO patientDto) {
        patientProxy.updatePatient(id, patientDto);
        return "redirect:/infoPatient/" + id;
    }

    @GetMapping("/infoPatient/{id}/delete")
    public String deletePatient(@PathVariable Integer id) {
        patientProxy.deletePatient(id);
        return "redirect:/";
    }
}
