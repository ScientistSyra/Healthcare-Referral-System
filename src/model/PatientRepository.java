package model;

import java.util.*;
import util.CSVReader;

public class PatientRepository {

    private List<Patient> patients = new ArrayList<>();
    private static final String FILE_PATH = "data/patients.csv";

    public void loadPatients() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        patients.clear();

        for (String[] row : rows) {
            // Skip header row
            if (row[0].equalsIgnoreCase("ID") || row[0].equalsIgnoreCase("patient_id")) {
                continue;
            }

            Patient patient = new Patient(
                    row[0], // Patient ID
                    row[1], // Full Name
                    row[2], // DOB
                    row[3], // NHS Number
                    row[4], // Gender
                    row[5], // Phone
                    row[6], // Email
                    row[7]  // GP Surgery ID
            );

            patients.add(patient);
        }
    }


    public List<Patient> getAllPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public Patient findByNhsNumber(String nhsNumber) {
        for (Patient p : patients) {
            if (p.getNhsNumber().equals(nhsNumber)) {
                return p;
            }
        }
        return null;
    }
}
