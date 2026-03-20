package service;

import model.Client;
import repository.ClientRepository;


public class ClientService {
    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public Client addClient(String fullName, String email, String phone) {
        if (clientRepository.findByEmail(email).isPresent()) {
            System.out.println("❌ Ошибка: Клиент с email " + email + " уже существует!");
            return null;
        }
        Client client = new Client();
        return clientRepository.save(client);
//
//        // Проверяем, нет ли уже такого телефона
//        if (clientRepository.phoneExists(phone)) {
//            System.out.println("❌ Ошибка: Клиент с телефоном " + phone + " уже существует!");
//            return null;
//        }
//
//        Client client = new Client();
//        Client savedClient = clientRepository.save(client);
//        System.out.println("✅ Клиент " + fullName + " успешно добавлен! ID: " + savedClient.getId());
//        return savedClient;
//    }
//
//    // 📋 ПОКАЗ ВСЕХ КЛИЕНТОВ
//    public void printAllClients() {
//        List<Client> clients = clientRepository.findAll();
//
//        if (clients.isEmpty()) {
//            System.out.println("📭 Список клиентов пуст");
//            return;
//        }
//
//        System.out.println("\n┌────┬────────────────────┬──────────────────────────┬────────────────┐");
//        System.out.println("│ ID │ ФИО                 │ Email                    │ Телефон        │");
//        System.out.println("├────┼────────────────────┼──────────────────────────┼────────────────┤");
//
//        for (Client client : clients) {
//            System.out.printf("│ %-2d │ %-18s │ %-24s │ %-14s │%n",
//                    client.getId(),
//                    truncate(client.getFullName(), 18),
//                    truncate(client.getEmail(), 24),
//                    truncate(client.getPhone(), 14));
//        }
//
//        System.out.println("└────┴────────────────────┴──────────────────────────┴────────────────┘");
//        System.out.println("Всего клиентов: " + clients.size());
//    }
//
//    // ✏️ РЕДАКТИРОВАНИЕ КЛИЕНТА
//    public boolean updateClient() {
//        System.out.println("\n✏️  РЕДАКТИРОВАНИЕ КЛИЕНТА");
//        System.out.println("─────────────────────────");
//
//        printAllClients();
//
//        System.out.print("Введите ID клиента для редактирования: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        Optional<Client> clientOptional = clientRepository.findById(id);
//
//        if (clientOptional.isEmpty()) {
//            System.out.println("❌ Ошибка: Клиент с ID " + id + " не найден!");
//            return false;
//        }
//
//        Client client = clientOptional.get();
//
//        System.out.println("Текущие данные:");
//        System.out.println("  ФИО: " + client.getFullName());
//        System.out.println("  Email: " + client.getEmail());
//        System.out.println("  Телефон: " + client.getPhone());
//
//        System.out.println("\nВведите новые данные (или нажмите Enter, чтобы оставить без изменений):");
//
//        System.out.print("Новое ФИО [" + client.getFullName() + "]: ");
//        String newFullName = scanner.nextLine();
//        if (newFullName.isEmpty()) {
//            newFullName = client.getFullName();
//        }
//
//        System.out.print("Новый email [" + client.getEmail() + "]: ");
//        String newEmail = scanner.nextLine();
//        if (newEmail.isEmpty()) {
//            newEmail = client.getEmail();
//        } else {
//            // Проверяем, не занят ли новый email (если он изменился)
//            if (!newEmail.equals(client.getEmail()) && clientRepository.emailExists(newEmail)) {
//                System.out.println("❌ Ошибка: Email " + newEmail + " уже используется!");
//                return false;
//            }
//        }
//
//        System.out.print("Новый телефон [" + client.getPhone() + "]: ");
//        String newPhone = scanner.nextLine();
//        if (newPhone.isEmpty()) {
//            newPhone = client.getPhone();
//        } else {
//            // Проверяем, не занят ли новый телефон (если он изменился)
//            if (!newPhone.equals(client.getPhone()) && clientRepository.phoneExists(newPhone)) {
//                System.out.println("❌ Ошибка: Телефон " + newPhone + " уже используется!");
//                return false;
//            }
//        }
//
//        // Обновляем данные
//        client.setFullName(newFullName);
//        client.setEmail(newEmail);
//        client.setPhone(newPhone);
//
//        System.out.println("\n✅ Клиент успешно обновлен!");
//        return true;
//    }
//
//    // ❌ УДАЛЕНИЕ КЛИЕНТА
//    public boolean deleteClient() {
//        System.out.println("\n❌ УДАЛЕНИЕ КЛИЕНТА");
//        System.out.println("──────────────────");
//
//        printAllClients();
//
//        System.out.print("Введите ID клиента для удаления: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        Optional<Client> clientOptional = clientRepository.findById(id);
//        if (clientOptional.isEmpty()) {
//            System.out.println("❌ Клиент с ID " + id + " не найден!");
//            return false;
//        }
//
//        Client client = clientOptional.get();
//        System.out.print("Удалить клиента " + client.getFullName() + "? (да/нет): ");
//        String confirm = scanner.nextLine();
//
//        if (confirm.equalsIgnoreCase("да")) {
//            boolean deleted = clientRepository.delete(id);
//            if (deleted) {
//                System.out.println("✅ Клиент " + client.getFullName() + " удален!");
//            }
//            return deleted;
//        } else {
//            System.out.println("❌ Удаление отменено");
//            return false;
//        }
//    }
//
//    // 🔍 ПОИСК ПО ID
//    public void findClientById() {
//        System.out.println("\n🔍 ПОИСК КЛИЕНТА ПО ID");
//        System.out.println("──────────────────────");
//
//        System.out.print("Введите ID клиента: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//
//        Optional<Client> clientOptional = clientRepository.findById(id);
//
//        if (clientOptional.isEmpty()) {
//            System.out.println("❌ Клиент с ID " + id + " не найден!");
//            return;
//        }
//
//        Client client = clientOptional.get();
//        printClientDetails(client);
//    }
//
//    // 🔍 ПОИСК ПО EMAIL
//    public void findClientByEmail() {
//        System.out.println("\n🔍 ПОИСК КЛИЕНТА ПО EMAIL");
//        System.out.println("──────────────────────────");
//
//        System.out.print("Введите email: ");
//        String email = scanner.nextLine();
//
//        Optional<Client> clientOptional = clientRepository.findByEmail(email);
//
//        if (clientOptional.isEmpty()) {
//            System.out.println("❌ Клиент с email " + email + " не найден!");
//            return;
//        }
//
//        Client client = clientOptional.get();
//        printClientDetails(client);
//    }
//
//    // 🔍 ПОИСК ПО ТЕЛЕФОНУ
//    public void findClientByPhone() {
//        System.out.println("\n🔍 ПОИСК КЛИЕНТА ПО ТЕЛЕФОНУ");
//        System.out.println("─────────────────────────────");
//
//        System.out.print("Введите телефон: ");
//        String phone = scanner.nextLine();
//
//        Optional<Client> clientOptional = clientRepository.findByPhone(phone);
//
//        if (clientOptional.isEmpty()) {
//            System.out.println("❌ Клиент с телефоном " + phone + " не найден!");
//            return;
//        }
//
//        Client client = clientOptional.get();
//        printClientDetails(client);
//    }
//
//    // 🔍 ПОИСК ПО ИМЕНИ
//    public void findClientsByName() {
//        System.out.println("\n🔍 ПОИСК КЛИЕНТОВ ПО ИМЕНИ");
//        System.out.println("───────────────────────────");
//
//        System.out.print("Введите имя для поиска: ");
//        String name = scanner.nextLine();
//
//        Optional<Client> foundClients = clientRepository.findByFullName(name);
//
//        if (foundClients.isEmpty()) {
//            System.out.println("❌ Клиенты с именем '" + name + "' не найдены!");
//            return;
//        }
//
//        System.out.println("\nНайдено клиентов: " + foundClients.size());
//        for (Client client : foundClients) {
//            printClientDetails(client);
//        }
//    }
//
//    // 📝 ДЕТАЛИ КЛИЕНТА
//    private void printClientDetails(Client client) {
//        System.out.println("\n┌─────────── ДЕТАЛИ КЛИЕНТА ───────────┐");
//        System.out.printf("│ ID:           %-23d │%n", client.getId());
//        System.out.printf("│ ФИО:          %-23s │%n", client.getFullName());
//        System.out.printf("│ Email:        %-23s │%n", client.getEmail());
//        System.out.printf("│ Телефон:      %-23s │%n", client.getPhone());
//        System.out.println("└────────────────────────────────────────┘");
//    }
//
//    // 📊 СТАТИСТИКА
//    public void printClientStatistics() {
//        List<Client> clients = clientRepository.findAll();
//
//        System.out.println("\n📊 СТАТИСТИКА ПО КЛИЕНТАМ");
//        System.out.println("─────────────────────────");
//        System.out.println("Всего клиентов: " + clients.size());
//
//        // Считаем клиентов с email на разных доменах
//        long gmailCount = clients.stream()
//                .filter(c -> c.getEmail().toLowerCase().contains("@gmail.com"))
//                .count();
//
//        long yandexCount = clients.stream()
//                .filter(c -> c.getEmail().toLowerCase().contains("@yandex.ru"))
//                .count();
//
//        long mailCount = clients.stream()
//                .filter(c -> c.getEmail().toLowerCase().contains("@mail.ru"))
//                .count();
//
//        System.out.println("Gmail:      " + gmailCount);
//        System.out.println("Yandex:     " + yandexCount);
//        System.out.println("Mail.ru:    " + mailCount);
//        System.out.println("Другие:     " + (clients.size() - gmailCount - yandexCount - mailCount));
//    }
//
//    // Вспомогательный метод для обрезания длинных строк
//    private String truncate(String str, int length) {
//        if (str == null) return "";
//        if (str.length() <= length) return str;
//        return str.substring(0, length - 3) + "...";
//    }
}}