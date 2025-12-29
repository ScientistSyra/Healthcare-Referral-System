package controller;

import java.util.List;
import model.Appointment;
import model.AppointmentRepository;

public class AppointmentController {

    private AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void loadAppointments() {
        appointmentRepository.loadAppointments();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.addAppointment(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.removeAppointment(appointment);
    }
}
