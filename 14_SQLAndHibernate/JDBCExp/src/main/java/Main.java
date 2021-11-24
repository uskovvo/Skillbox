import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "eC9955956682";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(subscription_date)/12 FROM PurchaseList " +
                                                             "WHERE YEAR(subscription_date) = 2018 GROUP BY course_name");

            while(resultSet.next()){
                String courseName = resultSet.getString("course_name");
                String countSubscription = resultSet.getString("COUNT(subscription_date)/12");
                System.out.println(courseName + " - " + countSubscription);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
