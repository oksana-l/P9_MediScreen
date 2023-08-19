package com.OCR.P9_MediScreen_Note.repository;

import com.OCR.P9_MediScreen_Note.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NoteRepositoryImpl implements NoteRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NoteRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public List<Note> findAllNotesByPatientId(Integer patientId) {
        Query query = new Query(Criteria.where("patientId").is(patientId));
        return mongoTemplate.find(query, Note.class);
    }

}
