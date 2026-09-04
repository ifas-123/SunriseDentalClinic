package ui;

import model.User;
import repository.UserRepository;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private final UserRepository userRepository =
            new UserRepository();

    public LoginFrame() {

        setTitle(
                "Sunrise Dental Clinic - Login"
        );

        setSize(450, 300);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createLoginForm();
    }

    private void createLoginForm() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(
                titleLabel,
                gbc
        );

        JLabel loginLabel =
                new JLabel(
                        "Staff / Manager Login",
                        SwingConstants.CENTER
                );

        gbc.gridy = 1;

        panel.add(
                loginLabel,
                gbc
        );

        gbc.gridwidth = 1;

        JLabel usernameLabel =
                new JLabel("Username:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(
                usernameLabel,
                gbc
        );

        usernameField =
                new JTextField(15);

        gbc.gridx = 1;

        panel.add(
                usernameField,
                gbc
        );

        JLabel passwordLabel =
                new JLabel("Password:");

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(
                passwordLabel,
                gbc
        );

        passwordField =
                new JPasswordField(15);

        gbc.gridx = 1;

        panel.add(
                passwordField,
                gbc
        );

        JButton loginButton =
                new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        panel.add(
                loginButton,
                gbc
        );

        loginButton.addActionListener(
                e -> login()
        );

        add(panel);
    }

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        User user =
                userRepository.login(
                        username,
                        password
                );

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Login successful.\n"
                        + "Role: "
                        + user.getRole(),
                "Login",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();

        new MainMenuFrame(
                user
        ).setVisible(true);
    }
}