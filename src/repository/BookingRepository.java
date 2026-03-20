package repository;

import model.Booking;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {
    private List<Booking> bookings = new ArrayList<>();

    public Booking save(Booking booking) {
        bookings.add(booking);
        return booking;
    }

    public Optional<Booking> findById(int id) {
        return bookings.stream()
                .filter(booking -> booking.getId() == id)
                .findFirst();
    }

    public Optional<Booking> findByClientId(int Id) {
        return bookings.stream()
                .filter(booking -> booking.getId() == Id)
                .findFirst();
    }

    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    public boolean update(Booking updatedBooking) {
        Optional<Booking> existing = findById(updatedBooking.getId());
        if (existing.isPresent()) {
            Booking booking = existing.get();
            booking.setCheckInDate(updatedBooking.getCheckInDate());
            booking.setCheckOutDate(updatedBooking.getCheckOutDate());
            booking.setStatus(updatedBooking.getStatus());
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return bookings.removeIf(booking -> booking.getId() == id);
    }

    public void clear() {
        bookings.clear();
    }
}