package service;

import model.Room;
import repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepository();
    }

    public Room addRoom(String roomNumber, String type, double pricePerNight){
        if (roomRepository.findByRoomNumber(roomNumber).isPresent()){
            System.out.println("Ошибка: Комната с таким номер "+ roomNumber + " уже существует!");
            return null;
        }
        Room room = new Room(roomNumber, type, pricePerNight);
        return roomRepository.save(room);
    }

    public void printAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        if (roomRepository.findAll().isEmpty()) {
            System.out.println("📭 Список номеров пуст");
            return;
        }

        System.out.println("\n┌────┬────────────┬──────────────────┬─────────────┐");
        System.out.println("│ ID │ Номер комн.│ Тип              │ Цена за ночь│");
        System.out.println("├────┼────────────┼──────────────────┼─────────────┤");

        for (Room room : rooms) {
            System.out.printf("│ %-2d │ %-10s │ %-16s │ %11.2f ₽ │%n",
                    room.getId(),
                    room.getRoomNumber(),
                    room.getType(),
                    room.getPricePerNight());
        }

        System.out.println("└────┴────────────┴──────────────────┴─────────────┘");
        System.out.println("Всего номеров: " + rooms.size());
    }

    public boolean updateRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n✏️  РЕДАКТИРОВАНИЕ НОМЕРА");
        System.out.println("───────────────────────");

        // Сначала показываем все комнаты
        printAllRooms();

        System.out.print("Введите ID номера для редактирования: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // очищаем буфер

        // Ищем комнату по ID
        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {
            System.out.println("❌ Ошибка: Комната с ID " + id + " не найдена!");
            return false;
        }

        Room room = roomOptional.get();
        String oldRoomNumber = room.getRoomNumber();

        System.out.println("Текущие данные:");
        System.out.println("  Номер: " + oldRoomNumber);
        System.out.println("  Тип: " + room.getType());
        System.out.println("  Цена: " + room.getPricePerNight() + " ₽");

        System.out.println("\nВведите новые данные (или нажмите Enter, чтобы оставить без изменений):");

        System.out.print("Новый номер комнаты [" + oldRoomNumber + "]: ");
        String newRoomNumber = scanner.nextLine();
        if (newRoomNumber.isEmpty()) {
            newRoomNumber = oldRoomNumber; // оставляем старый номер
        }

        System.out.print("Новый тип [" + room.getType() + "]: ");
        String newType = scanner.nextLine();
        if (newType.isEmpty()) {
            newType = room.getType(); // оставляем старый тип
        }

        System.out.print("Новая цена за ночь [" + room.getPricePerNight() + "]: ");
        String priceInput = scanner.nextLine();
        double newPrice;
        if (priceInput.isEmpty()) {
            newPrice = room.getPricePerNight(); // оставляем старую цену
        } else {
            newPrice = Double.parseDouble(priceInput);
        }

        // Проверяем, не занят ли новый номер (если он меняется)
        if (!oldRoomNumber.equals(newRoomNumber)) {
            Optional<Room> existingRoom = roomRepository.findByRoomNumber(newRoomNumber);
            if (existingRoom.isPresent()) {
                System.out.println("❌ Ошибка: Комната с номером " + newRoomNumber + " уже существует!");
                return false;
            }
        }

        // Обновляем данные
        room.setRoomNumber(newRoomNumber);
        room.setType(newType);
        room.setPricePerNight(newPrice);

        System.out.println("\n✅ Комната успешно обновлена!");
        System.out.println("  ID: " + id);
        System.out.println("  Номер: " + oldRoomNumber + " → " + newRoomNumber);
        System.out.println("  Тип: " + newType);
        System.out.println("  Цена: " + newPrice + " ₽");

        return true;
    }

    public boolean deleteRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n❌ УДАЛЕНИЕ НОМЕРА");
        System.out.println("─────────────────");

        printAllRooms();

        System.out.print("Введите ID номера для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            System.out.println("❌ Комната с ID " + id + " не найдена!");
            return false;
        }

        Room room = roomOptional.get();
        System.out.print("Удалить комнату " + room.getRoomNumber() + "? (да/нет): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("да")) {
            boolean deleted = roomRepository.delete(id);
            if (deleted) {
                System.out.println("✅ Комната " + room.getRoomNumber() + " удалена!");
            }
            return deleted;
        } else {
            System.out.println("❌ Удаление отменено");
            return false;
        }
    }

    public void findRoomById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🔍 ПОИСК НОМЕРА ПО ID");
        System.out.println("─────────────────────");

        System.out.print("Введите ID номера: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {
            System.out.println("❌ Комната с ID " + id + " не найдена!");
            return;
        }

        Room room = roomOptional.get();
        printRoomDetails(room);
    }

    // 🔍 ПОИСК ПО НОМЕРУ КОМНАТЫ
    public void findRoomByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🔍 ПОИСК НОМЕРА ПО № КОМНАТЫ");
        System.out.println("────────────────────────────");

        System.out.print("Введите номер комнаты: ");
        String number = scanner.nextLine();

        Optional<Room> roomOptional = roomRepository.findByRoomNumber(number);

        if (roomOptional.isEmpty()) {
            System.out.println("❌ Комната с номером " + number + " не найдена!");
            return;
        }

        Room room = roomOptional.get();
        printRoomDetails(room);
    }

    // 📝 ВСПОМОГАТЕЛЬНЫЙ МЕТОД ДЛЯ ВЫВОДА ДЕТАЛЕЙ
    private void printRoomDetails(Room room) {
        System.out.println("\n┌─────────── ДЕТАЛИ КОМНАТЫ ───────────┐");
        System.out.printf("│ ID:                 %-15d │%n", room.getId());
        System.out.printf("│ Номер комнаты:      %-15s │%n", room.getRoomNumber());
        System.out.printf("│ Тип:                 %-15s │%n", room.getType());
        System.out.printf("│ Цена за ночь:       %-15.2f ₽ │%n", room.getPricePerNight());
        System.out.println("└────────────────────────────────────────┘");
    }
}

