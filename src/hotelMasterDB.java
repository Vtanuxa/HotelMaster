import java.sql.*;
import java.util.Scanner;

public class hotelMasterDB {

    static final String URL = "jdbc:mysql://localhost:3306/hotelMasterDB";
    static final String USER = "root";
    static final String PASSWORD = "9999";

    public static void main(String[] args) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your full name");
            String fullName = scanner.nextLine();
            System.out.println("Enter your phone number");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter your email");
            String email = scanner.nextLine();
            System.out.println("Enter your number of passport");
            String passport = scanner.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (fullName, phone, email, passport) VALUES  (?, ?, ?, ?)");
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, passport);
            preparedStatement.executeUpdate();
            System.out.println("Inserted new client successfully");

            statement = connection.createStatement();
//          statement.execute("INSERT INTO Client (fullName, phone, email, passport) VALUE  ('Николаев Николай Николаевич', '+7-999-123-45-68', 'kolya@example.com', '1111 567890');");
//            System.out.println("Inserted successfully");

            resultSet = statement.executeQuery("SELECT * FROM client");

//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String fullName = resultSet.getString("fullName");
//                String phone = resultSet.getString("phone");
//                String email = resultSet.getString("email");
//                String passport = resultSet.getString("passport");
//                System.out.println("==============================================================================================================");
////                System.out.println(id + " | " + fullName + " | " + phone + " | " + email + " | " + passport + " | ");
//                System.out.printf("ID: %d ФИО: %s phone: %s email: %s passport: %s\n", id, fullName, phone, email, passport);
//            }
////
//            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
