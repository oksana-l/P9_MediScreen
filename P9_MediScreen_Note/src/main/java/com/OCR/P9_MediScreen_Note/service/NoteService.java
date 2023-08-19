package com.OCR.P9_MediScreen_Note.service;
import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotesByPatientId(Integer patientId);
    NoteDTO findNoteById(String id);
    NoteDTO create(NoteDTO noteDto);
    NoteDTO update(String id, NoteDTO noteDto);
    void delete(String id);
}
