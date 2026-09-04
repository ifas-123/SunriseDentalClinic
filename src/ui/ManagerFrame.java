package ui;

import model.Appointment;
import repository.AppointmentRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ManagerFrame extends JFrame {

    private final AppointmentRepository appointmentRepository =
            new AppointmentRepository();

    public ManagerFrame() {

        setTitle(
                "Sunrise Dental Clinic - Manager"
        );

        setSize(800, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createManagerInterface();
    }

    private void createManagerInterface() {

        JPanel panel =
                new JPanel(new BorderLayout(10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Manager - All Appointments",
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

        String[] columns = {
                "Appointment No.",
                "Patient Name",
                "Contact",
                "Dentist",
                "Treatment",
                "Date",
                "Time"
        };

        List<Appointment> appointments =
                appointmentRepository
                        .getAllAppointments();

        Object[][] data =
                new Object[appointments.size()][7];

        for (int i = 0; i < appointments.size(); i++) {

            Appointment appointment =
                    appointments.get(i);

            data[i][0] =
                    appointment.getAppointmentNumber();

            data[i][1] =
                    appointment.getPatientName();

            data[i][2] =
                    appointment.getContactNumber();

            data[i][3] =
                    appointment.getDentistName();

            data[i][4] =
                    appointment.getTreatmentType();

            data[i][5] =
                    appointment.getAppointmentDate();

            data[i][6] =
                    appointment.getAppointmentTime();
        }

        JTable appointmentTable =
                new JTable(data, columns);

        appointmentTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        appointmentTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton("Refresh");

        JButton closeButton =
                new JButton("Close");

        refreshButton.addActionListener(
                e -> refreshAppointments()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(panel);
    }

    private void refreshAppointments() {

        dispose();

        new ManagerFrame()
                .setVisible(true);
    }
}