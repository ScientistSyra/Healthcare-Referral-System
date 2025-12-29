package controller;

import java.util.List;
import model.Clinician;
import model.ClinicianRepository;

public class ClinicianController {

    private ClinicianRepository clinicianRepository;

    public ClinicianController(ClinicianRepository clinicianRepository) {
        this.clinicianRepository = clinicianRepository;
    }

    public void loadClinicians() {
        clinicianRepository.loadClinicians();
    }

    public List<Clinician> getAllClinicians() {
        return clinicianRepository.getAllClinicians();
    }

    public void addClinician(Clinician clinician) {
        clinicianRepository.addClinician(clinician);
    }

    public void deleteClinician(Clinician clinician) {
        clinicianRepository.removeClinician(clinician);
    }
}
