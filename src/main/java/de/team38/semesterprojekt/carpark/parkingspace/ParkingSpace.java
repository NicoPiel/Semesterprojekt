package de.team38.semesterprojekt.carpark.parkingspace;

import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.ticket.Ticket;

public class ParkingSpace implements ParkingSpaceIF{

    private int parkingSpaceNumber;
    private Ticket occupiedBy;
    private Category category;

    public ParkingSpace (int parkingSpaceNumber, Ticket ticket, Category category){
        this.parkingSpaceNumber =  parkingSpaceNumber;
        this.occupiedBy = ticket;
        this.category = category;
    }
    public ParkingSpace (int parkingSpaceNumber, Category category){
        this.parkingSpaceNumber =  parkingSpaceNumber;
        this.occupiedBy = null;
        this.category = category;
    }

    public boolean isFree(){
        return occupiedBy == null;
    }

    @Override
    public Ticket getOccupant() {
        return occupiedBy;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public int getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    @Override
    public boolean setOccupant(Ticket ticket) {
        if(ticket == null){
            return false;
        }
        if(ticket.getCategory()==null){
            return false;
        }
        if(!this.category.equals(ticket.getCategory())){
            return false;
        }
        occupiedBy = ticket;
        return true;
    }

    @Override
    public boolean removeOccupant() {
        if(occupiedBy == null){
            return false;
        }
        occupiedBy = null;
        return true;
    }


    @Override
    public boolean setCategory(Category category) {
        if(category == null){
            return false;
        }
        this.category = category;
        return true;
    }

    @Override
    public boolean setParkingSpaceNumber(int parkingSpaceNumber) {
        if(parkingSpaceNumber == 0){
            return false;
        }
        this.parkingSpaceNumber = parkingSpaceNumber;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParkingSpace){
            ParkingSpace other = (ParkingSpace) obj;
            if(other.getParkingSpaceNumber() == this.parkingSpaceNumber){
                return true;
            }
        }
        return false;
    }
}
