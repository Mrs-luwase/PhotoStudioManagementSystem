package rw.ac.auca.photostudiomanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDate;

@Entity
public class Booking extends Audit {

    //My columns fields
    @Id
    private String bookingId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "status", nullable = false)
    private String status;

    //Functions to access these fields

    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}