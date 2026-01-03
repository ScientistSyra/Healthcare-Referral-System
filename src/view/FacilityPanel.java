package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controller.FacilityController;
import model.Facility;

public class FacilityPanel extends JPanel {

    private FacilityController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public FacilityPanel(FacilityController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new String[]{
                        "facility_id",
                        "facility_name",
                        "facility_type",
                        "address",
                        "postcode",
                        "phone_number",
                        "email",
                        "opening_hours",
                        "manager_name",
                        "capacity",
                        "specialities_offered"
                }, 0
        );

        table = new JTable(tableModel);
        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private void loadTableData() {
        tableModel.setRowCount(0);

        for (Facility f : controller.getAllFacilities()) {
            tableModel.addRow(new Object[]{
                    f.getFacilityId(),
                    f.getFacilityName(),
                    f.getFacilityType(),
                    f.getAddress(),
                    f.getPostcode(),
                    f.getPhoneNumber(),
                    f.getEmail(),
                    f.getOpeningHours(),
                    f.getManagerName(),
                    f.getCapacity(),
                    f.getSpecialitiesOffered()
            });
        }
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Facility");
        JButton deleteBtn = new JButton("Delete Facility");

        addBtn.addActionListener(e -> addFacility());
        deleteBtn.addActionListener(e -> deleteFacility());

        panel.add(addBtn);
        panel.add(deleteBtn);

        return panel;
    }

    private void addFacility() {

        JTextField facilityId = new JTextField();
        JTextField facilityName = new JTextField();
        JTextField facilityType = new JTextField();
        JTextField address = new JTextField();
        JTextField postcode = new JTextField();
        JTextField phoneNumber = new JTextField();
        JTextField email = new JTextField();
        JTextField openingHours = new JTextField();
        JTextField managerName = new JTextField();
        JTextField capacity = new JTextField();
        JTextField specialitiesOffered = new JTextField();

        Object[] fields = {
                "Facility ID:", facilityId,
                "Facility Name:", facilityName,
                "Facility Type:", facilityType,
                "Address:", address,
                "Postcode:", postcode,
                "Phone Number:", phoneNumber,
                "Email:", email,
                "Opening Hours:", openingHours,
                "Manager Name:", managerName,
                "Capacity:", capacity,
                "Specialities Offered:", specialitiesOffered
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Add Facility", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Facility facility = new Facility(
                    facilityId.getText(),
                    facilityName.getText(),
                    facilityType.getText(),
                    address.getText(),
                    postcode.getText(),
                    phoneNumber.getText(),
                    email.getText(),
                    openingHours.getText(),
                    managerName.getText(),
                    capacity.getText(),
                    specialitiesOffered.getText()
            );

            controller.addFacility(facility);
            loadTableData();
        }
    }

    private void deleteFacility() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a facility to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this facility?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Facility facility = controller.getAllFacilities().get(row);
            controller.deleteFacility(facility);
            loadTableData();
        }
    }
}
