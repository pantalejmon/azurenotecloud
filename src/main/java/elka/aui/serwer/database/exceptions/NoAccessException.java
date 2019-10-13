package elka.aui.serwer.database.exceptions;

import elka.aui.serwer.database.entities.Users;

public class NoAccessException extends RuntimeException {
    public NoAccessException(Users user) {
        super("No access for user " + user.getName() + " " + user.getLastName());
    }
}
