package controller;

import java.util.List;
import model.Patient;
import model.PatientRepository;

public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void loadPatients() {
        patientRepository.loadPatients();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public void addPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    public void deletePatient(Patient patient) {
        patientRepository.removePatient(patient);
    }
}
