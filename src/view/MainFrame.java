package view;

import javax.swing.*;
import java.awt.*;

import controller.*;
import model.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Healthcare Referral System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /* =========================
           REPOSITORIES
           ========================= */
        
     PatientRepository patientRepo = new PatientRepository();
     ClinicianRepository clinicianRepo = new ClinicianRepository();
     AppointmentRepository appointmentRepo = new AppointmentRepository();
     PrescriptionRepository prescriptionRepo = new PrescriptionRepository();
     ReferralRepository referralRepo = new ReferralRepository();

        /* =========================
           CONTROLLERS
           ========================= */
        PatientController patientController =
                new PatientController(patientRepo);
        ClinicianController clinicianController =
                new ClinicianController(clinicianRepo);
        AppointmentController appointmentController =
                new AppointmentController(appointmentRepo);
        PrescriptionController prescriptionController =
                new PrescriptionController(prescriptionRepo);
        ReferralController referralController =
                new ReferralController(referralRepo);

        /* =========================
           LOAD CSV DATA
           ========================= */
        patientController.loadPatients();
        clinicianController.loadClinicians();
        appointmentController.loadAppointments();
        prescriptionController.loadPrescriptions();
        referralController.loadReferrals();

        /* =========================
           GUI TABS (VIEWS)
           ========================= */
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Patients",
                new PatientPanel(patientController));

        tabs.addTab("Clinicians",
                new ClinicianPanel(clinicianController));

        tabs.addTab("Appointments",
                new AppointmentPanel(appointmentController));

        tabs.addTab("Prescriptions",
                new PrescriptionPanel(prescriptionController));

        tabs.addTab("Referrals",
                new ReferralPanel(referralController));

        add(tabs, BorderLayout.CENTER);

        setVisible(true);
    }
}
