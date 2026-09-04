package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        setTitle("Sunrise Dental Clinic - Main Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createMainMenu();
    }

    private void createMainMenu() {

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        JLabel titleLabel = new JLabel(
                "Sunrise Dental Clinic Management System",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton registerButton =
                new JButton("Register New Appointment");
                registerButton.addActionListener(e -> {
    new AppointmentFrame().setVisible(true);
});        

        JButton searchButton =
                new JButton("Display Appointment Details");

        JButton billingButton =
                new JButton("Calculate and Print Bill");

        JButton helpButton =
                new JButton("Help");

        JButton exitButton =
                new JButton("Exit");

        panel.add(titleLabel);
        panel.add(registerButton);
        panel.add(searchButton);
        panel.add(billingButton);
        panel.add(helpButton);
        panel.add(exitButton);

        exitButton.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit System",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(panel);
    }
}