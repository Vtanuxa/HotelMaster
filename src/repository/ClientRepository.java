package repository;

import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static connection.DBConnection.connection;

public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    public Client save(Client client) {
        String saveClient = "INSERT INTO clients (fullName, phone, email, passport) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveClient);

            preparedStatement.setString(1, client.getFullName());
            preparedStatement.setString(2, client.getPhone());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPassport());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return client;
    }

    public Client findById(int id) throws SQLException {
        String findClient = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(findClient)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("fullName"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("passport"));
                return client;
            }
        }
        return null;
    }

    public Optional<Client> findByEmail(String email) {
        return clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();
    }

//    public Optional<Client> findByPhone(String phone) {
//        return clients.stream()
//                .filter(client -> client.getPhone().equals(phone))
//                .findFirst();
//    }
//
//    public Optional<Client> findByFullName(String fullName) {
//        return clients.stream()
//                .filter(client -> client.getFullName().equalsIgnoreCase(fullName))
//                .findFirst();
//    }
//
//    public List<Client> findAll() {
//        return new ArrayList<>(clients);
//    }
//
//    public boolean update(Client updateClient) {
//        Optional<Client> existingClient = findById(updateClient.getId());
//
//        if (existingClient.isPresent()) {
//            Client client = existingClient.get();
//            client.setFullName(updateClient.getFullName());
//            client.setEmail(updateClient.getEmail());
//            client.setPhone(updateClient.getPhone());
//            return true;
//        }
//        return false;
//    }
//
//    public boolean delete(int id) {
//        return clients.removeIf(client -> client.getId() == id);
//    }
//
//    public void clear() {
//        clients.clear();
//    }
}