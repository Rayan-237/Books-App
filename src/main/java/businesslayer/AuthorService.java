package businesslayer;

import java.util.List;
import dataaccesslayer.*;
import transferobjects.Author;

/**
 * Service layer class that acts as the intermediary between the
 * presentation layer (Servlets) and the data access layer (DAO).
 * <p>
 * This class contains business logic for all operations related to
 * {@link Author} objects and delegates CRUD operations to the
 * underlying {@link AuthorDAO} implementation.
 */
public class AuthorService {

    /** DAO used to perform all database operations for authors. */
    private final AuthorDAO dao = new AuthorDAOImpl();

    /**
     * Retrieves all authors from the database.
     *
     * @return a list of {@link Author} objects.
     * @throws DAOException if a database access error occurs.
     */
    public List<Author> getAllAuthors() throws DAOException {
        return dao.getAllAuthors();
    }

    /**
     * Retrieves a single author by ID.
     *
     * @param id the author ID to search for.
     * @return the matching {@link Author}, or {@code null} if not found.
     * @throws DAOException if a database access error occurs.
     */
    public Author getById(int id) throws DAOException {
        return dao.getAuthorById(id);
    }

    /**
     * Adds a new author to the database.
     *
     * @param fn the first name of the author.
     * @param ln the last name of the author.
     * @throws DAOException if an insert operation fails.
     */
    public void add(String fn, String ln) throws DAOException {
        dao.addAuthor(new Author(0, fn, ln));
    }

    /**
     * Updates an existing author using their ID.
     *
     * @param id the ID of the author to update.
     * @param fn the new first name.
     * @param ln the new last name.
     * @return true if the update affected a row, false otherwise.
     * @throws DAOException if an update operation fails.
     */
    public boolean update(int id, String fn, String ln) throws DAOException {
        return dao.updateAuthor(new Author(id, fn, ln));
    }

    /**
     * Deletes an author from the database by ID.
     *
     * @param id the ID of the author to delete.
     * @return true if a record was removed, false otherwise.
     * @throws DAOException if a delete operation fails.
     */
    public boolean delete(int id) throws DAOException {
        return dao.deleteAuthorById(id);
    }
}
