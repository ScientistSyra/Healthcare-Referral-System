package model;

import java.util.*;
import util.CSVReader;

public class PatientRepository {

    private List<Patient> patients = new ArrayList<>();
    private static final String FILE_PATH = "data/patients.csv";

    public void loadPatients() {
        List<String[]> rows = CSVReader.read("data/patients.csv");
        patients.clear();

        for (String[] row : rows) {

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
