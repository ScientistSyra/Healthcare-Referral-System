package controller;

import java.util.List;
import model.Prescription;
import model.PrescriptionRepository;

public class PrescriptionController {

    private PrescriptionRepository prescriptionRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public void loadPrescriptions() {
        prescriptionRepository.loadPrescriptions();
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.getAllPrescriptions();
    }

    public void addPrescription(Prescription prescription) {
        prescriptionRepository.addPrescription(prescription);
    }

    public void deletePrescription(Prescription prescription) {
        prescriptionRepository.removePrescription(prescription);
    }
}
