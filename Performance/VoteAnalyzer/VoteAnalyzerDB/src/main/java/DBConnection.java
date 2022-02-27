import lombok.Setter;

import java.sql.*;

public class DBConnection {
    private static final String dbName = "voter_list";
    private static final String dbUser = "root";
    private static final String dbPass = "TestTest";
    private static StringBuilder insertQuery = new StringBuilder();
    static XMLHandler xmlHandler = new XMLHandler();
    @Setter
    private static int limit = 3_000_000;

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

    public static void executeMultiInsert() {
        long startTime = System.currentTimeMillis();
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery + "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
        try {
            DBConnection.getConnection().createStatement().execute(sql);
            insertQuery.setLength(0);
            long  endTime = (System.currentTimeMillis() - startTime) / 1000;
            if(endTime > 30){
                setLimit((int) (limit - (limit * 0.5)));
            }
            System.out.println("Время на загрузку партии: " + endTime + " сек.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void flush() throws SQLException {
        if (insertQuery.length() != 0) {
            executeMultiInsert();
        }
    }


    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") +
                "('" + name + "', '" + birthDay + "', 1)");
        if(insertQuery.length() > 3_000_000) {
            executeMultiInsert();
            insertQuery.setLength(0);
        }
    }
}
