import java.sql.SQLException;

/**
 * Created by Menelaos Kotsollaris on 2017-01-20.
 *
 * Class Explanation: TODO
 */
public class HelloWorld
{
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException
    {
        PostgresHelper
                postgresHelper =
                new PostgresHelper(DbContract.HOST, DbContract.DB_NAME,
                                   DbContract.USERNAME, DbContract.PASSWORD);
        postgresHelper.connect();
        System.out.println("hey");
    }
}
