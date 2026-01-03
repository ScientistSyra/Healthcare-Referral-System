package controller;

import java.util.List;
import model.Facility;
import model.FacilityRepository;

public class FacilityController {

    private FacilityRepository facilityRepository;

    public FacilityController(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void loadFacilities() {
        facilityRepository.loadFacilities();
    }

    public List<Facility> getAllFacilities() {
        return facilityRepository.getAllFacilities();
    }

    public void addFacility(Facility facility) {
        facilityRepository.addFacility(facility);
    }

    public void deleteFacility(Facility facility) {
        facilityRepository.deleteFacility(facility);
    }
}
