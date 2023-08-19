package com.OCR.P9_MediScreen_Assessment.model.dto;

public class PatientRapportDto {
    private String firstName;
    private String lastName;
    private long age;
    private String riskCategory;

    public PatientRapportDto() {
    }

    public PatientRapportDto(final String firstName, final String lastName, final long age, final String riskCategory) {
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

    @Override
    public String toString() {
        return "Patient : " + firstName + " " + lastName +
                "(" + age + ")" +
                "diabetes assessment is: " + riskCategory;
    }
}
