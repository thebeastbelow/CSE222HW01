package hw1.cse222.enes_gonultas;

/**
 * Defines common methods for users. All users can book a room.
 * User's type and user name can be acquired by using getType and getUserName
 * methods. User name can be set using setUserName method.
 *
 * @see UserType
 * @author Enes Gonultas
 */

public interface User {
    /**
     * Books a room from HotelSys.
     *
     * @see HotelSys
     * @param roomNumber number of the room requested
     */
    public void bookARoom(int roomNumber);

    /**
     * Returns user type.
     *
     * @return user type
     */
    public UserType getType();

    /**
     * Sets a new user type.
     *
     * @param type new user type
     */
    public void setType(UserType type);

    /**
     * Returns user name.
     *
     * @return user name
     */
    public String getUserName();

    /**
     * Sets a new user name.
     *
     * @param userName new user name
     */
    public void setUserName(String userName);
}
