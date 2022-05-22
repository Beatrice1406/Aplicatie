package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class House {
    @Id
    private String Address;
    private String Size;
    private String Rooms;
    private String Baths;
    private String Floors;
    private String Special;

    public House(String address, String size, String rooms, String baths, String floors, String special) {
        Address = address;
        Size = size;
        Rooms = rooms;
        Baths = baths;
        Floors = floors;
        Special = special;
    }

    public House() {
    }

    public String getAddress() {
        return Address;
    }

    public String getSize() {
        return Size;
    }

    public String getRooms() {
        return Rooms;
    }

    public String getBaths() {
        return Baths;
    }

    public String getFloors() {
        return Floors;
    }

    public String getSpecial() {
        return Special;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public void setBaths(String baths) {
        Baths = baths;
    }

    public void setFloors(String floors) {
        Floors = floors;
    }

    public void setSpecial(String special) {
        Special = special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(Address, house.Address) && Objects.equals(Size, house.Size) && Objects.equals(Rooms, house.Rooms) && Objects.equals(Baths, house.Baths) && Objects.equals(Floors, house.Floors) && Objects.equals(Special, house.Special);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Address, Size, Rooms, Baths, Floors, Special);
    }

    @Override
    public String toString() {
        return
                "Address=" + Address  +
                ", Size= " + Size +
                ", Rooms= " + Rooms +
                ", Baths= " + Baths +
                ", Floors= " + Floors +
                ", Special= " + Special;
    }
}
