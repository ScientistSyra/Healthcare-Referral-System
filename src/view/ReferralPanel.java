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
                        "Referral ID",
                        "Patient ID",
                        "Clinician ID",
                        "Facility ID",
                        "Date",
                        "Urgency",
                        "Status"
                }, 0
        );

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Referral r : controller.getAllReferrals()) {
            tableModel.addRow(new Object[]{
                    r.getReferralId(),
                    r.getPatientId(),
                    r.getReferringClinicianId(),
                    r.getReferredToFacilityId(),
                    r.getReferralDate(),
                    r.getUrgencyLevel(),
                    r.getStatus()
            });
        }
    }

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

    private void addReferral() {

        JTextField id = new JTextField();
        JTextField patientId = new JTextField();
        JTextField clinicianId = new JTextField();
        JTextField facilityId = new JTextField();
        JTextField date = new JTextField();
        JTextField urgency = new JTextField();
        JTextField reason = new JTextField();
        JTextField status = new JTextField();
        JTextArea summary = new JTextArea(3, 20);

        Object[] fields = {
                "Referral ID:", id,
                "Patient ID:", patientId,
                "Referring Clinician ID:", clinicianId,
                "Referred To Facility ID:", facilityId,
                "Referral Date:", date,
                "Urgency:", urgency,
                "Referral Reason:", reason,
                "Status:", status,
                "Clinical Summary:", new JScrollPane(summary)
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Referral", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Referral referral = new Referral(
                    id.getText(),
                    patientId.getText(),
                    clinicianId.getText(),
                    facilityId.getText(),
                    date.getText(),
                    urgency.getText(),
                    reason.getText(),
                    summary.getText(),
                    status.getText()
            );

            controller.createReferral(referral);
            loadTableData();
        }
    }

    private void deleteReferral() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a referral to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this referral?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Referral referral = controller.getAllReferrals().get(row);
            controller.deleteReferral(referral);
            loadTableData();
        }
    }
}
