package com.OCR.P9_MediScreen_UI.controller;

import com.OCR.P9_MediScreen_UI.model.AssessmentDTO;
import com.OCR.P9_MediScreen_UI.proxies.MicroserviceAssessmentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UiAssessmentController {
    @Autowired
    private final MicroserviceAssessmentProxy assessmentProxy;

    public UiAssessmentController(final MicroserviceAssessmentProxy assessmentProxy) {
        this.assessmentProxy = assessmentProxy;
    }

    @ModelAttribute("assessmentDto")
    public AssessmentDTO assessmentDto() {
        return new AssessmentDTO();
    }

    @GetMapping("/infoPatient/{id}/assessment")
    public String getRapport(@PathVariable Integer id, Model model) {
        model.addAttribute("rapport", assessmentProxy.getAssessmentByPatientId(id));
        return "assessments/assessment";
    }
}
