package dataaccesslayer;

import transferobjects.Author;
import java.util.List;

/**
 * Data Access Object (DAO) interface defining the CRUD operations
 * for interacting with the {@code Authors} table in the database.
 * <p>
 * All DAO implementations (e.g., {@link AuthorDAOImpl}) must implement
 * these methods using JDBC.
 */
public interface AuthorDAO {

    /**
     * Retrieves all authors from the database.
     *
     * @return a List of {@link Author} objects.
     * @throws DAOException if a database access error occurs.
     */
    List<Author> getAllAuthors() throws DAOException;

    /**
     * Retrieves a single author by its unique ID.
     *
     * @param authorId the ID of the author to retrieve.
     * @return the matching {@link Author}, or {@code null} if not found.
     * @throws DAOException if a database access error occurs.
     */
    Author getAuthorById(int authorId) throws DAOException;

    /**
     * Inserts a new author into the database.
     *
     * @param author the author object containing first and last name.
     * @throws DAOException if a database access error occurs.
     */
    void addAuthor(Author author) throws DAOException;

    /**
     * Updates an existing author's first/last name by ID.
     *
     * @param author the author object containing updated fields.
     * @return true if an author record was updated, false otherwise.
     * @throws DAOException if a database access error occurs.
     */
    boolean updateAuthor(Author author) throws DAOException;

    /**
     * Deletes an author by ID.
     *
     * @param authorId the ID of the author to delete.
     * @return true if a record was deleted, false if no match found.
     * @throws DAOException if a database access error occurs.
     */
    boolean deleteAuthorById(int authorId) throws DAOException;
}
