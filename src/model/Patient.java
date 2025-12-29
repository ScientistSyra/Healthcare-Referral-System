package model;

public class Patient {

    private String patientId;
    private String fullName;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phone;
    private String email;
    private String gpSurgeryId;

    public Patient(String patientId, String fullName, String dateOfBirth,
                   String nhsNumber, String gender, String phone,
                   String email, String gpSurgeryId) {

        this.patientId = patientId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.gpSurgeryId = gpSurgeryId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGpSurgeryId() {
        return gpSurgeryId;
    }

    @Override
    public String toString() {
        return patientId + " - " + fullName;
    }
}
