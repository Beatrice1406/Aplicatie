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
    }
    public Booking()
    {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setAgent_book(String agent_book) {
        this.agent_book = agent_book;
    }

    public void setSpecial_req(String special_req) {
        this.special_req = special_req;
    }

    public String getHour() {
        return hour;
    }

    public String getAgent_book() {
        return agent_book;
    }

    public String getSpecial_req() {
        return special_req;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Booking booking = (Booking) object;
        return address.equals(booking.address) && day.equals(booking.day) && month.equals(booking.month) && year.equals(booking.year) && hour.equals(booking.hour) && agent_book.equals(booking.agent_book) && special_req.equals(booking.special_req) && user.equals(booking.user);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), address, day, month, year, hour, agent_book, special_req, user);
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
                ", Name='" + user  +
                '}';
    }
}