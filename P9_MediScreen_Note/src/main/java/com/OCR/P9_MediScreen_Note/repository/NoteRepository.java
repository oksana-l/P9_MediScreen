package com.OCR.P9_MediScreen_Note.repository;

import com.OCR.P9_MediScreen_Note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String>, NoteRepositoryCustom {
}
