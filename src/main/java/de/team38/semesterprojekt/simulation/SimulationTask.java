package de.team38.semesterprojekt.simulation;

import de.team38.semesterprojekt.garage.Garage;
import de.team38.semesterprojekt.garage.vehicles.Car;
import de.team38.semesterprojekt.garage.vehicles.CarCategory;

import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class SimulationTask implements Runnable {
    Garage g = Garage.getInstance();
    Random rand = new Random();
    
    @Override
    public void run() {
        double chanceEnter = 0.5;
        double chanceLeave = (double) g.getTotalOccupation() / (double) g.getTotalCapacity();
        if(rand.nextDouble() < chanceLeave){
            exitRandomCar();
        }
        if(rand.nextDouble() < chanceEnter){
            enterRandomCar();
        }
    }

    private void enterRandomCar(){
        int id;
        do{
            id = rand.nextInt(10000);
        }while(g.findById(id).isPresent());
        int category;
        do{
            category = rand.nextInt(CarCategory.values().length);
        }while(g.isFull(CarCategory.values()[category]));
        g.addCar(new Car(id, CarCategory.values()[category]));
    }

    private void exitRandomCar(){
        List<Car> cars = Garage.getInstance().getCars();
        if(cars.size() > 0){
            Car c = cars.get(rand.nextInt(cars.size()));
            Garage.getInstance().removeCar(c);
        }
    }

}
