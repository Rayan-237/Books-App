package dataaccesslayer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class responsible for loading database connection
 * configuration (from {@code database.properties}) and providing
 * JDBC {@link Connection} objects to the DAO layer.
 * <p>
 * Implements lazy initialization with thread safety to ensure the
 * instance is created only once.
 */
public final class DataSource {

    /** Singleton instance */
    private static volatile DataSource instance;

    private String url;
    private String user;
    private String password;

    /**
     * Private constructor to prevent instantiation.
     * Loads DB connection properties from classpath.
     */
    private DataSource() {
        loadProperties();
    }

    /**
     * Returns the Singleton instance of the DataSource.
     *
     * @return the single {@code DataSource} instance.
     */
    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    /**
     * Loads JDBC configuration (URL, user, password) from
     * {@code database.properties}.
     *
     * @throws RuntimeException if the file is missing or unreadable.
     */
    private void loadProperties() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (in == null) {
                throw new IllegalStateException("database.properties not found");
            }

            props.load(in);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException("Unable to load database properties", e);
        }
    }

    /**
     * Provides a fresh JDBC Connection to the books database.
     *
     * @return a valid JDBC Connection.
     * @throws SQLException if a connection cannot be established.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
