package com.OCR.P9_MediScreen_Note.service;

import com.OCR.P9_MediScreen_Note.model.Note;
import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Note.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class noteServiceTest {
    private NoteService noteService;
    private NoteRepository noteRepository;
    private Note note1;
    private Note note2;
    private Note note3;
    private Note note4;
    private Note note5;
    private Note note6;
    private Note note7;
    private Note note8;
    private List<Note> notesPatient1 = new ArrayList<>();
    private List<Note> notesPatient2 = new ArrayList<>();
    private List<Note> notesPatient3 = new ArrayList<>();
    private List<Note> notesPatient4 = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        noteRepository = mock(NoteRepository.class);
        noteService = new NoteServiceImpl(noteRepository);
        note1 = new Note("1r3yw8", 1, LocalDate.now().toString(),
                "Patient: TestNone Practitioner's notes/recommendations: " +
                        "Patient states that they are 'feeling terrific' " +
                        "Weight at or below recommended level");
        note2 = new Note(null, 2, LocalDate.now().toString(),
                "Patient: TestBorderline Practitioner's notes/recommendations: " +
                        "Patient states that they are feeling a great deal of stress at work " +
                        "Patient also complains that their hearing seems Abnormal as of late");
        note3 = new Note("3jwhs5ws", 2, LocalDate.now().toString(),
                "Patient: TestBorderline Practitioner's notes/recommendations: " +
                        "Patient states that they have had a Reaction to medication within last 3 months " +
                        "Patient also complains that their hearing continues to be problematic");
        note4 = new Note(null, 3, LocalDate.now().toString(),
                "Patient: TestInDanger Practitioner's notes/recommendations: " +
                        "Patient states that they are short term Smoker ");
        note5 = new Note(null, 3, LocalDate.now().toString(),
                "Patient: TestInDanger Practitioner's notes/recommendations: " +
                        "Patient states that they quit within last year " +
                        "Patient also complains that of Abnormal breathing spells " +
                        "Lab reports Cholesterol LDL high");
        note6 = new Note(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that walking up stairs has become difficult " +
                        "Patient also complains that they are having shortness of breath " +
                        "Lab results indicate Antibodies present elevated Reaction to medication");
        note7 = new Note(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that they are experiencing back pain when seated for a long time");
        note8 = new Note(null, 4, LocalDate.now().toString(),
                "Patient: TestEarlyOnset Practitioner's notes/recommendations: " +
                        "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
        notesPatient1.add(note1);
        notesPatient2.add(note2);
        notesPatient2.add(note3);
        notesPatient3.add(note4);
        notesPatient3.add(note5);
        notesPatient4.add(note6);
        notesPatient4.add(note7);
        notesPatient4.add(note8);
    }

    @Test
    public void shouldGetAllNotesByPatientIdTest() {
        when(noteRepository.findAllNotesByPatientId(4)).thenReturn(notesPatient4);
        List<NoteDTO> notes = noteService.getAllNotesByPatientId(4);

        Assertions.assertEquals(3, notes.size());
        Assertions.assertEquals(4, notes.get(0).getPatientId());
        Assertions.assertEquals(LocalDate.now().toString(),notes.get(0).getDate());
    }
    @Test
    public void shouldFindNoteByIdTest() {
        when(noteRepository.findById(any())).thenReturn(Optional.ofNullable(note1));

        NoteDTO noteDto = noteService.findNoteById("1r3yw8");

        Assertions.assertEquals(note1.getNote(), noteDto.getNote());
        Assertions.assertEquals(note1.getDate(), noteDto.getDate());
        Assertions.assertEquals(note1.getPatientId(), noteDto.getPatientId());
    }

    @Test
    public void shouldCreateNoteTest() {
        when(noteRepository.save(any())).thenReturn(note2);

        NoteDTO noteDto = noteService.create(new NoteDTO(note2));

        Assertions.assertEquals(note2.getNote(), noteDto.getNote());
        Assertions.assertEquals(note2.getDate(), noteDto.getDate());
        Assertions.assertEquals(note2.getPatientId(), noteDto.getPatientId());
    }

    @Test
    public void shouldUpdateNoteTest() {
        when(noteRepository.findById("3jwhs5ws")).thenReturn(Optional.ofNullable(note3));
        when(noteRepository.save(note3)).thenReturn(note4);

        NoteDTO noteDto = noteService.update("3jwhs5ws", new NoteDTO(note4));

        Assertions.assertEquals(note4.getNote(), noteDto.getNote());
        Assertions.assertEquals(note4.getDate(), noteDto.getDate());
        Assertions.assertEquals(note4.getPatientId(), noteDto.getPatientId());
    }

    @Test
    public void shouldDeleteNoteTest() {
        noteService.delete("3jwhs5ws");
        verify(noteRepository).deleteById("3jwhs5ws");
    }
}
