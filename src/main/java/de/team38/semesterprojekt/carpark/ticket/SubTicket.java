package de.team38.semesterprojekt.carpark.ticket;

import de.team38.semesterprojekt.carpark.category.Category;

public class SubTicket extends Ticket{
    String customerID;
    String validUntil;

    public SubTicket(String ticketNumber, Category category, String validUntil, String customerID){
        super.ticketNumber = ticketNumber;
        super.category = category;
        this.customerID = customerID;
        this.validUntil = validUntil;
    }

    public boolean setCustomerID(String customerID){
        if(customerID == null){
            return false;
        }
        //TODO is ID known?
        this.customerID = customerID;
        return true;
    }
    public String getCustomerID(){
        return customerID;
    }

    public boolean renew(String validUntil){
        if(validUntil == null){
            return false;
        }
        //TODO is ID known?
        this.validUntil = validUntil;
        return true;
    }
    public String getValidUntil(){
        return validUntil;
    }

}
