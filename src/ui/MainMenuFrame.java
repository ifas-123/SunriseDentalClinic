package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    private final User loggedInUser;

    public MainMenuFrame(User user) {

        this.loggedInUser = user;

        setTitle(
                "Sunrise Dental Clinic - Main Menu"
        );

        setSize(500, 500);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createMainMenu();
    }

    private void createMainMenu() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 50, 25, 50
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Sunrise Dental Clinic",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel userLabel =
                new JLabel(
                        "Logged in as: "
                                + loggedInUser.getUsername()
                                + " ("
                                + loggedInUser.getRole()
                                + ")"
                );

        userLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(userLabel);

        panel.add(
                Box.createVerticalStrut(25)
        );

        JButton registerButton =
                new JButton(
                        "Register New Appointment"
                );

        JButton searchButton =
                new JButton(
                        "Display Appointment Details"
                );

        JButton billingButton =
                new JButton(
                        "Calculate and Print Bill"
                );

        JButton helpButton =
                new JButton("Help");

        JButton exitButton =
                new JButton("Exit");

        JButton managerButton =
                new JButton(
                        "Manager - View All Appointments"
                );

        Dimension buttonSize =
                new Dimension(
                        300,
                        40
                );

        registerButton.setMaximumSize(
                buttonSize
        );

        searchButton.setMaximumSize(
                buttonSize
        );

        billingButton.setMaximumSize(
                buttonSize
        );

        helpButton.setMaximumSize(
                buttonSize
        );

        exitButton.setMaximumSize(
                buttonSize
        );

        managerButton.setMaximumSize(
                buttonSize
        );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        searchButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        billingButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        helpButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        exitButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        managerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(registerButton);

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(searchButton);

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(billingButton);

        // Manager-only function
        if (loggedInUser.getRole().equals("MANAGER")) {

            panel.add(
                    Box.createVerticalStrut(10)
            );

            panel.add(managerButton);

            managerButton.addActionListener(e -> {

                new ManagerFrame()
                        .setVisible(true);
            });
        }

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(helpButton);

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(exitButton);

        registerButton.addActionListener(e -> {

            new AppointmentFrame()
                    .setVisible(true);
        });

        searchButton.addActionListener(e -> {

            new AppointmentSearchFrame()
                    .setVisible(true);
        });

        billingButton.addActionListener(e -> {

            new BillingFrame()
                    .setVisible(true);
        });

        helpButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Sunrise Dental Clinic Management System\n\n"
                            + "Register New Appointment:\n"
                            + "Create a new patient appointment.\n\n"
                            + "Display Appointment Details:\n"
                            + "Search for an existing appointment.\n\n"
                            + "Calculate and Print Bill:\n"
                            + "Calculate and print the treatment bill.\n\n"
                            + "Manager:\n"
                            + "View all registered appointments.\n\n"
                            + "Exit:\n"
                            + "Close the application.",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        exitButton.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to exit?",
                            "Exit System",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        });

        add(panel);
    }
}