package ru.kravchenko.post_request_test_java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ResponseMobileDto {

    private String guestName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date departureDate;

    private String roomNumber;

    private String metaMessage;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMetaMessage() {
        return metaMessage;
    }

    public void setMetaMessage(String metaMessage) {
        this.metaMessage = metaMessage;
    }

}
