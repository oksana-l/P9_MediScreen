package com.OCR.P9_MediScreen_UI.controller;



import com.OCR.P9_MediScreen_UI.model.NoteDTO;
import com.OCR.P9_MediScreen_UI.model.PatientDTO;
import com.OCR.P9_MediScreen_UI.proxies.MicroserviceNotesProxy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UiNotesController {
    @Autowired
    private final MicroserviceNotesProxy notesProxy;

    public UiNotesController(MicroserviceNotesProxy notesProxy) {
        this.notesProxy = notesProxy;
    }

    @ModelAttribute("noteDto")
    public NoteDTO noteDto() {
        return new NoteDTO();
    }
    @ModelAttribute("patientDto")
    public PatientDTO patientDto() {
        return new PatientDTO();
    }

    @GetMapping("/infoPatient/{patientId}/")
    public String allNotes(@PathVariable Integer patientId, Model model) {
        List<NoteDTO> notes = notesProxy.listNotes(patientId);
        PatientDTO patientDto = new PatientDTO();
        patientDto.setNotes(notes);
        patientDto.setId(patientId);
        model.addAttribute("patientDto", patientDto);
        model.addAttribute("notes", notes);
        return "notes/listNotes";
    }

    @GetMapping("/note/{id}")
    public String note(@PathVariable String id, Model model) {
        model.addAttribute("noteDto", notesProxy.getNoteById(id));
        return "notes/note";
    }

    @GetMapping("/infoPatient/{id}/note")
    public String createNote(@PathVariable Integer id, Model model) {
        NoteDTO noteDto = new NoteDTO();
        noteDto.setPatientId(id);
        model.addAttribute("noteDto", noteDto);
        return "notes/newNote";
    }

    @PostMapping("/infoPatient/{id}/note")
    public String addNote(@Valid NoteDTO noteDto,
                          @PathVariable Integer id) {
        notesProxy.addNote(noteDto, id);
        return "redirect:/infoPatient/" + noteDto.getId() + "/";
    }

    @GetMapping("/note/{id}/edit")
    public String updateNote(@PathVariable String id, Model model) {
        model.addAttribute("noteDto", notesProxy.getNoteById(id));
        return "notes/updateNote";
    }

    @PostMapping("/note/{id}/edit")
    public String updateNote(@PathVariable String id,
                             @Valid NoteDTO noteDto) {
        notesProxy.updateNote(id, noteDto);
        return "redirect:/note/" + id;
    }

    @GetMapping("/note/{id}/delete")
    public String deleteNote(@PathVariable String id) {
        Integer patientId = notesProxy.getNoteById(id).getPatientId();
        notesProxy.deleteNote(id);
        return "redirect:/infoPatient/" + patientId + "/";
    }
}
