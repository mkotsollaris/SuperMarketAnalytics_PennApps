import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

/**
 * Created by Menelaos Kotsollaris on 2017-01-20.
 *
 * Class Explanation: TODO
 */
class PostgresHelper
{
    private Connection conn;
    private String host;
    private String dbName;
    private String user;
    private String pass;

    protected PostgresHelper()
    {
    }

    PostgresHelper(String host, String dbName, String user, String pass)
    {
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    boolean connect() throws SQLException, ClassNotFoundException
    {
        if(host.isEmpty() || dbName.isEmpty() || user.isEmpty() ||
                pass.isEmpty())
        {
            throw new SQLException("Database credentials missing");
        }

        Class.forName("org.postgresql.Driver");
        this.conn =
                DriverManager.getConnection(this.host + this.dbName, this.user,
                                            this.pass);
        return true;
    }

    ResultSet execQuery(String query) throws SQLException
    {
        return this.conn.createStatement().executeQuery(query);
    }

    int addImage(byte[] imageBytes) throws SQLException
    {
        PostgresHelper
                client =
                new PostgresHelper(DbContract.HOST, DbContract.DB_NAME,
                                   DbContract.USERNAME, DbContract.PASSWORD);
        String insert = "INSERT INTO Records" +
                "(" + COLUMNS.ID + "," + COLUMNS.NAME + "," +
                COLUMNS.IMAGE + ")";
        String values = COLUMNS.ID + "," +
                COLUMNS.NAME + "," + COLUMNS.IMAGE + "\');";
        String query = insert + " VALUES (" + values;
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setBytes(2, imageBytes);
        int result = statement.executeUpdate();
        statement.close();
        return result;
    }

    private enum COLUMNS
    {
        ID("id"),
        NAME("name"),
        IMAGE("image");

        private String name;

        COLUMNS(String name)
        {
            this.name = name;
        }
    }
}