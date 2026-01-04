package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.PrescriptionController;
import model.Prescription;

public class PrescriptionPanel extends JPanel {

    private PrescriptionController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public PrescriptionPanel(PrescriptionController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // ✅ HEADINGS NOW MATCH CSV EXACTLY
        tableModel = new DefaultTableModel(
                new String[]{
                        "clinician_id",
                        "appointment_id",
                        "prescription_date",
                        "medication_name",
                        "dosage",
                        "frequency",
                        "duration_days",
                        "quantity",
                        "instructions",
                        "pharmacy_name",
                        "status",
                        "issue_date",
                        "collection_date"
                }, 0
        );

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Prescription p : controller.getAllPrescriptions()) {
            tableModel.addRow(new Object[]{
                    p.getClinicianId(),
                    p.getAppointmentId(),
                    p.getPrescriptionDate(),
                    p.getMedicationName(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getDurationDays(),
                    p.getQuantity(),
                    p.getInstructions(),
                    p.getPharmacyName(),
                    p.getStatus(),
                    p.getIssueDate(),
                    p.getCollectionDate()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Prescription");
        JButton deleteBtn = new JButton("Delete Prescription");

        addBtn.addActionListener(e -> addPrescription());
        deleteBtn.addActionListener(e -> deletePrescription());

        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    private void addPrescription() {
        JTextField clinicianId = new JTextField();
        JTextField appointmentId = new JTextField();
        JTextField prescriptionDate = new JTextField();
        JTextField medicationName = new JTextField();
        JTextField dosage = new JTextField();
        JTextField frequency = new JTextField();
        JTextField durationDays = new JTextField();
        JTextField quantity = new JTextField();
        JTextField instructions = new JTextField();
        JTextField pharmacyName = new JTextField();
        JTextField status = new JTextField("Issued");
        JTextField issueDate = new JTextField();
        JTextField collectionDate = new JTextField();

        Object[] fields = {
                "Clinician ID:", clinicianId,
                "Appointment ID:", appointmentId,
                "Prescription Date:", prescriptionDate,
                "Medication Name:", medicationName,
                "Dosage:", dosage,
                "Frequency:", frequency,
                "Duration Days:", durationDays,
                "Quantity:", quantity,
                "Instructions:", instructions,
                "Pharmacy Name:", pharmacyName,
                "Status:", status,
                "Issue Date:", issueDate,
                "Collection Date:", collectionDate
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Prescription", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            controller.addPrescription(new Prescription(
                    clinicianId.getText(),
                    appointmentId.getText(),
                    prescriptionDate.getText(),
                    medicationName.getText(),
                    dosage.getText(),
                    frequency.getText(),
                    durationDays.getText(),
                    quantity.getText(),
                    instructions.getText(),
                    pharmacyName.getText(),
                    status.getText(),
                    issueDate.getText(),
                    collectionDate.getText()
            ));
            loadTableData();
        }
    }

    private void deletePrescription() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a prescription to delete.");
            return;
        }

        controller.deletePrescription(
                controller.getAllPrescriptions().get(row)
        );
        loadTableData();
    }
}
