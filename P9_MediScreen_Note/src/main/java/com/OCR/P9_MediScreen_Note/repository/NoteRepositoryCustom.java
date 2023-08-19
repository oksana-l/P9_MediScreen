package com.OCR.P9_MediScreen_Note.repository;

import com.OCR.P9_MediScreen_Note.model.Note;

import java.util.List;

public interface NoteRepositoryCustom {
    List<Note> findAllNotesByPatientId(Integer patientId);
}
