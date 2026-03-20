package model;

import java.util.Objects;

public class Room {
    private static int nextId = 1;
    private int id;
    private String roomNumber;
    private String type; // Одноместный, Двухместный, Люкс, Полулюкс
    private String status; // Свободен, Занят, На уборке
    private double pricePerNight; // Стоимость

    public Room(String roomNumber, String type, double pricePerNight) {
        this.id = nextId++;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public static void setNextId(int nextId) {
        Room.nextId = nextId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

