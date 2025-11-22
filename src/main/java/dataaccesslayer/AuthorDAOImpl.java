package dataaccesslayer;

import transferobjects.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC-based implementation of the {@link AuthorDAO} interface.
 * <p>
 * This class performs all SQL operations for the {@code Authors} table
 * including SELECT, INSERT, UPDATE, and DELETE queries.
 */
public class AuthorDAOImpl implements AuthorDAO {

    private static final String SELECT_ALL =
            "SELECT AuthorID, FirstName, LastName FROM Authors";

    private static final String SELECT_BY_ID =
            "SELECT AuthorID, FirstName, LastName FROM Authors WHERE AuthorID = ?";

    private static final String INSERT =
            "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";

    private static final String UPDATE =
            "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";

    private static final String DELETE =
            "DELETE FROM Authors WHERE AuthorID = ?";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> authors = new ArrayList<>();

        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Author a = new Author();
                a.setAuthorId(rs.getInt("AuthorID"));
                a.setFirstName(rs.getString("FirstName"));
                a.setLastName(rs.getString("LastName"));
                authors.add(a);
            }

        } catch (SQLException e) {
            throw new DAOException("Error retrieving all authors", e);
        }

        return authors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getAuthorById(int authorId) throws DAOException {
        Author author = null;

        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, authorId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    author = new Author();
                    author.setAuthorId(rs.getInt("AuthorID"));
                    author.setFirstName(rs.getString("FirstName"));
                    author.setLastName(rs.getString("LastName"));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error retrieving author by ID", e);
        }

        return author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAuthor(Author author) throws DAOException {

        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error adding author", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateAuthor(Author author) throws DAOException {

        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setInt(3, author.getAuthorId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Error updating author", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteAuthorById(int authorId) throws DAOException {

        try (Connection con = DataSource.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setInt(1, authorId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Error deleting author", e);
        }
    }
}
