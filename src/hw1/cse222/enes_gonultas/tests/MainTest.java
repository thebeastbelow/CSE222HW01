package hw1.cse222.enes_gonultas.tests;

import hw1.cse222.enes_gonultas.Controller;
import hw1.cse222.enes_gonultas.Guest;
import hw1.cse222.enes_gonultas.HotelSys;
import hw1.cse222.enes_gonultas.Receptionist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Main test class. Tests all functionality of the management system.
 *
 * @author Enes Gonultas
 */
public class MainTest {
    @Test
    public static void main(String[] args) {
        Controller controller = new Controller();
        Guest guest = new Guest("guest");
        Receptionist receptionist = new Receptionist("admin");
        HotelSys sys = HotelSys.getInstance();
        sys.reset();

        controller.loginAs(guest);
        // Guest can book a room
        guest.bookARoom(1);
        assertTrue(sys.getRoom(1).isReserved());
        // Guest can cancel a reservation
        guest.cancelReservation(1);
        assertFalse(sys.getRoom(1).isReserved());

        // Receptionist can book a room
        receptionist.bookARoom(1);
        assertTrue(sys.getRoom(1).isReserved());
        // Receptionist can cancel a reservation
        receptionist.cancelReservation(1);
        assertFalse(sys.getRoom(1).isReserved());
        // Receptionist can check in a room
        receptionist.bookARoom(1);
        receptionist.checkIn(1);
        assertTrue(sys.getRoom(1).isCheckedIn());
        // Receptionist can check out a room
        receptionist.checkOut(1);
        assertFalse(sys.getRoom(1).isReserved());
        assertFalse(sys.getRoom(1).isCheckedIn());
    }
}
