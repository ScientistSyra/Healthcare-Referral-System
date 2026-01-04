package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.AppointmentController;
import model.Appointment;

public class AppointmentPanel extends JPanel {

    private AppointmentController controller;
    private JTable table;
    private DefaultTableModel model;

    public AppointmentPanel(AppointmentController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
                "appointment_id",
                "patient_id",
                "clinician_id",
                "facility_id",
                "appointment_date",
                "appointment_time",
                "duration_minutes",
                "appointment_type",
                "status",
                "reason_for_visit",
                "notes",
                "created_date",
                "last_modified"
        }, 0);

        table = new JTable(model);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        for (Appointment a : controller.getAllAppointments()) {
            model.addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getPatientId(),
                    a.getClinicianId(),
                    a.getFacilityId(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getDurationMinutes(),
                    a.getAppointmentType(),
                    a.getStatus(),
                    a.getReasonForVisit(),
                    a.getNotes(),
                    a.getCreatedDate(),
                    a.getLastModified()
            });
        }
    }

    private JPanel buttonPanel() {
        JPanel p = new JPanel();

        JButton add = new JButton("Add Appointment");
        JButton del = new JButton("Delete Appointment");

        add.addActionListener(e -> addAppointment());
        del.addActionListener(e -> deleteAppointment());

        p.add(add);
        p.add(del);
        return p;
    }

    private void addAppointment() {

        JTextField[] f = new JTextField[13];
        for (int i = 0; i < f.length; i++) f[i] = new JTextField();

        Object[] fields = {
                "Appointment ID", f[0],
                "Patient ID", f[1],
                "Clinician ID", f[2],
                "Facility ID", f[3],
                "Appointment Date", f[4],
                "Appointment Time", f[5],
                "Duration Minutes", f[6],
                "Appointment Type", f[7],
                "Status", f[8],
                "Reason For Visit", f[9],
                "Notes", f[10],
                "Created Date", f[11],
                "Last Modified", f[12]
        };

        if (JOptionPane.showConfirmDialog(this, fields,
                "Add Appointment", JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION) {

            controller.addAppointment(new Appointment(
                    f[0].getText(), f[1].getText(), f[2].getText(),
                    f[3].getText(), f[4].getText(), f[5].getText(),
                    f[6].getText(), f[7].getText(), f[8].getText(),
                    f[9].getText(), f[10].getText(), f[11].getText(),
                    f[12].getText()
            ));
            loadData();
        }
    }

    private void deleteAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        controller.deleteAppointment(
                controller.getAllAppointments().get(row)
        );
        loadData();
    }
}
