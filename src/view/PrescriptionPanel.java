package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.PrescriptionController;
import model.Prescription;
import model.Patient;

public class PrescriptionPanel extends JPanel {

    private PrescriptionController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public PrescriptionPanel(PrescriptionController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "Prescription ID",
                        "Patient ID",
                        "Medication",
                        "Dosage",
                        "Pharmacy",
                        "Status"
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

        for (Prescription p : controller.getAllPrescriptions()) {
            tableModel.addRow(new Object[]{
                    p.getPrescriptionId(),
                    p.getPatientId(),
                    p.getMedication(),
                    p.getDosage(),
                    p.getPharmacy(),
                    p.getCollectionStatus()
            });
        }
    }

    /* =========================
       BUTTON PANEL
       ========================= */
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

    /* =========================
       ADD PRESCRIPTION
       ========================= */
    private void addPrescription() {

        JTextField id = new JTextField();
        JTextField patientId = new JTextField();
        JTextField medication = new JTextField();
        JTextField dosage = new JTextField();
        JTextField pharmacy = new JTextField();
        JTextField status = new JTextField();

        Object[] fields = {
                "Prescription ID:", id,
                "Patient ID:", patientId,
                "Medication:", medication,
                "Dosage:", dosage,
                "Pharmacy:", pharmacy,
                "Status:", status
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Prescription", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Prescription prescription = new Prescription(
                    id.getText(),
                    patientId.getText(),
                    medication.getText(),
                    dosage.getText(),
                    pharmacy.getText(),
                    status.getText()
            );

            controller.addPrescription(prescription);
            loadTableData();
        }
    }

    /* =========================
       DELETE PRESCRIPTION
       ========================= */
    private void deletePrescription() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a prescription to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this prescription?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Prescription p = controller.getAllPrescriptions().get(row);
            controller.deletePrescription(p);
            loadTableData();
        }
    }
}
