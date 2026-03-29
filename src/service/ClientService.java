package service;

import model.Client;
import repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {
    private ClientRepository clientRepository;
    private Scanner scanner;

    public ClientService() {
        this.clientRepository = new ClientRepository();
        this.scanner = new Scanner(System.in);
    }

    public Client addClient() {
        System.out.println("\n ДОБАВЛЕНИЕ НОВОГО КЛИЕНТА");
        System.out.println("Введите пожалуйста ФИО: ");
        String fullName = scanner.nextLine();
        System.out.println("Введите пожалуйста номер телефона: ");
        String phone = scanner.nextLine();
        System.out.println("Введите пожалуйста email: ");
        String email = scanner.nextLine();
        System.out.println("Введите пожалуйста паспортные данные: ");
        String passport = scanner.nextLine();

        Optional<Client> existingClient = clientRepository.findByPhone(phone);
        if (existingClient.isPresent()) {
            System.out.println("Клиент с номером телефона: " + phone + " уже существует!");
            return null;
        }
        Client client = new Client(fullName, phone, email, passport);
        Client newClient = clientRepository.save(client);
        System.out.println("Клиент успешно добавлен! ID: " + newClient.getId());
        return newClient;
    }

    public void AllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            System.out.println("📭 Список клиентов пуст");
            return;
        }

        System.out.println("\nID | ФИО | Телефон | Паспорт");
        System.out.println("----------------------------------------");

        for (Client client : clients) {
            System.out.printf("%d | %s | %s | %s%n",
                    client.getId(),
                    client.getFullName(),
                    client.getPhone(),
                    client.getPassport());
        }

        System.out.println("----------------------------------------");
        System.out.println("Всего клиентов: " + clients.size());
    }

    public boolean updateClient() {
        System.out.println("\n✏️ РЕДАКТИРОВАНИЕ КЛИЕНТА");
        System.out.println("─────────────────────────────────");

        AllClients();

        System.out.print("\nВведите ID клиента для редактирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            System.out.println("\nКлиент с ID " + id + " не найден!");
            return false;
        }

        Client client = clientOptional.get();
        System.out.println("\nТекущие данные:");
        System.out.println(client);
        System.out.println("\nВведите новые данные: ");

        System.out.print("Новое ФИО [" + client.getFullName() + "]: ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()) {
            client.setFullName(newFullName);
        }

        System.out.print("Новый телефон [" + client.getPhone() + "]: ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            Optional<Client> existingClient = clientRepository.findByPhone(newPhone);
            if (existingClient.isPresent()) {
                System.out.println("Клиент с номером телефона " + newPhone + " уже есть!");
                return false;
            }
            client.setPhone(newPhone);
        }

        System.out.print("Новый email [" + client.getEmail() + "]: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            Optional<Client> existingClient = clientRepository.findByEmail(newEmail);
            if (existingClient.isPresent()) {
                System.out.println("Клиент с Email " + newEmail + " уже есть!");
                return false;
            }
            client.setEmail(newEmail);
        }

        System.out.print("Новый паспорт [" + client.getPassport() + "]: ");
        String newPassport = scanner.nextLine();
        if (!newPassport.isEmpty()) {
            client.setPassport(newPassport);
        }

        if (clientRepository.update(client)) {
            System.out.println("\nКлиент успешно обновлен!");
            System.out.println(client);
            return true;
        } else {
            System.out.println("\n Ошибка при обновлении клиента!");
            return false;
        }
    }

    public boolean deleteClient() {
        System.out.println("\nУДАЛЕНИЕ КЛИЕНТА");
        System.out.println("──────────────────");

        AllClients();

        System.out.print("Введите ID клиента для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            System.out.println("Клиент с ID " + id + " не найден!");
            return false;
        }
        Client client = clientOptional.get();
        System.out.print("Удалить клиента " + client.getFullName() + "? (да/нет): ");
        String input = scanner.nextLine();

        if (!input.equals("да")) {
            System.out.println("\n Удаление клиента с ID = " + client.getId() + " отменено");
            return false;
        } else {
            clientRepository.delete(id);
            System.out.print("\n Удаление комнаты c ID = " + client.getId() + " ...");
            System.out.println();
        }

        if (clientRepository.findById(id).isPresent()) {
            System.out.println("Ошибка удаления");
            return false;
        }
        System.out.println("\n Клиент с ID =" + client.getId() + " удален!");
        return false;
    }


    public void findClientById() {
        System.out.println("\nПОИСК КЛИЕНТА ПО ID");
        System.out.println("──────────────────────");

        System.out.print("Введите ID клиента: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            System.out.println("Клиент с ID " + id + " не найден!");
            return;
        }

        Client client = clientOptional.get();
        System.out.println(client);
    }

    public void findClientByEmail() {
        System.out.println("\n🔍 ПОИСК КЛИЕНТА ПО EMAIL");
        System.out.println("──────────────────────────");

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        Optional<Client> clientOptional = clientRepository.findByEmail(email);

        if (clientOptional.isEmpty()) {
            System.out.println("Клиент с email " + email + " не найден!");
            return;
        }

        Client client = clientOptional.get();
        System.out.println(client);
    }

    public void findClientByPhone() {
        System.out.println("\n🔍 ПОИСК КЛИЕНТА ПО ТЕЛЕФОНУ");
        System.out.println("──────────────────────────");

        System.out.print("Введите номер телефона: ");
        String inputPhone = scanner.nextLine();

        Optional<Client> clientOptional = clientRepository.findByPhone(inputPhone);

        if (clientOptional.isEmpty()) {
            System.out.println("Клиент с телефоном: " + inputPhone + " не найден!");
            return;
        }

        Client client = clientOptional.get();
        System.out.println(client);
    }
}