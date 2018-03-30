package hw1.cse222.enes_gonultas;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tests the management system. HotelMan is the starting point of the
 * management system. It first asks user whether they are a guest or a
 * receptionist. Then list available commands and executes commands according
 * to user input.
 *
 * @see User
 * @see HotelSys
 * @author Enes Gonultas
 */
public class HotelMan {
    public static void main(String[] args) {
        Controller controller;
        Scanner sc;
        int reply = 0;
        User user;

        controller = new Controller();
        sc = new Scanner(System.in);

        while (reply != 3) {
            System.out.println("1. Guest");
            System.out.println("2. Receptionist");
            System.out.println("3. Exit");
            System.out.print("Are you a guest or receptionist?:[1/2/3] ");
            reply = sc.nextInt();
            if (reply == 1) {
                // log user in as a guest
                System.out.print("Enter a user name to login or "
                        + "create an account: ");
                String userName = sc.next();
                user = new Guest(userName);
                controller.loginAs(user);
                System.out.println("Login successful!");

                // show available commands
                ArrayList<String> commands = controller.availableCommands();
                int selectedCommand = 0;

                while (selectedCommand != 4) {
                    System.out.println();
                    commands.forEach(System.out::println);
                    System.out.print("Select a command: ");
                    selectedCommand = sc.nextInt();
                    if (selectedCommand == 1) {
                        // book a room
                        System.out.print(
                                "Select a room number to book:[1-10] ");
                        int roomNumber = sc.nextInt();
                        user.bookARoom(roomNumber);
                    } else if (selectedCommand == 2) {
                        // cancel a reservation
                        System.out.print("Select a room number to cancel " +
                                "reservation:[1-10] ");
                        int roomNumber = sc.nextInt();
                        ((Guest) user).cancelReservation(roomNumber);
                    } else if (selectedCommand == 3) {
                        // get an available room number
                        HotelSys sys = HotelSys.getInstance();
                        int smallestAvailableRoomNumber =
                                sys.smallestAvailableRoomNumber();
                        if (smallestAvailableRoomNumber > 0) {
                            System.out.println("Room " +
                                    smallestAvailableRoomNumber + " is " +
                                    "available.");
                        } else {
                            System.out.println("There is no available room. " +
                                    "Please check again later.");
                        }
                    }
                }
                controller.logOut();
            }
            else if (reply == 2) {
                System.out.print("Enter password: ");
                int secretPassword = sc.nextInt();
                if (secretPassword == 12345) {
                    user = new Receptionist("admin");
                    controller.loginAs(user);
                    System.out.println("Login successful!");
                    // show available commands
                    ArrayList<String> commands
                            = controller.availableCommands();
                    int selectedCommand = 0;

                    while (selectedCommand != 6) {
                        System.out.println();
                        commands.forEach(System.out::println);
                        System.out.print("Select a command: ");
                        selectedCommand = sc.nextInt();
                        if (selectedCommand == 1) {
                            // book a room
                            System.out.print(
                                    "Select a room number to book:[1-10] ");
                            int roomNumber = sc.nextInt();
                            user.bookARoom(roomNumber);
                        } else if (selectedCommand == 2) {
                            // check in
                            System.out.print("Select a room number to " +
                                    "check in:[1-10] ");
                            int roomNumber = sc.nextInt();
                            ((Receptionist) user).checkIn(roomNumber);
                        } else if (selectedCommand == 3) {
                            // check out
                            System.out.print("Select a room number to " +
                                    "check out:[1-10] ");
                            int roomNumber = sc.nextInt();
                            ((Receptionist) user).checkOut(roomNumber);
                        } else if (selectedCommand == 4) {
                            // list room statuses
                            HotelSys sys = HotelSys.getInstance();
                            sys.listRooms().forEach(System.out::println);
                        } else if (selectedCommand == 5) {
                            // cancel a room
                            System.out.print("Select a room number to " +
                                    "cancel:[1-10] ");
                            int roomNumber = sc.nextInt();
                            ((Receptionist) user)
                                    .cancelReservation(roomNumber);
                        }
                    }
                    controller.logOut();
                } else {
                    System.out.println("Wrong password! Try 12345");
                }
            }
        }
        // save state
        HotelSys.getInstance().writeCsvFile();
    }
}
