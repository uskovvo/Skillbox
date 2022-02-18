import java.sql.*;

public class DBConnection {
    private static final String dbName = "voter_list";
    private static final String dbUser = "root";
    private static final String dbPass = "TestTest";
    private static StringBuilder insertQuery = new StringBuilder();

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "UNIQUE KEY (name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert()
    {
        String sql = "INSERT INTO voter_count(name, birthDate, count) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE count=count + 1";
        try {
            DBConnection.getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append(isStart ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', 1)");
        if(insertQuery.length() % 100_000 == 0){
            executeMultiInsert();
            insertQuery.setLength(0);
        }
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
