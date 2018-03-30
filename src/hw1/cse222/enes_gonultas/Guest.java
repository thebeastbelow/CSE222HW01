package hw1.cse222.enes_gonultas;

/**
 * Defines hotel guests.
 *
 * @see Receptionist
 * @author Enes Gonultas
 */
public class Guest extends AbstractUser {

    public Guest(String name) {
        super(name, UserType.STANDARD);
    }

    /**
     * Books a room. First, checks availability of the room. If it's available,
     * books the room via {@code HotelSys}.
     *
     * @see HotelSys
     * @param roomNumber requested room number
     */
    @Override
    public void bookARoom(int roomNumber) {
        HotelSys sys = HotelSys.getInstance();
        if (sys.isRoomAvailable(roomNumber)) {
            sys.bookARoom(this, roomNumber);
        } else {
            System.out.println("An error occurred.");
        }
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
