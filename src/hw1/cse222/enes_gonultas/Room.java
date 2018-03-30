package hw1.cse222.enes_gonultas;

/**
 * Represents a room in the hotel. Users can reserve, checkIn and
 * checkOut a room.
 *
 * @see HotelSys
 * @author Enes Gonultas
 */
public class Room {
    private boolean reserved, checkedIn;
    private String reserver;

    Room(boolean reserved, boolean checkedIn, String reserver) {
        this.reserved = reserved;
        this.checkedIn = checkedIn;
        this.reserver = reserver;
    }

    /**
     * Reserves the room instance.
     *
     * @param user user that requested room to be reserved
     */
    public void reserve(User user) {
        setReserver(user.getUserName());
        setReserved(true);
        System.out.println("Room successfully reserved.");
    }

    /**
     * Cancels reservation for room instance.
     *
     * @param user user that requested reservation to be canceled
     */
    public void cancelReservation(User user) {
        if (getReserver().equals(user.getUserName())
                && isReserved() && !isCheckedIn()) {
            setReserved(false);
            setReserver("");
            System.out.println("Reservation successfully canceled.");
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Checks in for room instance.
     */
    public void checkIn() {
        if (isReserved() && !isCheckedIn()) {
            setCheckedIn(true);
            System.out.println("Successfully checked in.");
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Checks out for room instance.
     */
    public void checkOut() {
        if (isReserved() && isCheckedIn()) {
            setReserved(false);
            setCheckedIn(false);
            setReserver("");
            System.out.println("Successfully checked out.");
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Returns whether the room is reserved.
     *
     * @return true if room is reserved, false otherwise
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     * Sets reservation status.
     *
     * @param reserved new reservation status
     */
    private void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     * Returns whether the room is checked in.
     *
     * @return true if room is checked in, false otherwise
     */
    public boolean isCheckedIn() {
        return checkedIn;
    }

    /**
     * Sets check-in status
     *
     * @param checkedIn new check-in status
     */
    private void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * Returns the user name of the user that reserved the room.
     *
     * @return user name of the reserver
     */
    public String getReserver() {
        return reserver;
    }

    /**
     * Sets the name of the user that reserved the room.
     *
     * @param reserver new user name of the reserver
     */
    private void setReserver(String reserver) {
        this.reserver = reserver;
    }

    /**
     * Returns status of a room.
     *
     * @return status text
     */
    public String getStatus() {
        String status;
        if (this.isCheckedIn()) {
            status = "checked-in";
        } else if (this.isReserved()) {
            status = "reserved";
        } else {
            status = "available";
        }
        return status;
    }
}
