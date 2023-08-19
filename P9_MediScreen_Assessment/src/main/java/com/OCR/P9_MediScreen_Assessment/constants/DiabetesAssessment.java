package com.OCR.P9_MediScreen_Assessment.constants;

public enum DiabetesAssessment {
    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In danger"),
    EARLY_ONSET("Early onset");
    private String riskLevel;

    DiabetesAssessment(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskLevel() {
        return this.riskLevel;
    }
}
