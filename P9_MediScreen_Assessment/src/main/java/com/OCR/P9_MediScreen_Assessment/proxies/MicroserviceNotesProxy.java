package com.OCR.P9_MediScreen_Assessment.proxies;

import com.OCR.P9_MediScreen_Assessment.model.dto.NoteDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-notes")
public interface MicroserviceNotesProxy {

    @GetMapping("/notes")
    List<NoteDTO> listNotes(@RequestParam Integer patientId);
}
