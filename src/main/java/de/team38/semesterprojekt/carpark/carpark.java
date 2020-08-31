package de.team38.semesterprojekt.carpark;

import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpace;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpaces;
import de.team38.semesterprojekt.carpark.ticket.Ticket;
import de.team38.semesterprojekt.garage.vehicles.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class carpark implements carparkIF {
    boolean open;

    public boolean addParkingSpace(ParkingSpace parkingSpace) {
        return ParkingSpaces.addParkingSpace(parkingSpace);
    }

    public boolean changeParkingSpaceCategory(ParkingSpace parkingSpace, Category category) {
        return false;
    }

    @Override
    public boolean open() {
        if (open)
            return false;
        else{
            open = true;
            return true;
        }
    }

    @Override
    public boolean close() {
        if (!open)
            return false;
        else{
            open = false;
            return true;
        }

    }

    @Override
    public boolean isCarparkOpen() {
        return open;
    }

    @Override
    public boolean drawTicket(Category category) {
        return false;
    }

    @Override
    public boolean payTicket(Ticket ticket) {
        return false;
    }


}
