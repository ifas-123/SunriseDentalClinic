package ui;

import model.Appointment;
import repository.AppointmentRepository;

import javax.swing.*;
import java.awt.*;

public class AppointmentSearchFrame extends JFrame {

    private JTextField appointmentNumberField;

    private final AppointmentRepository appointmentRepository =
            new AppointmentRepository();

    public AppointmentSearchFrame() {

        setTitle(
                "Sunrise Dental Clinic - Search Appointment"
        );

        setSize(500, 220);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createSearchForm();
    }

    private void createSearchForm() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel titleLabel =
                new JLabel(
                        "Display Appointment Details",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(
                titleLabel,
                gbc
        );

        gbc.gridwidth = 1;

        JLabel appointmentNumberLabel =
                new JLabel(
                        "Appointment Number:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                appointmentNumberLabel,
                gbc
        );

        appointmentNumberField =
                new JTextField(12);

        gbc.gridx = 1;

        panel.add(
                appointmentNumberField,
                gbc
        );

        JButton searchButton =
                new JButton("Search");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(
                searchButton,
                gbc
        );

        searchButton.addActionListener(
                e -> searchAppointment()
        );

        add(panel);
    }

    private void searchAppointment() {

        String appointmentNumber =
                appointmentNumberField
                        .getText()
                        .trim();

        if (appointmentNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an appointment number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (!appointmentNumber.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment number must contain numbers only.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int number =
                Integer.parseInt(
                        appointmentNumber
                );

        Appointment appointment =
                appointmentRepository
                        .findAppointmentByNumber(number);

        if (appointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No appointment found with number "
                            + appointmentNumber + ".",
                    "Appointment Not Found",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        showAppointmentDetails(appointment);
    }

    private void showAppointmentDetails(
            Appointment appointment) {

        JFrame detailsFrame =
                new JFrame(
                        "Appointment Details"
                );

        detailsFrame.setSize(550, 450);

        detailsFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        detailsFrame.setLocationRelativeTo(this);

        detailsFrame.setResizable(false);

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Appointment Details",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel detailsPanel =
                new JPanel(
                        new GridLayout(
                                8,
                                2,
                                10,
                                10
                        )
                );

        detailsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        detailsPanel.add(
                new JLabel("Appointment Number:")
        );

        detailsPanel.add(
                new JLabel(
                        String.valueOf(
                                appointment
                                        .getAppointmentNumber()
                        )
                )
        );

        detailsPanel.add(
                new JLabel("Patient Name:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment.getPatientName()
                )
        );

        detailsPanel.add(
                new JLabel("Address:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment.getAddress()
                )
        );

        detailsPanel.add(
                new JLabel("Contact Number:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment.getContactNumber()
                )
        );

        detailsPanel.add(
                new JLabel("Dentist Name:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment.getDentistName()
                )
        );

        detailsPanel.add(
                new JLabel("Treatment Type:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment.getTreatmentType()
                )
        );

        detailsPanel.add(
                new JLabel("Appointment Date:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment
                                .getAppointmentDate()
                                .toString()
                )
        );

        detailsPanel.add(
                new JLabel("Appointment Time:")
        );

        detailsPanel.add(
                new JLabel(
                        appointment
                                .getAppointmentTime()
                                .toString()
                )
        );

        panel.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        JButton closeButton =
                new JButton("Close");

        closeButton.addActionListener(
                e -> detailsFrame.dispose()
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(closeButton);

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        detailsFrame.add(panel);

        detailsFrame.setVisible(true);
    }
}