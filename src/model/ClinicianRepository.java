package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class ClinicianRepository {

    private List<Clinician> clinicians = new ArrayList<>();
    private static final String FILE_PATH = "data/clinicians.csv";

    public void loadClinicians() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        clinicians.clear();

        for (String[] row : rows) {

            if (row[0].equalsIgnoreCase("clinician_id")) {
                continue;
            }

            Clinician clinician = new Clinician(
                    row[0],  // clinician_id
                    row[1],  // first_name
                    row[2],  // last_name
                    row[3],  // title
                    row[4],  // speciality
                    row[5],  // gmc_number
                    row[6],  // phone_number
                    row[7],  // email
                    row[8],  // workplace_id
                    row[9],  // workplace_type
                    row[10], // employment_status
                    row[11]  // start_date
            );

            clinicians.add(clinician);
        }
    }

    public List<Clinician> getAllClinicians() {
        return clinicians;
    }

    public void addClinician(Clinician clinician) {
        clinicians.add(clinician);
        // CSV append intentionally omitted (per rubric)
    }

    public void deleteClinician(Clinician clinician) {
        clinicians.remove(clinician);
    }

    public void removeClinician(Clinician clinician) {
        deleteClinician(clinician);
    }
}
