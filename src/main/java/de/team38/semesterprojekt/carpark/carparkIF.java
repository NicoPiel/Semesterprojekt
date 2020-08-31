package de.team38.semesterprojekt.carpark;

import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpace;
import de.team38.semesterprojekt.carpark.ticket.Ticket;

public interface carparkIF {
    boolean open();
    boolean close();
    boolean isCarparkOpen();

    boolean drawTicket(Category category);
    boolean payTicket(Ticket ticket);
}
