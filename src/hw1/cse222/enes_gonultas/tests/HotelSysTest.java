package hw1.cse222.enes_gonultas.tests;

import hw1.cse222.enes_gonultas.Guest;
import hw1.cse222.enes_gonultas.HotelSys;
import hw1.cse222.enes_gonultas.Receptionist;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the {@code HotelSys} class.
 *
 * @author Enes Gonultas
 */
class HotelSysTest {
    private HotelSys sys;
    private Receptionist receptionist;
    private Guest guest;

    @BeforeEach
    void init() {
        sys = HotelSys.getInstance();
        sys.reset();
        receptionist = new Receptionist("admin");
        guest = new Guest("guest");
    }

    /*
    Tests bookARoom() method.
     */
    @org.junit.jupiter.api.Test
    void bookARoom() {
        // Receptionist can book a room
        sys.bookARoom(receptionist, 1);
        assertTrue(sys.getRoom(1).isReserved());
        assertEquals(sys.getRoom(1).getReserver(), receptionist.getUserName());

        // Guest can book a room
        sys.bookARoom(guest, 2);
        assertTrue(sys.getRoom(2).isReserved());
        assertEquals(sys.getRoom(2).getReserver(), guest.getUserName());

        // Receptionist, as a super user, can override a reservation
        sys.bookARoom(receptionist, 2);
        assertEquals(sys.getRoom(1).getReserver(), receptionist.getUserName());

        // Guest, as a standard user can not override a reservation
        sys.bookARoom(guest, 2);
        assertEquals(sys.getRoom(1).getReserver(), receptionist.getUserName());
    }

    /*
    Tests cancelReservation() method.
     */
    @org.junit.jupiter.api.Test
    void cancelReservation() {
        sys.bookARoom(guest, 1);
        sys.bookARoom(receptionist, 2);
        assertTrue(sys.getRoom(1).isReserved());
        assertTrue(sys.getRoom(2).isReserved());

        // Receptionist can not cancel reservations
        sys.cancelReservation(receptionist, 1);
        assertTrue(sys.getRoom(1).isReserved());

        // Guest can cancel reservations made by themselves
        sys.cancelReservation(guest, 1);
        assertFalse(sys.getRoom(1).isReserved());

        // Guest can not cancel reservations made by another user
        sys.cancelReservation(guest, 2);
        assertTrue(sys.getRoom(2).isReserved());
    }

    /*
    Tests checkIn() and checkOut() methods.
     */
    @org.junit.jupiter.api.Test
    void checkInAndCheckOut() {
        sys.bookARoom(receptionist, 1);
        assertTrue(sys.getRoom(1).isReserved());

        // Receptionist can check a room in
        sys.checkIn(1);
        assertTrue(sys.getRoom(1).isReserved());
        assertTrue(sys.getRoom(1).isCheckedIn());

        // Receptionist can check a room out
        sys.checkOut(1);
        assertFalse(sys.getRoom(1).isReserved());
        assertFalse(sys.getRoom(1).isCheckedIn());
    }
}