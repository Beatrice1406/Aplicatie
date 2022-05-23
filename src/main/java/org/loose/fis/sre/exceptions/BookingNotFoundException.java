package org.loose.fis.sre.exceptions;

public class BookingNotFoundException extends Exception {
    private String Name,hour,day,month,year;
    public BookingNotFoundException(String Name, String hour, String day, String month, String year) {
        super(String.format("A booking for %s on %s %s %s %s does not exist!" ,Name, hour, day, month, year));
        this.Name= Name;
        this.hour=hour;
        this.day=day;
        this.month=month;
        this.year=year;

    }


}
