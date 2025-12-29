package model;

public class Prescription {

    private String prescriptionId;
    private String patientId;
    private String medication;
    private String dosage;
    private String pharmacy;
    private String collectionStatus;

    public Prescription(String prescriptionId, String patientId,
                        String medication, String dosage,
                        String pharmacy, String collectionStatus) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
        this.pharmacy = pharmacy;
        this.collectionStatus = collectionStatus;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getMedication() {
        return medication;
    }

    public String getDosage() {
        return dosage;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    @Override
    public String toString() {
        return prescriptionId + " - " + medication;
    }
}
