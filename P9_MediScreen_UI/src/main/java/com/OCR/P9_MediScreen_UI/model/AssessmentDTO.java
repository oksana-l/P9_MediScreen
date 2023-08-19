package com.OCR.P9_MediScreen_UI.model;

public class AssessmentDTO {
    private String firstName;
    private String lastName;
    private long age;
    private String riskCategory;

    public AssessmentDTO() {
    }
    public AssessmentDTO(final String firstName, final String lastName, final long age, final String riskCategory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.riskCategory = riskCategory;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return this.age;
    }

    public void setAge(final long age) {
        this.age = age;
    }

    public String getRiskCategory() {
        return this.riskCategory;
    }

    public void setRiskCategory(final String riskCategory) {
        this.riskCategory = riskCategory;
    }
}
