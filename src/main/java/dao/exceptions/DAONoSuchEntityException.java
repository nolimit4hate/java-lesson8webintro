package dao.exceptions;

/**
 *      this exception be throwed if the entry will not be found in data base
 */

public class DAONoSuchEntityException extends DAOException {

    public DAONoSuchEntityException(String message) {
        super(message);
    }

    public DAONoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
