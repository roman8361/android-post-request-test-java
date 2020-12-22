package ru.kravchenko.post_request_test_java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ResponseMobileDto {

    private String firstName;

    private String secondName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date departureDate;

    private String roomNumber;

    private String metaMessage;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
