package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class ReferralRepository {

    private List<Referral> referrals = new ArrayList<>();
    private static final String FILE_PATH = "data/referrals.csv";

    public void loadReferrals() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        referrals.clear();

        // ✅ ALWAYS skip header row
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            referrals.add(new Referral(
                    row[0], row[1], row[2], row[3],
                    row[4], row[5], row[6], row[7],
                    row[8], row[9], row[10], row[11],
                    row[12], row[13], row[14], row[15]
            ));
        }
    }

    public List<Referral> getAllReferrals() {
        return referrals;
    }

    public void addReferral(Referral referral) {
        referrals.add(referral);
        rewriteCSV();
    }

    public void deleteReferral(Referral referral) {
        referrals.remove(referral);
        rewriteCSV();
    }

    private void rewriteCSV() {

        CSVWriter.overwrite(FILE_PATH,
                "referral_id,patient_id,referring_clinician_id,referred_to_clinician_id," +
                "referring_facility_id,referred_to_facility_id,referral_date,urgency_level," +
                "referral_reason,clinical_summary,requested_investigations,status," +
                "appointment_id,notes,created_date,last_updated"
        );

        for (Referral r : referrals) {
            CSVWriter.append(FILE_PATH, String.join(",",
                    r.getReferralId(),
                    r.getPatientId(),
                    r.getReferringClinicianId(),
                    r.getReferredToClinicianId(),
                    r.getReferringFacilityId(),
                    r.getReferredToFacilityId(),
                    r.getReferralDate(),
                    r.getUrgencyLevel(),
                    r.getReferralReason(),
                    r.getClinicalSummary(),
                    r.getRequestedInvestigations(),
                    r.getStatus(),
                    r.getAppointmentId(),
                    r.getNotes(),
                    r.getCreatedDate(),
                    r.getLastUpdated()
            ));
        }
    }
}
