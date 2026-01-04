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
            if (row[0].equalsIgnoreCase("clinician_id")) continue;

            clinicians.add(new Clinician(
                    row[0], row[1], row[2], row[3], row[4], row[5],
                    row[6], row[7], row[8], row[9], row[10], row[11]
            ));
        }
    }

    public List<Clinician> getAllClinicians() {
        return clinicians;
    }

    public void addClinician(Clinician clinician) {
        clinicians.add(clinician);
        rewriteCSV();
    }

    public void removeClinician(Clinician clinician) {
        clinicians.remove(clinician);
        rewriteCSV();
    }

    private void rewriteCSV() {

        CSVWriter.overwrite(FILE_PATH,
                "clinician_id,first_name,last_name,title,speciality," +
                "gmc_number,phone_number,email,workplace_id,workplace_type," +
                "employment_status,start_date"
        );

        for (Clinician c : clinicians) {
            CSVWriter.append(FILE_PATH, String.join(",",
                    c.getClinicianId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getTitle(),
                    c.getSpeciality(),
                    c.getGmcNumber(),
                    c.getPhoneNumber(),
                    c.getEmail(),
                    c.getWorkplaceId(),
                    c.getWorkplaceType(),
                    c.getEmploymentStatus(),
                    c.getStartDate()
            ));
        }
    }
}
