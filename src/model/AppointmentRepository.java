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
            if (row[0].equalsIgnoreCase("appointment_id")) continue;

            appointments.add(new Appointment(
                    row[0], row[1], row[2], row[3],
                    row[4], row[5], row[6], row[7],
                    row[8], row[9], row[10], row[11], row[12]
            ));
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        rewriteCSV();
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        rewriteCSV();
    }

    private void rewriteCSV() {

        CSVWriter.overwrite(FILE_PATH,
                "appointment_id,patient_id,clinician_id,facility_id," +
                "appointment_date,appointment_time,duration_minutes," +
                "appointment_type,status,reason_for_visit,notes," +
                "created_date,last_modified"
        );

        for (Appointment a : appointments) {
            CSVWriter.append(FILE_PATH, String.join(",",
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
            ));
        }
    }
}
