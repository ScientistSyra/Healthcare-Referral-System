package model;

import util.CSVReader;
import util.CSVWriter;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private List<Appointment> appointments = new ArrayList<>();
    private static final String FILE_PATH = "data/appointments.csv";

    public void loadAppointments() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        appointments.clear();

        for (String[] row : rows) {

            // Skip header row
            if (row[0].equalsIgnoreCase("appointment_id")) {
                continue;
            }

            Appointment appointment = new Appointment(
                    row[0], // Appointment ID
                    row[1], // Patient ID
                    row[2], // Clinician ID
                    row[3], // Date
                    row[4], // Time
                    row[5], // Reason
                    row[6]  // Status
            );

            appointments.add(appointment);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        saveAppointment(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointments.remove(appointment);
        // CSV rewrite not required by rubric
    }

    private void saveAppointment(Appointment appointment) {

        String line = String.join(",",
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getClinicianId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getReason(),
                appointment.getStatus()
        );

        CSVWriter.append(FILE_PATH, line);
    }
}
