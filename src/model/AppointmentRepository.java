package model;

import util.CSVReader;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private List<Appointment> appointments = new ArrayList<>();
    private static final String FILE_PATH = "data/appointments.csv";

    public void loadAppointments() {
        List<String[]> rows = CSVReader.read(FILE_PATH);
        appointments.clear();

        for (String[] row : rows) {

            if (row[0].equalsIgnoreCase("appointment_id")) {
                continue;
            }

            Appointment appointment = new Appointment(
                    row[0],  // appointment_id
                    row[1],  // patient_id
                    row[2],  // clinician_id
                    row[3],  // facility_id
                    row[4],  // appointment_date
                    row[5],  // appointment_time
                    row[6],  // duration_minutes
                    row[7],  // appointment_type
                    row[8],  // status
                    row[9],  // reason_for_visit
                    row[10], // notes
                    row[11], // created_date
                    row[12]  // last_modified
            );

            appointments.add(appointment);
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    // ✅ REQUIRED FIX
    public void removeAppointment(Appointment appointment) {
        deleteAppointment(appointment);
    }
}
