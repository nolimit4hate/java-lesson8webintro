package dao.exceptions;

/**
 *      this exception be throwed if the entry already exists in data base
 */

public class DAOEntryAlreadyExistsException extends DAOException {

    public DAOEntryAlreadyExistsException(String message) {
        super(message);
    }

    public DAOEntryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
