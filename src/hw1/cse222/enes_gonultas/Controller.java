package hw1.cse222.enes_gonultas;

import java.util.ArrayList;

/**
 * Manages user sessions and lists available commands for each user type.
 *
 * @see UserType
 * @author Enes Gonultas
 */
public class Controller {
    private User user;

    /**
     * Logs a user in.
     *
     * @param user user that requested login
     */
    public void loginAs(User user) {
        this.user = user;
    }

    /**
     * Logs currently logged-in user out.
     */
    public void logOut() {
        user = null;
    }

    /**
     * Returns currently logged-in user.
     *
     * @return current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Creates and returns a list of available commands according to type of
     * the currently logged-in user.
     *
     * @return command list
     */
    public ArrayList<String> availableCommands() {
        ArrayList<String> commands = new ArrayList<>();
        if (user != null) {
            if (user.getType() == UserType.STANDARD) {
                commands.add("1. Book A Room");
                commands.add("2. Cancel A Reservation");
                commands.add("3. Get An Available Room Number");
                commands.add("4. Log Out");
            } else {
                commands.add("1. Book A Room");
                commands.add("2. Check In");
                commands.add("3. Check Out");
                commands.add("4. List Rooms");
                commands.add("5. Cancel A Reservation");
                commands.add("6. Log Out");
            }
        }

        return commands;
    }
}
