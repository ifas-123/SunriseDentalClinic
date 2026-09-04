package ui;

import model.Appointment;
import repository.AppointmentRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AppointmentFrame extends JFrame {

    private JTextField appointmentNumberField;
    private JTextField patientNameField;
    private JTextField addressField;
    private JTextField contactNumberField;
    private JTextField dentistNameField;
    private JComboBox<String> treatmentTypeComboBox;
    private JTextField appointmentDateField;
    private JTextField appointmentTimeField;

    private final AppointmentRepository appointmentRepository =
            new AppointmentRepository();

    public AppointmentFrame() {

        setTitle("Sunrise Dental Clinic - Register Appointment");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createAppointmentForm();
    }

    private void createAppointmentForm() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(
                "Register New Appointment",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Appointment Number
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                new JLabel("Appointment Number:"),
                gbc
        );

        appointmentNumberField = new JTextField();

        gbc.gridx = 1;

        panel.add(
                appointmentNumberField,
                gbc
        );

        // Patient Name
        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                new JLabel("Patient Name:"),
                gbc
        );

        patientNameField = new JTextField();

        gbc.gridx = 1;

        panel.add(
                patientNameField,
                gbc
        );

        // Address
        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                new JLabel("Address:"),
                gbc
        );

        addressField = new JTextField();

        gbc.gridx = 1;

        panel.add(
                addressField,
                gbc
        );

        // Contact Number
        gbc.gridx = 0;
        gbc.gridy = 4;

        panel.add(
                new JLabel("Contact Number:"),
                gbc
        );

        contactNumberField = new JTextField();

        gbc.gridx = 1;

        panel.add(
                contactNumberField,
                gbc
        );

        // Dentist
        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(
                new JLabel("Dentist Name:"),
                gbc
        );

        dentistNameField = new JTextField();

        gbc.gridx = 1;

        panel.add(
                dentistNameField,
                gbc
        );

        // Treatment
        gbc.gridx = 0;
        gbc.gridy = 6;

        panel.add(
                new JLabel("Treatment Type:"),
                gbc
        );

        String[] treatments = {
                "Select Treatment",
                "Dental Cleaning",
                "Dental Filling",
                "Tooth Extraction",
                "Root Canal",
                "Dental Check-up"
        };

        treatmentTypeComboBox =
                new JComboBox<>(treatments);

        gbc.gridx = 1;

        panel.add(
                treatmentTypeComboBox,
                gbc
        );

        // Date
        gbc.gridx = 0;
        gbc.gridy = 7;

        panel.add(
                new JLabel("Appointment Date:"),
                gbc
        );

        appointmentDateField = new JTextField();

        appointmentDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 1;

        panel.add(
                appointmentDateField,
                gbc
        );

        // Time
        gbc.gridx = 0;
        gbc.gridy = 8;

        panel.add(
                new JLabel("Appointment Time:"),
                gbc
        );

        appointmentTimeField = new JTextField();

        appointmentTimeField.setToolTipText(
                "Format: HH:MM"
        );

        gbc.gridx = 1;

        panel.add(
                appointmentTimeField,
                gbc
        );

        // Register button
        JButton registerButton =
                new JButton("Register Appointment");

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;

        panel.add(
                registerButton,
                gbc
        );

        // Clear button
        JButton clearButton =
                new JButton("Clear");

        gbc.gridy = 10;

        panel.add(
                clearButton,
                gbc
        );

        registerButton.addActionListener(
                e -> registerAppointment()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        add(panel);
    }

    private void registerAppointment() {

        String appointmentNumber =
                appointmentNumberField.getText().trim();

        String patientName =
                patientNameField.getText().trim();

        String address =
                addressField.getText().trim();

        String contactNumber =
                contactNumberField.getText().trim();

        String dentistName =
                dentistNameField.getText().trim();

        String treatmentType =
                (String) treatmentTypeComboBox.getSelectedItem();

        String appointmentDate =
                appointmentDateField.getText().trim();

        String appointmentTime =
                appointmentTimeField.getText().trim();

        // Required field validation
        if (appointmentNumber.isEmpty()
                || patientName.isEmpty()
                || address.isEmpty()
                || contactNumber.isEmpty()
                || dentistName.isEmpty()
                || appointmentDate.isEmpty()
                || appointmentTime.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all required fields.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Treatment validation
        if (treatmentType == null
                || treatmentType.equals("Select Treatment")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a treatment type.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Appointment number validation
        if (!appointmentNumber.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment number must contain numbers only.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Contact number validation
        if (!contactNumber.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Contact number must contain exactly 10 digits.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Date and time validation
        try {

            LocalDate date =
                    LocalDate.parse(appointmentDate);

            LocalTime time =
                    LocalTime.parse(appointmentTime);

            // Create Appointment object
            Appointment appointment =
                    new Appointment(
                            Integer.parseInt(appointmentNumber),
                            patientName,
                            address,
                            contactNumber,
                            dentistName,
                            treatmentType,
                            date,
                            time
                    );

            // Save to database
            boolean saved =
                    appointmentRepository.saveAppointment(
                            appointment
                    );

            if (saved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment registered successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to save the appointment.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid appointment date or time.\n"
                            + "Use date format YYYY-MM-DD "
                            + "and time format HH:MM.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid appointment number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearForm() {

        appointmentNumberField.setText("");
        patientNameField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        dentistNameField.setText("");
        treatmentTypeComboBox.setSelectedIndex(0);
        appointmentDateField.setText("");
        appointmentTimeField.setText("");
    }
}