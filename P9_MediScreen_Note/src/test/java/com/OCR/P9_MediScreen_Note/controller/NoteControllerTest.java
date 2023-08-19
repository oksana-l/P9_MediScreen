package com.OCR.P9_MediScreen_Note.controller;

import com.OCR.P9_MediScreen_Note.model.Note;
import com.OCR.P9_MediScreen_Note.model.dto.NoteDTO;
import com.OCR.P9_MediScreen_Note.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Note note1;
    private Note note2;
    private List<NoteDTO> mockNotes;
    @MockBean
    private NoteService noteService;
    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
        note1 = new Note("1r3yw8", 1, LocalDate.now().toString(),
                "Le patient déclare qu'il se sent très bien" +
                "Poids égal ou inférieur au poids recommandé");
        note2 = new Note("1r3yw9", 1, LocalDate.now().toString(),
                "Le patient déclare qu'il se sent fatigué pendant la journée" +
                "Il se plaint également de douleurs musculaires" +
                "Tests de laboratoire indiquant une microalbumine élevée");
        mockNotes = new ArrayList<>();
        mockNotes.add(new NoteDTO(note1));
        mockNotes.add(new NoteDTO(note2));
    }

    @Test
    public void shouldGetListNotesByPatientsTest() throws Exception {
        int patientId = 1;
        when(noteService.getAllNotesByPatientId(1)).thenReturn(mockNotes);

        MvcResult result = mockMvc.perform(get("/notes")
                        .param("patientId", String.valueOf(patientId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldGetNoteByIdTest() throws Exception {
        String noteId = "1r3yw8";
        NoteDTO mockNote = new NoteDTO(note1);

        when(noteService.findNoteById(noteId)).thenReturn(mockNote);

        MvcResult result = mockMvc.perform(get("/notes/" + noteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        NoteDTO responseNote = new ObjectMapper().readValue(responseContent, NoteDTO.class);

        assertEquals("1r3yw8", responseNote.getId());
        assertEquals(LocalDate.now().toString(), responseNote.getDate());
    }

    @Test
    public void shouldAddNoteTest() throws Exception {
        NoteDTO mockNote = new NoteDTO(note1);

        when(noteService.create(new NoteDTO(note1))).thenReturn(mockNote);

        MvcResult result = mockMvc.perform(post("/notes")
                        .param("patientId", "1")
                        .content("{" +
                                "\"note\": \"Le patient déclare qu'il se sent fatigué pendant la journée. " +
                                "Il se plaint également de douleurs musculaires. " +
                                "Tests de laboratoire indiquant une microalbumine élevée\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
    }

    @Test
    public void shouldUpdateNote() throws Exception {
        String noteId = "1r3yw8";
        NoteDTO mockUpdatedNote = new NoteDTO(note1);

        when(noteService.update(noteId, new NoteDTO(note1))).thenReturn(mockUpdatedNote);

        MvcResult result = mockMvc.perform(put("/notes/{id}", noteId)
                        .content("{" +
                                "\"patientId\": 1," +
                                "\"date\": \"" + LocalDate.now().toString() + "\"," +
                                "\"note\": \"" + note1.getNote() + "\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();

    }

    @Test
    public void testDeleteNote() throws Exception {
        String noteId = "1r3yw8";
        doNothing().when(noteService).delete(noteId);

        mockMvc.perform(delete("/notes/{id}", noteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
