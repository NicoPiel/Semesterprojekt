package de.team38.semesterprojekt.garage;

import de.team38.semesterprojekt.garage.vehicles.Car;
import de.team38.semesterprojekt.garage.vehicles.CarCategory;
import de.team38.semesterprojekt.simulation.Simulation;

import java.util.*;
import java.util.stream.Collectors;

public class Garage {
    private List<Car> cars = new ArrayList<Car>();
    private HashMap<CarCategory, Integer> capacities = new HashMap<CarCategory, Integer>();
    private double pricePerHour = 0;
    private Simulation simulation;

    private static Garage instance;

    public synchronized static Garage getInstance(){
        return instance != null ? instance : new Garage();
    }

    private Garage(){
        instance = this;
        capacities.put(CarCategory.DEFAULT, 100);
        capacities.put(CarCategory.DISABLED, 10);
        capacities.put(CarCategory.WOMAN, 10);
        pricePerHour = 2;
        simulation = new Simulation();
    }

    /**
     * Returns the total capacity of this Garage, including all categories
     * @return the capacity
     */
    public synchronized int getTotalCapacity(){
        return capacities.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Returns the total amount of cars currently inside the garage
     * @return the amount of cars
     */
    public synchronized int getTotalOccupation(){
        return cars.size();
    }

    /**
     * Returns the price per hour of parking
     * @return price per hour
     */
    public synchronized double getPricePerHour(){
        return pricePerHour;
    }

    /**
     * Returns the capacity of a given category
     * @param cat the category
     * @return the capacity
     */
    public synchronized int getCapacity(CarCategory cat){
        return capacities.containsKey(cat) ? capacities.get(cat) : 0;
    }

    /**
     * Returns the amount of cars of a given category
     * @param cat the category
     * @return the amount of cars
     */
    public synchronized int getOccupation(CarCategory cat){
        return cars.stream().filter(car -> car.getCategory() == cat).collect(Collectors.toList()).size();
    }

    /**
     * Checks if a given category is full
     * @param cat the category
     * @return true, if the category is full
     */
    public synchronized boolean isFull(CarCategory cat){
        return getCapacity(cat) <= getOccupation(cat);
    }

    /**
     * Checks if the garage is full
     * @return true, if the garage is full
     */
    public synchronized boolean isFull(){
        return getTotalCapacity() <= getTotalOccupation();
    }

    /**
     * Tries to add a car to the garage
     * @param car the car
     * @return true, if the car was added successfully, false, if the car's category is full
     */
    public synchronized boolean addCar(Car car){
        if(!isFull(car.getCategory())){
            cars.add(car);
            return true;
        }
        return false;
    }

    /**
     * Removes a car from the garage
     * @param car
     */
    public synchronized void removeCar(Car car){
        cars.remove(car);
    }

    /**
     * Tries to find a car with a given id in the garage
     * @param id the id to search for
     * @return an Optional containing the car, if present
     */
    public synchronized Optional<Car> findById(int id){
        List<Car> results = cars.stream().filter(car -> car.getId() == id).limit(1).collect(Collectors.toList());
        return Optional.ofNullable(results.size() >= 1 ? results.get(0) : null);
    }

    /**
     * Returns a copy of the list of all cars in the garage
     * @return the List of cars
     */
    public synchronized List<Car> getCars(){
        return new ArrayList<>(cars);
    }

    public Simulation getSimulation() {
        return simulation;
    }
}
