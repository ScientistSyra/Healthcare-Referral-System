package controller;

import java.util.List;
import model.Appointment;
import model.AppointmentRepository;

public class AppointmentController {

    private AppointmentRepository repository;

    public AppointmentController(AppointmentRepository repository) {
        this.repository = repository;
    }

    public void loadAppointments() {
        repository.loadAppointments();
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    public void addAppointment(Appointment appointment) {
        repository.addAppointment(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        repository.removeAppointment(appointment);
    }
}
