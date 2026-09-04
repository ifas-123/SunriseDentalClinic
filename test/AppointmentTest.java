package test;

import model.Appointment;
import repository.AppointmentRepository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    void testAppointmentNumber() {

        Appointment appointment =
                new Appointment(
                        2001,
                        "Test Patient",
                        "Colombo",
                        "0712345678",
                        "Dr. Silva",
                        "Dental Cleaning",
                        LocalDate.of(2026, 9, 10),
                        LocalTime.of(10, 30)
                );

        assertEquals(
                2001,
                appointment.getAppointmentNumber()
        );
    }

    @Test
    void testPatientName() {

        Appointment appointment =
                new Appointment(
                        2002,
                        "Nimal Perera",
                        "Kandy",
                        "0712345678",
                        "Dr. Silva",
                        "Dental Cleaning",
                        LocalDate.of(2026, 9, 11),
                        LocalTime.of(11, 00)
                );

        assertEquals(
                "Nimal Perera",
                appointment.getPatientName()
        );
    }

    @Test
    void testContactNumber() {

        Appointment appointment =
                new Appointment(
                        2003,
                        "Test Patient",
                        "Colombo",
                        "0771234567",
                        "Dr. Silva",
                        "Dental Filling",
                        LocalDate.of(2026, 9, 12),
                        LocalTime.of(12, 00)
                );

        assertEquals(
                "0771234567",
                appointment.getContactNumber()
        );
    }

    @Test
    void testTreatmentType() {

        Appointment appointment =
                new Appointment(
                        2004,
                        "Test Patient",
                        "Colombo",
                        "0781234567",
                        "Dr. Silva",
                        "Root Canal",
                        LocalDate.of(2026, 9, 13),
                        LocalTime.of(13, 00)
                );

        assertEquals(
                "Root Canal",
                appointment.getTreatmentType()
        );
    }

    @Test
    void testFindExistingAppointment() {

        AppointmentRepository repository =
                new AppointmentRepository();

        Appointment appointment =
                repository.findAppointmentByNumber(1001);

        assertNotNull(
                appointment
        );

        assertEquals(
                1001,
                appointment.getAppointmentNumber()
        );
    }

    @Test
    void testFindNonExistingAppointment() {

        AppointmentRepository repository =
                new AppointmentRepository();

        Appointment appointment =
                repository.findAppointmentByNumber(999999);

        assertNull(
                appointment
        );
    }

    @Test
    void testTreatmentPrice() {

        AppointmentRepository repository =
                new AppointmentRepository();

        double price =
                repository.getTreatmentPrice(
                        "Dental Cleaning"
                );

        assertEquals(
                5000.00,
                price,
                0.01
        );
    }

    @Test
    void testInvalidTreatmentPrice() {

        AppointmentRepository repository =
                new AppointmentRepository();

        double price =
                repository.getTreatmentPrice(
                        "Invalid Treatment"
                );

        assertEquals(
                -1,
                price
        );
    }
}