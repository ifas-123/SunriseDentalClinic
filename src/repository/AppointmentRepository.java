package repository;

import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRepository {

    public boolean saveAppointment(Appointment appointment) {

        String sql = """
                INSERT INTO appointments
                (
                    appointment_number,
                    patient_name,
                    address,
                    contact_number,
                    dentist_name,
                    treatment_type,
                    appointment_date,
                    appointment_time
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    appointment.getAppointmentNumber()
            );

            statement.setString(
                    2,
                    appointment.getPatientName()
            );

            statement.setString(
                    3,
                    appointment.getAddress()
            );

            statement.setString(
                    4,
                    appointment.getContactNumber()
            );

            statement.setString(
                    5,
                    appointment.getDentistName()
            );

            statement.setString(
                    6,
                    appointment.getTreatmentType()
            );

            statement.setDate(
                    7,
                    java.sql.Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            statement.setTime(
                    8,
                    java.sql.Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            int rowsAffected =
                    statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error saving appointment: "
                            + e.getMessage()
            );

            return false;
        }
    }

    public Appointment findAppointmentByNumber(
            int appointmentNumber) {

        String sql = """
                SELECT *
                FROM appointments
                WHERE appointment_number = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    appointmentNumber
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return new Appointment(
                        resultSet.getInt(
                                "appointment_number"
                        ),
                        resultSet.getString(
                                "patient_name"
                        ),
                        resultSet.getString(
                                "address"
                        ),
                        resultSet.getString(
                                "contact_number"
                        ),
                        resultSet.getString(
                                "dentist_name"
                        ),
                        resultSet.getString(
                                "treatment_type"
                        ),
                        resultSet.getDate(
                                "appointment_date"
                        ).toLocalDate(),
                        resultSet.getTime(
                                "appointment_time"
                        ).toLocalTime()
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error searching appointment: "
                            + e.getMessage()
            );
        }

        return null;
    }

    public double getTreatmentPrice(
            String treatmentName) {

        String sql = """
                SELECT price
                FROM treatments
                WHERE treatment_name = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    treatmentName
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getDouble("price");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error retrieving treatment price: "
                            + e.getMessage()
            );
        }

        return -1;
    }
}