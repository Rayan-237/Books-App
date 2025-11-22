package dataaccesslayer;

/**
 * Custom exception class used to wrap all database-related errors
 * encountered in the Data Access Layer (DAO).
 * <p>
 * This allows higher layers (e.g., business layer, servlets)
 * to catch a single, meaningful exception type instead of dealing
 * with raw SQL exceptions.
 */
public class DAOException extends Exception {

    /**
     * Constructs a DAOException with a detailed message and cause.
     *
     * @param message a human-readable explanation of the error.
     * @param cause   the original exception that triggered the failure.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
