package com.OCR.P9_MediScreen_Note.service;

import com.OCR.P9_MediScreen_Note.model.Note;
import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Note.repository.NoteRepository;
import com.OCR.P9_MediScreen_Note.repository.NoteRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<NoteDTO> getAllNotesByPatientId(Integer patientId) {
        List<NoteDTO> notes = new ArrayList<>();
        noteRepository.findAllNotesByPatientId(patientId).forEach(note -> notes.add(new NoteDTO(note)));
        return notes;
    }
    @Override
    public NoteDTO findNoteById(String id) {
        NoteDTO noteDTO = new NoteDTO(noteRepository.findById(id).get());
        return noteDTO;
    }
    @Override
    public NoteDTO create(NoteDTO noteDto) {
        noteDto.setDate(LocalDate.now().toString());
        return new NoteDTO(noteRepository.save(new Note(noteDto)));
    }

    @Override
    public NoteDTO update(String id, NoteDTO noteDto) {
        Note noteForUpdate = noteRepository.findById(id).get();
        noteForUpdate.setDate(LocalDate.now().toString());
        noteForUpdate.setNote(noteDto.getNote());
        return new NoteDTO(noteRepository.save(noteForUpdate));
    }

    @Override
    public void delete(String id) {
        noteRepository.deleteById(id);
    }

}
