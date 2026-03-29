import model.Client;
import model.Room;
import service.ClientService;
import service.RoomService;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static RoomService roomService = new RoomService();
    static ClientService clientService = new ClientService();

    public static void main(String[] args) {

        while (true){
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    manageRooms();
                    break;
                case 2:
                    manageClients();
                default:
                    System.out.println("Такая функция ещё не реализована");
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🏨 СИСТЕМА УПРАВЛЕНИЯ ОТЕЛЕМ      ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  1. 🛏️ Управление номерами            ║");
        System.out.println("║  2. 👤 Управление клиентами           ║");
        System.out.println("║  3. 📅 Управление бронированием       ║");
        System.out.println("║  4. 🔍 Поиск                          ║");
        System.out.println("║  0. 🚪 Выход                          ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("👉 Выберите опцию: ");
    }

    private static void manageRooms() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║        🛏️  УПРАВЛЕНИЕ НОМЕРАМИ        ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║  1. 📋 Показать все номера            ║");
            System.out.println("║  2. ➕ Добавить номер                 ║");
            System.out.println("║  3. ✏️ Редактировать номер            ║");
            System.out.println("║  4. ❌ Удалить номер                  ║");
            System.out.println("║  5. 🔍 Найти номер по ID              ║");
            System.out.println("║  6. 🔢 Найти номер по № комнаты       ║");
            System.out.println("║  0. 🔙 Назад                          ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("👉 Выберите опцию: ");

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    roomService.AllRooms();
                    break;
                case 2:
                    roomService.addRoom();
                    break;
                case 3:
                    roomService.updateRoom();
                case 4:
                    roomService.deleteRoom();
                case 5:
                    roomService.findRoomById();
                    break;
                case 6:
                    roomService.findRoomByNumber();
                case 0:
                    return;
                default:
                    System.out.println("Такая функция ещё не реализована");
            }
        }
    }

    private static void manageClients() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║        👤 УПРАВЛЕНИЕ КЛИЕНТАМИ         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. 📋 Показать всех клиентов          ║");
            System.out.println("║  2. ➕ Добавить клиента                ║");
            System.out.println("║  3. ✏️ Редактировать клиента           ║");
            System.out.println("║  4. ❌ Удалить клиента                 ║");
            System.out.println("║  5. 🔍 Найти по ID                     ║");
            System.out.println("║  6. 🔍 Найти по email                  ║");
            System.out.println("║  7. 🔍 Найти по телефону               ║");
            System.out.println("║  0. 🔙 Назад                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("👉 Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clientService.AllClients();
                    break;
                case 2:
                    clientService.addClient();
                    break;
                case 3:
                    clientService.updateClient();
                    break;
                case 4:
                    clientService.deleteClient();
                    break;
                case 5:
                    clientService.findClientById();
                    break;
                case 6:
                    clientService.findClientByEmail();
                    break;
                case 7:
                    clientService.findClientByPhone();
                    break;
                case 0:
                    System.out.println("🔙 Возврат в главное меню...");
                    return;
                default:
                    System.out.println("❌ Неверный выбор!");
            }
        }
    }
}