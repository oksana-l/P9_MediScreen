package com.OCR.P9_MediScreen_Note.model.dto;

import com.OCR.P9_MediScreen_Note.model.Note;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.Objects;

public class NoteDTO {

    private String id;
    private Integer patientId;
    private String date;
    private String note;

    public NoteDTO() {
    }

    public NoteDTO(final String id, final Integer patientId, final String date, final String note) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.patientId = note.getPatientId();
        this.date = note.getDate();
        this.note = note.getNote();
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return this.patientId;
    }

    public void setPatientId(final Integer patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final NoteDTO noteDTO = (NoteDTO) o;
        return Objects.equals(id, noteDTO.id) && Objects.equals(patientId, noteDTO.patientId) && Objects.equals(date, noteDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, date, note);
    }
}
