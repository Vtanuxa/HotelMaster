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

    public Room addRoom() {
        System.out.println("\n ДОБАВЛЕНИЕ НОВОГО НОМЕРА");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пожалуйста номер комнаты");
        int inputRoomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите пожалуйста тип комнаты");
        String inputType = scanner.nextLine();
        System.out.println("Введите пожалуйста стоимость суток");
        Double inputPricePerNight = scanner.nextDouble();
        scanner.nextLine();

        Optional<Room> existingRoom = roomRepository.findByRoomNumber(inputRoomNumber);
        if (existingRoom.isPresent()) {
            System.out.println("Ошибка: Комната с номером " + inputRoomNumber + " уже существует!");
            return null;
        }
        Room room = new Room(inputRoomNumber, inputType, inputPricePerNight);
        room.setStatus("Свободен");
        Room newRoom = roomRepository.save(room);
        System.out.println("Комната успешно добавлена! ID: " + newRoom.getId());
        return newRoom;
    }

    public void AllRooms() {
        List<Room> rooms = roomRepository.findAll();

        if (rooms.isEmpty()) {
            System.out.println("Список номеров пуст");
            return;
        }

        System.out.println("\nID | Номер | Тип | Цена | Статус");
        System.out.println("----------------------------------------");

        for (Room room : rooms) {
            System.out.printf("%d | %s | %s | %.2f ₽ | %s%n", room.getId(), room.getRoomNumber(), room.getType(), room.getPricePerNight(), room.getStatus());
        }

        System.out.println("----------------------------------------");
        System.out.println("Всего номеров: " + rooms.size());
    }

    public boolean updateRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nРЕДАКТИРОВАНИЕ НОМЕРА");
        System.out.println("───────────────────────");

        AllRooms();

        System.out.print("\nВведите ID номера для редактирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {
            System.out.println("\nКомната с ID " + id + " не найдена!");
            return false;
        }

        Room room = roomOptional.get();
        System.out.println(room);
        System.out.println("\nВведите новые данные");

        System.out.print("Новый номер комнаты с ID = [" + room.getId() + "]: ");
        String newRoomNumber = scanner.nextLine();
        if (!newRoomNumber.isEmpty()) {
            room.setRoomNumber(Integer.parseInt(newRoomNumber));
        }

        System.out.println("Новый тип комнаты c ID = [" + room.getId() + "]: ");
        String newType = scanner.nextLine();
        if (!newType.isEmpty()) {
            room.setType(newType);
        }

        System.out.println("Новый статус комнаты c ID = [" + room.getId() + "]: ");
        String newStatus = scanner.nextLine();
        if (!newStatus.isEmpty()) {
            room.setStatus(newStatus);
        }

        System.out.println("Новый прайс комнаты c ID = [" + room.getId() + "]: ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            room.setPricePerNight(Double.parseDouble(newPrice));
        }

        if (roomRepository.update(room)) {
            System.out.println("Комната обновлена!");
            System.out.println(room);
            return true;
        } else {
            System.out.println("Ошибка обновления!");
            return false;
        }
    }

    public boolean deleteRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n УДАЛЕНИЕ НОМЕРА");
        System.out.println("─────────────────");

        AllRooms();

        System.out.print("\n Введите ID номера для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            System.out.println("\n Комната с ID = " + id + " не найдена!");
            return false;
        }

        Room room = roomOptional.get();
        System.out.println(room);
        System.out.println("\n Удалить комнату с ID = " + room.getId() + "?" + " Да/Нет");
        String input = scanner.nextLine();
        if (!input.equals("да")) {
            System.out.println("\n Удаление комнаты с ID = " + room.getId() + " отменено");
            return false;
        } else {
            roomRepository.delete(id);
            System.out.print("\n Удаление комнаты c ID = " + room.getId() + " ...");
            System.out.println();
        }

        if (roomRepository.findById(id).isPresent()) {
            System.out.println("Ошибка удаления");
            return false;
        }
        System.out.println("\n Комната с ID =" + room.getId() + " удалена!");
        return false;
    }


    public void findRoomById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ПОИСК НОМЕРА ПО ID");
        System.out.println("─────────────────────");

        System.out.print("Введите ID номера: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> findRoom = roomRepository.findById(id);

        if (findRoom.isEmpty()) {
            System.out.println("❌ Комната с ID " + id + " не найдена!");
            return;
        }

        Room room = findRoom.get();
        System.out.println(room);

    }

    public void findRoomByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ПОИСК НОМЕРА ПО НОМЕРУ");
        System.out.println("─────────────────────");

        System.out.print("Введите номер комнаты: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        Optional<Room> findRoom = roomRepository.findByRoomNumber(number);

        if (findRoom.isEmpty()) {
            System.out.println("❌ Комната с таким номером " + number + " не найдена!");
            return;
        }

        Room room = findRoom.get();
        System.out.println(room);

    }
}


