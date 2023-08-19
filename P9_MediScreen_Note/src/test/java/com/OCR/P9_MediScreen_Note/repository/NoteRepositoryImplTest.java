package com.OCR.P9_MediScreen_Note.repository;

import com.OCR.P9_MediScreen_Note.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class NoteRepositoryImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private NoteRepositoryImpl noteRepository;

    @BeforeEach
    public void setUp() {
        noteRepository = new NoteRepositoryImpl(mongoTemplate);
    }

    @Test
    public void testFindAllNotesByPatientId() {

        Integer patientId = 2;

        List<Note> notes = noteRepository.findAllNotesByPatientId(patientId);

        assertEquals(2, notes.size());
    }
}
