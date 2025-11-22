package transferobjects;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) representing a single record
 * from the {@code Authors} table in the database.
 * <p>
 * This class is used to move data between the DAO layer,
 * service layer, and presentation layer (Servlets) without
 * exposing database-related classes such as {@link java.sql.ResultSet}.
 * <p>
 * Implements {@link Serializable} so that objects can be easily
 * stored in session or transferred if required by future features.
 */
public class Author implements Serializable {

    /** The unique ID of the author (primary key in the database). */
    private int authorId;

    /** The author's first name. */
    private String firstName;

    /** The author's last name. */
    private String lastName;

    /**
     * Default no-argument constructor.
     * Required for frameworks and for cases where fields
     * are set manually via setters.
     */
    public Author() {}

    /**
     * Constructs a full {@code Author} DTO with all fields populated.
     *
     * @param authorId  the author's unique ID
     * @param firstName the author's first name
     * @param lastName  the author's last name
     */
    public Author(int authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the author's ID.
     *
     * @return the author ID
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the author's ID.
     *
     * @param authorId the new ID value
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Returns the author's first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the author's first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the author's last name.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the author's last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
