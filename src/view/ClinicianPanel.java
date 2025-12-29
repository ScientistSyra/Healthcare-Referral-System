package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.ClinicianController;
import model.Clinician;

public class ClinicianPanel extends JPanel {

    private ClinicianController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public ClinicianPanel(ClinicianController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "Clinician ID",
                        "Full Name",
                        "Role",
                        "Specialty",
                        "Workplace"
                }, 0
        );

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Clinician c : controller.getAllClinicians()) {
            tableModel.addRow(new Object[]{
                    c.getClinicianId(),
                    c.getFullName(),
                    c.getRole(),
                    c.getSpecialty(),
                    c.getWorkplace()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Clinician");
        JButton deleteBtn = new JButton("Delete Clinician");

        addBtn.addActionListener(e -> addClinician());
        deleteBtn.addActionListener(e -> deleteClinician());

        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    private void addClinician() {

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField role = new JTextField();
        JTextField specialty = new JTextField();
        JTextField workplace = new JTextField();

        Object[] fields = {
                "Clinician ID:", id,
                "Full Name:", name,
                "Role:", role,
                "Specialty:", specialty,
                "Workplace:", workplace
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Clinician", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Clinician clinician = new Clinician(
                    id.getText(),
                    name.getText(),
                    role.getText(),
                    specialty.getText(),
                    workplace.getText()
            );

            controller.addClinician(clinician);
            loadTableData();
        }
    }

    private void deleteClinician() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a clinician to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this clinician?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Clinician clinician = controller.getAllClinicians().get(row);
            controller.deleteClinician(clinician);
            loadTableData();
        }
    }
}
