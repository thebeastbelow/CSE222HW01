package hw1.cse222.enes_gonultas;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the core functionality of the hotel management system.
 * {@code HotelSys} reads records from database, changes records in according
 * to user needs, then saves everything back to database.
 * <p>
 * In the "database.csv" file, records kept as lines like:
 * "isRoomReserved, isRoomCheckedIn, userName"
 * while line number being index of the room. First three boolean variables
 * can take the values 0 or 1, means {@code false} or {@code true}
 * respectively. The last optional one is the user name of the user that
 * reserved the room, if room is reserved.
 * <p>
 * {@code HotelSys} designed to be a "Singleton", namely, only one instance of
 * the {@code HotelSys} can exit at any moment. This prevent some bad things
 * -like two different {@code HotelSys} instances changing the database file
 * asynchronously- from happening.
 *
 * @author Enes Gonultas
 */
public class HotelSys {
    private final String CSV_FILE_NAME = "database.csv";
    private static HotelSys uniqueSystemInstance;
    private Room[] rooms;

    /*
    Private constructor, because HotelSys is a Singleton, it must not be
    instantiated outside of the class.
     */
    private HotelSys() {
        setup();
    }

    /**
     * Returns the unique HotelSys instance. A crucial part of the singleton
     * design pattern, a static method for getting "the one". If there isn't a
     * HotelSys instance already, creates one.
     *
     * @return the unique hotel system instance
     */
    public static synchronized HotelSys getInstance() {
        if (uniqueSystemInstance == null) {
            uniqueSystemInstance = new HotelSys();
        }
        return uniqueSystemInstance;
    }

    /*
    Sets up the rooms. Reads record from the database. If no records are found,
    creates brand new rooms.
     */
    private void setup() {
        if ((rooms = readCsvFile()) == null) {
            rooms = new Room[10];
            for (int i = 0; i < rooms.length; i++) {
                rooms[i] = new Room(false, false, "");
            }
        }
    }

    /**
     * Resets all rooms to default state.
     */
    public void reset() {
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room(false, false, "");
        }
    }

    /*
    Reads the csv file for room records.
     */
    private Room[] readCsvFile() {
        // try to read records from csv file
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(CSV_FILE_NAME),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }

        // if file exists create room instances according to the data
        Room[] rooms = new Room[10];
        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            rooms[i] = new Room(data[0].equals("1"), data[1].equals("1"),
                    (data.length == 3 ? data[2] : ""));
        }

        return rooms;
    }

    /*
    Writes records to database file.
     */
    void writeCsvFile() {
        ArrayList<String> lines = new ArrayList<>();
        // create data lines
        for (Room room: rooms) {
            lines.add(String.join(",",
                    room.isReserved() ? "1" : "0",
                    room.isCheckedIn() ? "1" : "0",
                    room.getReserver() == null ? "" : room.getReserver()));
        }

        // try to save records to the csv file
        try {
            Files.write(Paths.get(CSV_FILE_NAME), lines);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    /**
     * Returns whether the requested room is available.
     *
     * @param roomNumber number of the room
     * @return true if the room is available, false otherwise
     */
    public boolean isRoomAvailable(int roomNumber) {
        return (1 <= roomNumber && roomNumber <= 10)
                && !rooms[roomNumber-1].isReserved();
    }

    /**
     * Books a room.
     *
     * @param user user that wants to book the room
     * @param roomNumber number of the room
     */
    public void bookARoom(User user, int roomNumber) {
        if (1 <= roomNumber && roomNumber <= 10) {
            rooms[roomNumber-1].reserve(user);
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Cancels a room reservation.
     *
     * @param user user that wants to cancel reservation
     * @param roomNumber number of the room
     */
    public void cancelReservation(User user, int roomNumber) {
        if (1 <= roomNumber && roomNumber <= 10) {
            rooms[roomNumber-1].cancelReservation(user);
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Checks in a room.
     *
     * @param roomNumber number of the room
     */
    public void checkIn(int roomNumber) {
        if (1 <= roomNumber && roomNumber <= 10) {
            rooms[roomNumber-1].checkIn();
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Checks out a room.
     *
     * @param roomNumber number of the room
     */
    public void checkOut(int roomNumber) {
        if (1 <= roomNumber && roomNumber <= 10) {
            rooms[roomNumber-1].checkOut();
        } else {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Returns the smallest available room number or 0 if there is no
     * available room at the moment.
     *
     * @return the smallest available room number or 0 if no room is available
     */
    public int smallestAvailableRoomNumber() {
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].isReserved()) {
                return i+1;
            }
        }
        return 0;
    }

    /**
     * A list of Strings that represents rooms. Each element consist of a room
     * number and status of the room.
     *
     * @return list of room statuses
     */
    public ArrayList<String> listRooms() {
        ArrayList<String> roomsList = new ArrayList<>();
        for (int i = 0; i < rooms.length; i++) {
            roomsList.add("Room#" + (i < 9 ? "0" : "") + (i + 1) + " "
                    + rooms[i].getStatus() + " " + rooms[i].getReserver());
        }
        return roomsList;
    }

    /**
     * Returns a room in the Hotel.
     *
     * @param roomNumber number of the room requested
     * @return the room requested
     */
    public Room getRoom(int roomNumber) {
        return rooms[roomNumber-1];
    }
}
