package de.team38.semesterprojekt.carpark.ticket;

import de.team38.semesterprojekt.carpark.category.Category;

public class DefaultTicket extends Ticket{
    private long enterTimeStamp;
    private long exitTimeStamp;
    private boolean payed;


    public DefaultTicket(String ticketNumber, Category category, long enterTimeStamp){
        super.ticketNumber = ticketNumber;
        super.category = category;
        this.enterTimeStamp = enterTimeStamp;
        this.exitTimeStamp = 0;
        this.payed = false;
    }
    public DefaultTicket(String ticketNumber, Category category){
        super.ticketNumber = ticketNumber;
        super.category = category;
        this.enterTimeStamp = System.currentTimeMillis();
        this.exitTimeStamp = 0;
        this.payed = false;
    }

    public long getEnterTimeStamp() {
        return enterTimeStamp;
    }

    public boolean setEnterTimeStamp(long enterTimeStamp) {
        if(enterTimeStamp == 0) {
            return false;
        }
        this.enterTimeStamp = enterTimeStamp;
        return true;
    }

    public long getExitTimeStamp() {
        return exitTimeStamp;
    }

    public boolean setExitTimeStamp(long exitTimeStamp) {
        if(exitTimeStamp == 0) {
            return false;
        }
        this.exitTimeStamp = exitTimeStamp;
        return true;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
    public float getCurrentPrice(){
        long now = System.currentTimeMillis();
        long hours = (enterTimeStamp - now) / 1000 / 60 / 60;
        return hours * category.getPricePerHour();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultTicket){
            DefaultTicket other = (DefaultTicket) obj;
            return other.getTicketNumber().equals(this.getTicketNumber());
        }
        return false;
    }
}
