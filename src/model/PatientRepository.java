package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private List<Patient> patients = new ArrayList<>();
    private static final String FILE_PATH = "data/patients.csv";

    // ================= LOAD =================
    public void loadPatients() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        patients.clear();

        for (String[] row : rows) {

            // Skip header
            if (row[0].equalsIgnoreCase("patient_id")) {
                continue;
            }

            Patient patient = new Patient(
                    row[0],  // patient_id
                    row[1],  // first_name
                    row[2],  // last_name
                    row[3],  // date_of_birth
                    row[4],  // nhs_number
                    row[5],  // gender
                    row[6],  // phone_number
                    row[7],  // email
                    row[8],  // address
                    row[9],  // postcode
                    row[10], // emergency_contact_name
                    row[11], // emergency_contact_phone
                    row[12], // registration_date
                    row[13]  // gp_surgery_id
            );

            patients.add(patient);
        }
    }

    // ================= READ =================
    public List<Patient> getAllPatients() {
        return patients;
    }

    // ================= CREATE =================
    public void addPatient(Patient patient) {
        patients.add(patient);
        rewriteCSV();
    }

    // ================= UPDATE =================
    public void updatePatient(Patient oldPatient, Patient newPatient) {
        int index = patients.indexOf(oldPatient);
        if (index != -1) {
            patients.set(index, newPatient);
            rewriteCSV();
        }
    }

    // ================= DELETE =================
    public void removePatient(Patient patient) {
        patients.remove(patient);
        rewriteCSV();
    }

    // ================= FIND =================
    public Patient findByNhsNumber(String nhsNumber) {
        for (Patient p : patients) {
            if (p.getNhsNumber().equals(nhsNumber)) {
                return p;
            }
        }
        return null;
    }

    // ================= CSV WRITE =================
    private void rewriteCSV() {

        // Write header
        CSVWriter.overwrite(FILE_PATH,
                "patient_id,first_name,last_name,date_of_birth,nhs_number,gender," +
                "phone_number,email,address,postcode,emergency_contact_name," +
                "emergency_contact_phone,registration_date,gp_surgery_id"
        );

        // Write records
        for (Patient p : patients) {
            String line = String.join(",",
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getGender(),
                    p.getPhone(),
                    p.getEmail(),
                    p.getAddress(),
                    p.getPostcode(),
                    p.getEmergencyContactName(),
                    p.getEmergencyContactPhone(),
                    p.getRegistrationDate(),
                    p.getGpSurgeryId()
            );
            CSVWriter.append(FILE_PATH, line);
        }
    }
}
