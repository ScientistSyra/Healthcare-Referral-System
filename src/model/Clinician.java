package model;

public class Clinician {

    private String clinicianId;
    private String fullName;
    private String role;
    private String specialty;
    private String workplace;

    public Clinician(String clinicianId, String fullName,
                     String role, String specialty,
                     String workplace) {

        this.clinicianId = clinicianId;
        this.fullName = fullName;
        this.role = role;
        this.specialty = specialty;
        this.workplace = workplace;
    }

    public String getClinicianId() {
        return clinicianId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getWorkplace() {
        return workplace;
    }

    @Override
    public String toString() {
        return clinicianId + " - " + fullName;
    }
}
