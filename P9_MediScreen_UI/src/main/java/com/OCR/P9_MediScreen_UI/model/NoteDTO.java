package com.OCR.P9_MediScreen_UI.model;

    public class NoteDTO {
        private String id;
        private Integer patientId;
        private String date;
        private String note;

        public NoteDTO() {
        }

        public NoteDTO(String id, Integer patientId, String date, String note) {
            this.id = id;
            this.patientId = patientId;
            this.date = date;
            this.note = note;
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

}
