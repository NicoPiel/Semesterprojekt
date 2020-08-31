package de.team38.semesterprojekt.carpark.ticket;

import de.team38.semesterprojekt.carpark.category.Category;

public abstract class Ticket implements TicketIF {
    String ticketNumber;
    Category category;

    @Override
    public String getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public boolean setTicketNumber(String ticketNumber) {
        if(ticketNumber == null){
            return false;
        }
        //TODO Already in use
        this.ticketNumber = ticketNumber;
        return true;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public boolean setTicketCategory(Category category) {
        if(category == null){
            return false;
        }
        this.category = category;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ticket){
            Ticket other = (Ticket) obj;
            return other.getTicketNumber().equals(this.getTicketNumber());
        }
        return false;
    }
}
