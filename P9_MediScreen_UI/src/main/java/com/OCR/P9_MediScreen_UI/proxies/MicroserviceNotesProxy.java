package com.OCR.P9_MediScreen_UI.proxies;

import com.OCR.P9_MediScreen_UI.model.NoteDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-notes")
public interface MicroserviceNotesProxy {

    @GetMapping("/notes")
    List<NoteDTO> listNotes(@RequestParam Integer patientId);

    @GetMapping("/notes/{id}")
    NoteDTO getNoteById(@PathVariable("id") final String id);

    @PostMapping("/notes")
    NoteDTO addNote(@RequestBody NoteDTO noteDto,
                    @RequestParam Integer patientId);

    @PutMapping("/notes/{id}")
    NoteDTO updateNote(@PathVariable("id") final String id,
                       @RequestBody @Valid NoteDTO noteDto);

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable("id") String id);
}
