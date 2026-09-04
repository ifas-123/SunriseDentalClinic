package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String HOST =
            System.getProperty(
                    "db.host",
                    "localhost"
            );

    private static final String PORT =
            System.getProperty(
                    "db.port",
                    "3306"
            );

    private static final String DATABASE =
            System.getProperty(
                    "db.name",
                    "sunrise_dental_clinic"
            );

    private static final String USER =
            System.getProperty(
                    "db.user",
                    "root"
            );

    private static final String PASSWORD =
            System.getProperty(
                    "db.password",
                    ""
            );

    private static final String URL =
            "jdbc:mysql://"
                    + HOST
                    + ":"
                    + PORT
                    + "/"
                    + DATABASE;

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}