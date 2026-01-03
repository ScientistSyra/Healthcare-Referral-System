package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {

    private List<Prescription> prescriptions = new ArrayList<>();
    private static final String FILE_PATH = "data/prescriptions.csv";

    public void loadPrescriptions() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        prescriptions.clear();

        for (String[] row : rows) {

            if (row[0].equalsIgnoreCase("clinician_id")) {
                continue;
            }

            prescriptions.add(new Prescription(
                    row[0], row[1], row[2], row[3], row[4],
                    row[5], row[6], row[7], row[8],
                    row[9], row[10], row[11], row[12]
            ));
        }
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        rewriteCSV();
    }

    public void deletePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
        rewriteCSV();
    }

    public void removePrescription(Prescription prescription) {
        deletePrescription(prescription);
    }

    private void rewriteCSV() {

        CSVWriter.overwrite(FILE_PATH,
                "clinician_id,appointment_id,prescription_date,medication_name," +
                "dosage,frequency,duration_days,quantity,instructions," +
                "pharmacy_name,status,issue_date,collection_date"
        );

        for (Prescription p : prescriptions) {
            CSVWriter.append(FILE_PATH, String.join(",",
                    p.getClinicianId(),
                    p.getAppointmentId(),
                    p.getPrescriptionDate(),
                    p.getMedicationName(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getDurationDays(),
                    p.getQuantity(),
                    p.getInstructions(),
                    p.getPharmacyName(),
                    p.getStatus(),
                    p.getIssueDate(),
                    p.getCollectionDate()
            ));
        }
    }
}
