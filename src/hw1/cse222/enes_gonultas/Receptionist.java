package hw1.cse222.enes_gonultas;

/**
 * Defines Receptionist.
 *
 * @see Guest
 * @author Enes Gonultas
 */
public class Receptionist extends AbstractUser {

    public Receptionist(String name) {
        super(name, UserType.SUPER);
    }

    /**
     * Books a room. As a super user Receptionist doesn't check for
     * availability of the room. Books the room via {@code HotelSys} even if
     * it is not available.
     *
     * @see HotelSys
     * @param roomNumber requested room number
     */
    @Override
    public void bookARoom(int roomNumber) {
        HotelSys sys = HotelSys.getInstance();
        sys.bookARoom(this, roomNumber);
    }

    /**
     * Checks a room in.
     *
     * @param roomNumber number of the room that will be checked in
     */
    public void checkIn(int roomNumber) {
        HotelSys sys = HotelSys.getInstance();
        sys.checkIn(roomNumber);
    }

    /**
     * Checks a room out.
     *
     * @param roomNumber number of the room that will be checked out
     */
    public void checkOut(int roomNumber) {
        HotelSys sys = HotelSys.getInstance();
        sys.checkOut(roomNumber);
    }

    /**
     * Cancels a reservation.
     *
     * @param roomNumber number of the room
     */
    public void cancelReservation(int roomNumber) {
        HotelSys sys = HotelSys.getInstance();
        sys.cancelReservation(this, roomNumber);
    }
}
