package rw.ac.auca.photostudiomanagementsystem.bean;

import rw.ac.auca.photostudiomanagementsystem.dao.BookingDao;
import rw.ac.auca.photostudiomanagementsystem.model.Booking;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ManagedBean
@SessionScoped
public class BookingBean {

    private BookingDao bookingDao = new BookingDao();
    private List<Booking> bookingList;
    private Booking booking = new Booking();

    @PostConstruct
    public void init() {
        bookingList = bookingDao.findAllBookings();
    }

    // list page "New" link comes here
    public String prepareNew() {
        booking = new Booking();
        booking.setBookingId(generateBookingId());
        return "bookingForm?faces-redirect=true";
    }

    private String generateBookingId() {
        int nextNumber = bookingDao.findAllBookings().size() + 1;
        return String.format("BK%03d", nextNumber);
    }

    // list page "Edit" link comes here
    public String prepareEdit(String bookingId) {
        booking = bookingDao.findBookingById(bookingId);
        return "bookingForm?faces-redirect=true";
    }

    public String saveBooking() {
        if (booking.getBookingId() == null || booking.getBookingId().trim().isEmpty()) {
            booking.setBookingId(generateBookingId());
        }
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        bookingDao.saveBooking(booking);
        bookingList = bookingDao.findAllBookings();
        return "confirmation?faces-redirect=true";
    }

    public String updateBooking() {
        booking.setUpdatedAt(LocalDateTime.now());
        bookingDao.updateBooking(booking);
        bookingList = bookingDao.findAllBookings();
        return "confirmation?faces-redirect=true";
    }

    public String deleteBooking(String bookingId) {
        bookingDao.deleteBooking(bookingId);
        bookingList = bookingDao.findAllBookings();
        return "bookingList?faces-redirect=true";
    }

    // custom validator, checks event date isn't in the past
    public void validateEventDate(FacesContext context, UIComponent component, Object value) {
        LocalDate date = (LocalDate) value;
        if (date.isBefore(LocalDate.now())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Event date cannot be in the past.", null));
        }
    }

    public List<Booking> getBookingList() { return bookingList; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
}