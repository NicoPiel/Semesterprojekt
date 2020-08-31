package de.team38.semesterprojekt.carpark.parkingspace;

import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.ticket.Ticket;

public interface ParkingSpaceIF {
    Ticket getOccupant();
    Category getCategory();
    int getParkingSpaceNumber();

    boolean setOccupant(Ticket ticket);
    boolean removeOccupant();
    boolean setCategory(Category category);
    boolean setParkingSpaceNumber(int parkingSpaceNumber);
}
