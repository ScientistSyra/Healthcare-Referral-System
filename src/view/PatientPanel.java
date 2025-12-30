package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientPanel extends JPanel {

    private PatientController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public PatientPanel(PatientController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{
                "Patient ID", "First Name", "Last Name", "DOB",
                "NHS Number", "Gender", "Phone", "Email",
                "Address", "Postcode",
                "Emergency Contact", "Emergency Phone",
                "Registration Date", "GP Surgery ID"
        }, 0);

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Patient p : controller.getAllPatients()) {
            tableModel.addRow(new Object[]{
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getDateOfBirth(),
                    p.getNhsNumber(),
                    p.getGender(),
                    p.getPhone(),
                    p.getEmail(),
                    p.getAddress(),
                    p.getPostcode(),
                    p.getEmergencyContactName(),
                    p.getEmergencyContactPhone(),
                    p.getRegistrationDate(),
                    p.getGpSurgeryId()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Patient");
        JButton editBtn = new JButton("Edit Patient");
        JButton deleteBtn = new JButton("Delete Patient");

        addBtn.addActionListener(e -> addPatient());
        editBtn.addActionListener(e -> editPatient());
        deleteBtn.addActionListener(e -> deletePatient());

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);

        return panel;
    }

    private void addPatient() {
        Patient patient = showPatientForm(null);
        if (patient != null) {
            controller.addPatient(patient);
            loadTableData();
        }
    }

    private void editPatient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a patient to edit.");
            return;
        }

        Patient existing = controller.getAllPatients().get(row);
        Patient updated = showPatientForm(existing);

        if (updated != null) {
            controller.updatePatient(existing, updated);
            loadTableData();
        }
    }

    private void deletePatient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a patient to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this patient?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Patient patient = controller.getAllPatients().get(row);
            controller.deletePatient(patient);
            loadTableData();
        }
    }

    private Patient showPatientForm(Patient p) {

        JTextField patientId = new JTextField(p != null ? p.getPatientId() : "");
        JTextField firstName = new JTextField(p != null ? p.getFirstName() : "");
        JTextField lastName = new JTextField(p != null ? p.getLastName() : "");
        JTextField dob = new JTextField(p != null ? p.getDateOfBirth() : "");
        JTextField nhs = new JTextField(p != null ? p.getNhsNumber() : "");
        JTextField gender = new JTextField(p != null ? p.getGender() : "");
        JTextField phone = new JTextField(p != null ? p.getPhone() : "");
        JTextField email = new JTextField(p != null ? p.getEmail() : "");
        JTextField address = new JTextField(p != null ? p.getAddress() : "");
        JTextField postcode = new JTextField(p != null ? p.getPostcode() : "");
        JTextField emergencyName = new JTextField(p != null ? p.getEmergencyContactName() : "");
        JTextField emergencyPhone = new JTextField(p != null ? p.getEmergencyContactPhone() : "");
        JTextField regDate = new JTextField(p != null ? p.getRegistrationDate() : "");
        JTextField gpSurgery = new JTextField(p != null ? p.getGpSurgeryId() : "");

        Object[] fields = {
                "Patient ID:", patientId,
                "First Name:", firstName,
                "Last Name:", lastName,
                "Date of Birth:", dob,
                "NHS Number:", nhs,
                "Gender:", gender,
                "Phone:", phone,
                "Email:", email,
                "Address:", address,
                "Postcode:", postcode,
                "Emergency Contact Name:", emergencyName,
                "Emergency Contact Phone:", emergencyPhone,
                "Registration Date:", regDate,
                "GP Surgery ID:", gpSurgery
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Patient Details", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            return new Patient(
                    patientId.getText(),
                    firstName.getText(),
                    lastName.getText(),
                    dob.getText(),
                    nhs.getText(),
                    gender.getText(),
                    phone.getText(),
                    email.getText(),
                    address.getText(),
                    postcode.getText(),
                    emergencyName.getText(),
                    emergencyPhone.getText(),
                    regDate.getText(),
                    gpSurgery.getText()
            );
        }

        return null;
    }
}
