package com.OCR.P9_MediScreen_Note.model;

import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private Integer patientId;
    private String date;
    private String note;

    public Note() {
    }

    public Note(final String id, final Integer patientId, final String date, final String note) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.note = note;
    }

    public Note(NoteDTO noteDto) {
        this.id = noteDto.getId();
        this.patientId = noteDto.getPatientId();
        this.date = noteDto.getDate();
        this.note = noteDto.getNote();
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
        final Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(patientId, note.patientId) && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, date);
    }
}