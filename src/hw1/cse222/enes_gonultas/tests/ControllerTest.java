package hw1.cse222.enes_gonultas.tests;

import hw1.cse222.enes_gonultas.Controller;
import hw1.cse222.enes_gonultas.Guest;
import hw1.cse222.enes_gonultas.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the {@code Controller} class.
 *
 * @author Enes Gonultas
 */
class ControllerTest {
    private Controller controller;
    private User user;

    /*
    Creates a controller and user before each test.
     */
    @BeforeEach
    void init() {
        controller = new Controller();
        user = new Guest("user");
    }

    /*
    Tests login and logout methods.
     */
    @Test
    void loginAndLogOut() {
        // A user can log in
        controller.loginAs(user);
        assertEquals(controller.getUser(), user);
        // A user can log out
        controller.logOut();
        assertEquals(controller.getUser(), null);
    }
}