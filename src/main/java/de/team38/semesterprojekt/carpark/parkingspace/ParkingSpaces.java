package de.team38.semesterprojekt.carpark.parkingspace;

import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.ticket.DefaultTicket;
import de.team38.semesterprojekt.carpark.ticket.Ticket;
import de.team38.semesterprojekt.garage.vehicles.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingSpaces {
    private static List<ParkingSpace> parkingSpaces= new ArrayList<ParkingSpace>();

    public static boolean addParkingSpace(ParkingSpace parkingSpace){
        if(parkingSpaces.contains(parkingSpace)) {
            return false;
        }
        parkingSpaces.add(parkingSpace);
        return true;
    }
    public static boolean removeParkingSpace(ParkingSpace parkingSpace){
        if(!parkingSpaces.contains(parkingSpace)){
            return false;
        }
        parkingSpaces.remove(parkingSpace);
        return true;
    }

    /**
     * Reset static parking spaces
     */
    public static void resetParkingSpaces(){
        parkingSpaces = new ArrayList<ParkingSpace>();
    }

    /**
     * Tries to find a car with a given id in the garage
     * @param id the id to search for
     * @return an Optional containing the car, if present
     * by Mark
     */
    public static Optional<ParkingSpace> findParkingSpaceByID(int id){
        List<ParkingSpace> results = parkingSpaces.stream().filter(space -> space.getParkingSpaceNumber() == id).limit(1).collect(Collectors.toList());
        return Optional.ofNullable(results.size() >= 1 ? results.get(0) : null);
    }

    /**
     * Returns amount of parking spaces for a particular category
     * @param category the category to search for
     * @return amount of parking spaces
     */
    public static int countSpacesByCategory(Category category){
        return (int) parkingSpaces.stream().filter(space -> space.getCategory().equals(category)).count();
    }

    /**
     * Returns amount of occupied parking spaces for a particular category
     * @param category the category to search for
     * @return amount of parking spaces
     */
    public static int countOccupiedParkingSpacesByCategory(Category category){
        return (int) parkingSpaces.stream().filter(space -> space.getCategory().equals(category) & !space.isFree()).count();
    }

    /**
     * Returns amount of free parking spaces for a particular category
     * @param category the category to search for
     * @return amount of parking spaces
     */
    public static int countFreeParkingSpacesByCategory(Category category){
        return (int) parkingSpaces.stream().filter(space -> space.getCategory().equals(category) & space.isFree()).count();
    }

    /**
     * Returns amount of all free parking spaces
     * @return amount of parking spaces
     */
    public static int countFreeParkingSpaces(){
        return (int) parkingSpaces.stream().filter(ParkingSpace::isFree).count();
    }

    /**
     * Returns amount of all occupied parking spaces
     * @return amount of parking spaces
     */
    public static int countOccupiedParkingSpaces(){
        return (int) parkingSpaces.stream().filter(space -> !space.isFree()).count();
    }

    /**
     * Returns amount of all parking spaces
     * @return amount of parking spaces
     */
    public static int countSpaces(){
        return parkingSpaces.size();
    }
    /** TODO
     * amount of parking spaces
     * @param ticket the occupant to search for
     * @return true if a parking space was found with the given occupant
     */
    /*public static boolean occupantAlreadyUsingParkSpace(Ticket ticket){
        List<ParkingSpace> results = parkingSpaces.stream().filter(space -> space.getOccupant().getTicketNumber().equals(ticket.getTicketNumber())).limit(1).collect(Collectors.toList());
        Optional<ParkingSpace> re= Optional.ofNullable(results.size() >= 1 ? results.get(0) : null);
        return re.isPresent();
    }*/

}
