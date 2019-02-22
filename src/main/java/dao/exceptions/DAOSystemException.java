package dao.exceptions;

/**
 *      some exception to replace SQLException for a while hardcoding DAO
 */

public class DAOSystemException extends DAOException {

    public DAOSystemException(String message) {
        super(message);
    }

    public DAOSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
