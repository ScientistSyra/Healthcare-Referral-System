package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.AppointmentController;
import model.Appointment;

public class AppointmentPanel extends JPanel {

    private AppointmentController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public AppointmentPanel(AppointmentController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "Appointment ID",
                        "Patient ID",
                        "Clinician ID",
                        "Facility ID",
                        "Date",
                        "Time",
                        "Duration (min)",
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

        for (Appointment a : controller.getAllAppointments()) {
            tableModel.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getPatientId(),
                    a.getClinicianId(),
                    a.getFacilityId(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getDurationMinutes(),
                    a.getStatus()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Appointment");
        JButton deleteBtn = new JButton("Delete Appointment");

        addBtn.addActionListener(e -> JOptionPane.showMessageDialog(
                this, "Add Appointment not implemented yet."
        ));

        deleteBtn.addActionListener(e -> deleteAppointment());

        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    private void deleteAppointment() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an appointment to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this appointment?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Appointment appointment =
                    controller.getAllAppointments().get(row);
            controller.deleteAppointment(appointment);
            loadTableData();
        }
    }
}
