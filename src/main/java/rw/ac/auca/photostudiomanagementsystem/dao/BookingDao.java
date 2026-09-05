package rw.ac.auca.photostudiomanagementsystem.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rw.ac.auca.photostudiomanagementsystem.model.Booking;
import rw.ac.auca.photostudiomanagementsystem.util.HibernateUtil;

import java.util.List;

public class BookingDao {

    HibernateUtil hibernateUtil = new HibernateUtil();

    // CREATE
    public Booking saveBooking(Booking theBooking){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.save(theBooking);
        tr.commit();
        ss.close();
        return theBooking;
    }

    // READ all
    public List<Booking> findAllBookings(){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        List<Booking> bookings = ss.createQuery("SELECT b FROM Booking b").list();
        ss.close();
        return bookings;
    }

// READ one, needed so an edit page can load existing data
public Booking findBookingById(String bookingId){
    Session ss = hibernateUtil.getSessionFactory().openSession();
    Booking booking = ss.get(Booking.class, bookingId);
    ss.close();
    return booking;
}

    // UPDATE
    public Booking updateBooking(Booking theBooking){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.update(theBooking);
        tr.commit();
        ss.close();
        return theBooking;
    }
    // DELETE
    public void deleteBooking(String bookingId){
        Session ss = hibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        Booking booking = ss.get(Booking.class, bookingId);
        if (booking != null) {
            ss.delete(booking);
        }
        tr.commit();
        ss.close();
    }
}