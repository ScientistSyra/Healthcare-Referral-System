package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.ReferralController;
import model.Referral;

public class ReferralPanel extends JPanel {

    private ReferralController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public ReferralPanel(ReferralController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "referral_id",
                        "patient_id",
                        "referring_clinician_id",
                        "referred_to_clinician_id",
                        "referring_facility_id",
                        "referred_to_facility_id",
                        "referral_date",
                        "urgency_level",
                        "referral_reason",
                        "clinical_summary",
                        "requested_investigations",
                        "status",
                        "appointment_id",
                        "notes",
                        "created_date",
                        "last_updated"
                }, 0
        );

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /* =========================
       LOAD TABLE DATA
       ========================= */
    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Referral r : controller.getAllReferrals()) {
            tableModel.addRow(new Object[]{
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
            });
        }
    }

    /* =========================
       BUTTON PANEL
       ========================= */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Referral");
        JButton deleteBtn = new JButton("Delete Referral");

        addBtn.addActionListener(e -> addReferral());
        deleteBtn.addActionListener(e -> deleteReferral());

        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    /* =========================
       ADD REFERRAL (FIXED)
       ========================= */
    private void addReferral() {

        JTextField referralId = new JTextField();
        JTextField patientId = new JTextField();
        JTextField referringClinicianId = new JTextField();
        JTextField referredToClinicianId = new JTextField();
        JTextField referringFacilityId = new JTextField();
        JTextField referredToFacilityId = new JTextField();
        JTextField referralDate = new JTextField();
        JTextField urgencyLevel = new JTextField();
        JTextField referralReason = new JTextField();
        JTextArea clinicalSummary = new JTextArea(3, 20);
        JTextField requestedInvestigations = new JTextField();
        JTextField status = new JTextField();
        JTextField appointmentId = new JTextField();
        JTextField notes = new JTextField();
        JTextField createdDate = new JTextField();
        JTextField lastUpdated = new JTextField();

        Object[] fields = {
                "Referral ID:", referralId,
                "Patient ID:", patientId,
                "Referring Clinician ID:", referringClinicianId,
                "Referred To Clinician ID:", referredToClinicianId,
                "Referring Facility ID:", referringFacilityId,
                "Referred To Facility ID:", referredToFacilityId,
                "Referral Date:", referralDate,
                "Urgency Level:", urgencyLevel,
                "Referral Reason:", referralReason,
                "Clinical Summary:", new JScrollPane(clinicalSummary),
                "Requested Investigations:", requestedInvestigations,
                "Status:", status,
                "Appointment ID:", appointmentId,
                "Notes:", notes,
                "Created Date:", createdDate,
                "Last Updated:", lastUpdated
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Referral",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Referral referral = new Referral(
                    referralId.getText(),
                    patientId.getText(),
                    referringClinicianId.getText(),
                    referredToClinicianId.getText(),
                    referringFacilityId.getText(),
                    referredToFacilityId.getText(),
                    referralDate.getText(),
                    urgencyLevel.getText(),
                    referralReason.getText(),
                    clinicalSummary.getText(),
                    requestedInvestigations.getText(),
                    status.getText(),
                    appointmentId.getText(),
                    notes.getText(),
                    createdDate.getText(),
                    lastUpdated.getText()
            );

            controller.createReferral(referral);
            loadTableData();
        }
    }

    /* =========================
       DELETE REFERRAL
       ========================= */
    private void deleteReferral() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a referral to delete."
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this referral?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Referral referral =
                    controller.getAllReferrals().get(row);
            controller.deleteReferral(referral);
            loadTableData();
        }
    }
}
