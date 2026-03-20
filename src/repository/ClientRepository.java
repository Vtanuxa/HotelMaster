package repository;

import model.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    public Client save(Client client) {
        clients.add(client);
        return client;
    }

    public Optional<Client> findById(int id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    public Optional<Client> findByEmail(String email) {
        return clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();
    }

    public Optional<Client> findByPhone(String phone) {
        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst();
    }

    public Optional<Client> findByFullName(String fullName) {
        return clients.stream()
                .filter(client -> client.getFullName().equalsIgnoreCase(fullName))
                .findFirst();
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    public boolean update(Client updateClient) {
        Optional<Client> existingClient = findById(updateClient.getId());

        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            client.setFullName(updateClient.getFullName());
            client.setEmail(updateClient.getEmail());
            client.setPhone(updateClient.getPhone());
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return clients.removeIf(client -> client.getId() == id);
    }

    public void clear() {
        clients.clear();
    }
}