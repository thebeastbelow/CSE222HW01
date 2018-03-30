package hw1.cse222.enes_gonultas;

/**
 * Implements most of the {@code User} interface methods. Only
 * {@code bookARoom} method left for concrete classes.
 *
 * @see User
 * @author Enes Gonultas
 */
public abstract class AbstractUser implements User {
    private UserType type;
    private String userName;

    AbstractUser(String userName, UserType type) {
        this.userName = userName;
        this.type = type;
    }

    @Override
    public abstract void bookARoom(int roomNumber);

    @Override
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
