package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.PatientController;
import model.Patient;

public class PatientPanel extends JPanel {

    private PatientController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public PatientPanel(PatientController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "Patient ID",
                        "Full Name",
                        "Date of Birth",
                        "NHS Number",
                        "Gender",
                        "Phone",
                        "Email",
                        "GP Surgery ID"
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

        for (Patient p : controller.getAllPatients()) {
            tableModel.addRow(new Object[]{
                    p.getPatientId(),
                    p.getFullName(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getGender(),
                    p.getPhone(),
                    p.getEmail(),
                    p.getGpSurgeryId()
            });
        }
    }

    /* =========================
       BUTTON PANEL (CRUD)
       ========================= */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Patient");
        JButton editBtn = new JButton("Edit Patient");
        JButton deleteBtn = new JButton("Delete Patient");
        JButton refreshBtn = new JButton("Refresh");

        addBtn.addActionListener(e -> addPatient());
        editBtn.addActionListener(e -> editPatient());
        deleteBtn.addActionListener(e -> deletePatient());
        refreshBtn.addActionListener(e -> loadTableData());

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(refreshBtn);

        return panel;
    }

    /* =========================
       CRUD OPERATIONS
       ========================= */

    private void addPatient() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField dob = new JTextField();
        JTextField nhs = new JTextField();
        JTextField gender = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        JTextField gp = new JTextField();

        Object[] fields = {
                "Patient ID:", id,
                "Full Name:", name,
                "Date of Birth:", dob,
                "NHS Number:", nhs,
                "Gender:", gender,
                "Phone:", phone,
                "Email:", email,
                "GP Surgery ID:", gp
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Patient", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            Patient patient = new Patient(
                    id.getText(),
                    name.getText(),
                    dob.getText(),
                    nhs.getText(),
                    gender.getText(),
                    phone.getText(),
                    email.getText(),
                    gp.getText()
            );
            controller.addPatient(patient);
            loadTableData();
        }
    }

    private void editPatient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient to edit.");
            return;
        }

        JTextField name = new JTextField(tableModel.getValueAt(row, 1).toString());
        JTextField phone = new JTextField(tableModel.getValueAt(row, 5).toString());
        JTextField email = new JTextField(tableModel.getValueAt(row, 6).toString());
        JTextField gp = new JTextField(tableModel.getValueAt(row, 7).toString());

        Object[] fields = {
                "Full Name:", name,
                "Phone:", phone,
                "Email:", email,
                "GP Surgery ID:", gp
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Edit Patient", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            Patient updated = new Patient(
                    tableModel.getValueAt(row, 0).toString(),
                    name.getText(),
                    tableModel.getValueAt(row, 2).toString(),
                    tableModel.getValueAt(row, 3).toString(),
                    tableModel.getValueAt(row, 4).toString(),
                    phone.getText(),
                    email.getText(),
                    gp.getText()
            );

            controller.deletePatient(controller.getAllPatients().get(row));
            controller.addPatient(updated);
            loadTableData();
        }
    }

    private void deletePatient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this patient?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deletePatient(controller.getAllPatients().get(row));
            loadTableData();
        }
    }
}
