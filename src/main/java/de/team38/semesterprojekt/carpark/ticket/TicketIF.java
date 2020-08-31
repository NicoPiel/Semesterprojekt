package de.team38.semesterprojekt.carpark.ticket;

import de.team38.semesterprojekt.carpark.category.Category;

public interface TicketIF {

    String getTicketNumber();
    boolean setTicketNumber(String ticketNumber);

    Category getCategory();
    boolean setTicketCategory(Category category);
}
