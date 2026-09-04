package ui;

import model.Appointment;
import repository.AppointmentRepository;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BillingFrame extends JFrame {

    private JTextField appointmentNumberField;

    private final AppointmentRepository appointmentRepository =
            new AppointmentRepository();

    private final DecimalFormat currencyFormat =
            new DecimalFormat("0.00");

    public BillingFrame() {

        setTitle(
                "Sunrise Dental Clinic - Billing"
        );

        setSize(500, 250);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createBillingForm();
    }

    private void createBillingForm() {

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
                        "Calculate and Print Bill",
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

        JLabel appointmentLabel =
                new JLabel(
                        "Appointment Number:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(
                appointmentLabel,
                gbc
        );

        appointmentNumberField =
                new JTextField(12);

        gbc.gridx = 1;

        panel.add(
                appointmentNumberField,
                gbc
        );

        JButton calculateButton =
                new JButton(
                        "Calculate Bill"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(
                calculateButton,
                gbc
        );

        calculateButton.addActionListener(
                e -> calculateBill()
        );

        add(panel);
    }

    private void calculateBill() {

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
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String treatment =
                appointment.getTreatmentType();

        double price =
                appointmentRepository
                        .getTreatmentPrice(treatment);

        if (price < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment price could not be found.",
                    "Billing Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        showInvoice(
                appointment,
                price
        );
    }

    private void showInvoice(
            Appointment appointment,
            double price) {

        JFrame invoiceFrame =
                new JFrame(
                        "Sunrise Dental Clinic - Invoice"
                );

        invoiceFrame.setSize(
                550,
                500
        );

        invoiceFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        invoiceFrame.setLocationRelativeTo(this);

        invoiceFrame.setResizable(false);

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "SUNRISE DENTAL CLINIC",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JTextArea invoiceArea =
                new JTextArea();

        invoiceArea.setEditable(false);

        invoiceArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        String invoice =
                "\n"
                + "              DENTAL TREATMENT INVOICE\n"
                + "\n"
                + "------------------------------------------------\n"
                + "Appointment Number : "
                + appointment.getAppointmentNumber()
                + "\n"
                + "Patient Name       : "
                + appointment.getPatientName()
                + "\n"
                + "Contact Number     : "
                + appointment.getContactNumber()
                + "\n"
                + "Dentist Name       : "
                + appointment.getDentistName()
                + "\n"
                + "Treatment          : "
                + appointment.getTreatmentType()
                + "\n"
                + "Appointment Date   : "
                + appointment.getAppointmentDate()
                + "\n"
                + "Appointment Time   : "
                + appointment.getAppointmentTime()
                + "\n"
                + "------------------------------------------------\n"
                + "Treatment Price    : "
                + currencyFormat.format(price)
                + "\n"
                + "Total Amount       : "
                + currencyFormat.format(price)
                + "\n"
                + "------------------------------------------------\n"
                + "\n"
                + "Thank you for visiting Sunrise Dental Clinic.";

        invoiceArea.setText(invoice);

        panel.add(
                new JScrollPane(invoiceArea),
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel();

        JButton printButton =
                new JButton("Print");

        JButton closeButton =
                new JButton("Close");

        printButton.addActionListener(e -> {

            try {

                invoiceArea.print();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        invoiceFrame,
                        "Unable to print the invoice.",
                        "Print Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        closeButton.addActionListener(
                e -> invoiceFrame.dispose()
        );

        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        invoiceFrame.add(panel);

        invoiceFrame.setVisible(true);
    }
}