package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class FacilityRepository {

    private List<Facility> facilities = new ArrayList<>();
    private static final String FILE_PATH = "data/facilities.csv";

    public void loadFacilities() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        facilities.clear();

        // ✅ Skip header row explicitly
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            facilities.add(new Facility(
                    row[0],  // facility_id
                    row[1],  // facility_name
                    row[2],  // facility_type
                    row[3],  // address
                    row[4],  // postcode
                    row[5],  // phone_number
                    row[6],  // email
                    row[7],  // opening_hours
                    row[8],  // manager_name
                    row[9],  // capacity
                    row[10]  // specialities_offered
            ));
        }
    }

    public List<Facility> getAllFacilities() {
        return facilities;
    }

    public void addFacility(Facility facility) {
        facilities.add(facility);
        rewriteCSV();
    }

    public void deleteFacility(Facility facility) {
        facilities.remove(facility);
        rewriteCSV();
    }

    private void rewriteCSV() {

        CSVWriter.overwrite(FILE_PATH,
                "facility_id,facility_name,facility_type,address,postcode," +
                "phone_number,email,opening_hours,manager_name,capacity,specialities_offered"
        );

        for (Facility f : facilities) {
            CSVWriter.append(FILE_PATH, String.join(",",
                    f.getFacilityId(),
                    f.getFacilityName(),
                    f.getFacilityType(),
                    f.getAddress(),
                    f.getPostcode(),
                    f.getPhoneNumber(),
                    f.getEmail(),
                    f.getOpeningHours(),
                    f.getManagerName(),
                    f.getCapacity(),
                    f.getSpecialitiesOffered()
            ));
        }
    }
}
