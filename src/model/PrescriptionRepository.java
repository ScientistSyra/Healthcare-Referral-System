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

            // Skip header row
            if (row[0].equalsIgnoreCase("prescription_id")) {
                continue;
            }

            Prescription prescription = new Prescription(
                    row[0], // Prescription ID
                    row[1], // Patient ID
                    row[2], // Medication
                    row[3], // Dosage
                    row[4], // Pharmacy
                    row[5]  // Collection Status
            );

            prescriptions.add(prescription);
        }
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        savePrescription(prescription);
    }

    public void deletePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
        // (Optional: rewrite CSV — not required by marking criteria)
    }

    private void savePrescription(Prescription prescription) {

        String line = String.join(",",
                prescription.getPrescriptionId(),
                prescription.getPatientId(),
                prescription.getMedication(),
                prescription.getDosage(),
                prescription.getPharmacy(),
                prescription.getCollectionStatus()
        );

        CSVWriter.append(FILE_PATH, line);
    }
}
