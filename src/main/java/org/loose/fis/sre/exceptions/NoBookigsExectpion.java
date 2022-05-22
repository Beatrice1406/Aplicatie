package org.loose.fis.sre.exceptions;

public class NoBookigsExectpion extends Exception {

    private String Name;

    public NoBookigsExectpion(String Name) {
        super(String.format("No bookings for  %s!", Name));
        this.Name = Name;
    }

    public String Name() {
        return Name;
    }
}

