package de.team38.semesterprojekt.simulation;

import de.team38.semesterprojekt.garage.Garage;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


public class Simulation {
    SimulationTask task = new SimulationTask();
    ScheduledExecutorService timer = null;

    public void startSimulation(){
        stopSimulation();
        timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void stopSimulation(){
        if(timer != null) {
            timer.shutdown();
            timer = null;
        }
    }


}
