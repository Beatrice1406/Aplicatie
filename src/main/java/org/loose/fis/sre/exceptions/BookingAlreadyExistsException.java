package org.loose.fis.sre.exceptions;

public class BookingAlreadyExistsException extends Exception {

    private String day;
    private String hour;
    private String month;
    private String year;
    private String agent_book;

    public BookingAlreadyExistsException(String day, String month, String year, String hour, String agent_book) {
        super(String.format("A booking for %s %s %s at %s with the agent %s already exists!", day,month,year,hour,agent_book));
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.agent_book = agent_book;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHour() {
        return hour;
    }

    public String getAgent_book() {
        return agent_book;
    }
}
