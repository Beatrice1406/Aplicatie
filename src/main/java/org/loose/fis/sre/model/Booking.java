package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Booking {


    @Id
    private String address;
    private String day;
    private String month;
    private String year;
    private String hour;
    private String agent_book;
    private String special_req;
    private String accept_booking;
    private String rejection_message;
    private String user;

    public Booking(String address,String day,String month,String year,String hour, String agent_book, String special_req,String user) {
        this.user=user;
        this.address = address;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.agent_book = agent_book;
        this.special_req = special_req;
        this.accept_booking="not responded";
        this.rejection_message=" ";
    }

    public String getUser() {
        return user;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public String getHour() {
        return hour;
    }

    public String getAgent_book() {
        return agent_book;
    }


    public String getDay() {
        return day;
    }



    public void setAccept_booking(String accept_booking) {
        this.accept_booking = accept_booking;
    }


    public void setRejection_message(String rejection_message) {
        this.rejection_message = rejection_message;
    }

    public String getMonth() {
        return month;
    }


    public String getYear() {
        return year;
    }


    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Booking booking = (Booking) object;
        return address.equals(booking.address) && day.equals(booking.day) && month.equals(booking.month) && year.equals(booking.year) && hour.equals(booking.hour) && agent_book.equals(booking.agent_book) && special_req.equals(booking.special_req) && accept_booking.equals(booking.accept_booking) && rejection_message.equals(booking.rejection_message) && user.equals(booking.user);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), address, day, month, year, hour, agent_book, special_req, accept_booking, rejection_message, user);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Booking{" +
                "address= " + address +"\n" +
                ", day=" + day  +
                ", month=" + month  +
                ", year= " + year  +
                ", hour= " + hour  + "\n" +
                ", agent_book= " + agent_book +
                ", special_req= " + special_req   + "\n" +
                ", accept_booking= " + accept_booking  +
                ", rejection_message= " + rejection_message +
                ", Name='" + user  +
                '}';
    }
}