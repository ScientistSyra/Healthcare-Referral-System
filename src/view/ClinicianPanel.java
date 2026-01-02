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
                        "Title",
                        "Speciality",
                        "Workplace Type",
                        "Employment Status"
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
                    c.getTitle(),
                    c.getSpeciality(),
                    c.getWorkplaceType(),
                    c.getEmploymentStatus()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton deleteBtn = new JButton("Delete Clinician");
        deleteBtn.addActionListener(e -> deleteClinician());

        panel.add(deleteBtn);
        return panel;
    }

    private void deleteClinician() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a clinician to delete.");
            return;
        }

        Clinician clinician = controller.getAllClinicians().get(row);
        controller.deleteClinician(clinician);
        loadTableData();
    }
}
