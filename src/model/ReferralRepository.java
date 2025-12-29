package model;

import util.CSVReader;
import util.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReferralRepository {

    private List<Referral> referrals = new ArrayList<>();

    private static final String CSV_FILE = "data/referrals.csv";
    private static final String OUTPUT_FILE = "output/referrals_output.txt";

    public void loadReferrals() {
        List<String[]> rows = CSVReader.read(CSV_FILE);
        referrals.clear();

        for (String[] row : rows) {

            // Skip header
            if (row[0].equalsIgnoreCase("referral_id")) {
                continue;
            }

            Referral referral = new Referral(
                    row[0],  // referral_id
                    row[1],  // patient_id
                    row[2],  // referring_clinician_id
                    row[5],  // referred_to_facility_id
                    row[6],  // referral_date
                    row[7],  // urgency_level
                    row[8],  // referral_reason
                    row[9],  // clinical_summary
                    row[11]  // status
            );

            referrals.add(referral);
        }
    }

    public List<Referral> getAllReferrals() {
        return referrals;
    }

    public void addReferral(Referral referral) {
        referrals.add(referral);
        saveReferral(referral);
    }

    public void deleteReferral(Referral referral) {
        referrals.remove(referral);
    }

    private void saveReferral(Referral referral) {

        String csvLine = String.join(",",
                referral.getReferralId(),
                referral.getPatientId(),
                referral.getReferringClinicianId(),
                "", "",                              // clinician + facility placeholders
                referral.getReferredToFacilityId(),
                referral.getReferralDate(),
                referral.getUrgencyLevel(),
                referral.getReferralReason(),
                referral.getClinicalSummary(),
                "",                                  // requested investigations
                referral.getStatus()
        );

        CSVWriter.append(CSV_FILE, csvLine);

        writeReferralText(referral);
    }

    private void writeReferralText(Referral referral) {
        try (FileWriter fw = new FileWriter(OUTPUT_FILE, true)) {

            fw.write("----- REFERRAL -----\n");
            fw.write("Referral ID: " + referral.getReferralId() + "\n");
            fw.write("Patient ID: " + referral.getPatientId() + "\n");
            fw.write("Referring Clinician: " + referral.getReferringClinicianId() + "\n");
            fw.write("Referred To Facility: " + referral.getReferredToFacilityId() + "\n");
            fw.write("Urgency: " + referral.getUrgencyLevel() + "\n");
            fw.write("Reason: " + referral.getReferralReason() + "\n");
            fw.write("Status: " + referral.getStatus() + "\n");
            fw.write("Clinical Summary:\n");
            fw.write(referral.getClinicalSummary() + "\n");
            fw.write("--------------------\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
