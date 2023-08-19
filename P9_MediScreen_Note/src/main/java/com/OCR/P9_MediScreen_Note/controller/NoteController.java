package com.OCR.P9_MediScreen_Note.controller;

import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Note.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> listNotesByPatientId(@RequestParam Integer patientId) {
        List<NoteDTO> notes = noteService.getAllNotesByPatientId(patientId);
        return ResponseEntity.ok(notes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable String id) {
        return ResponseEntity.ok(noteService.findNoteById(id));
    }

    @PostMapping
    public ResponseEntity<NoteDTO> addNote(@RequestBody @Valid NoteDTO noteDto,
                                           @RequestParam Integer patientId) {
        noteDto.setId(null);
        noteDto.setPatientId(patientId);
        NoteDTO savedNoteDto = noteService.create(noteDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build()
                .toUri();
        return ResponseEntity.created(location)
                             .body(savedNoteDto);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<NoteDTO> addNoteForm(@Valid NoteDTO noteDto,
                                               @RequestParam Integer patientId) {
        return addNote(noteDto, patientId);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable String id,
                                              @RequestBody @Valid NoteDTO noteDto) {
        NoteDTO patientUpdated = noteService.update(id, noteDto);
        return ResponseEntity.ok(patientUpdated);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
