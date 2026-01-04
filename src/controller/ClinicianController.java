package controller;

import java.util.List;
import model.Clinician;
import model.ClinicianRepository;

public class ClinicianController {

    private ClinicianRepository repository;

    public ClinicianController(ClinicianRepository repository) {
        this.repository = repository;
    }

    public void loadClinicians() {
        repository.loadClinicians();
    }

    public List<Clinician> getAllClinicians() {
        return repository.getAllClinicians();
    }

    public void addClinician(Clinician clinician) {
        repository.addClinician(clinician);
    }

    public void deleteClinician(Clinician clinician) {
        repository.removeClinician(clinician);
    }
}
