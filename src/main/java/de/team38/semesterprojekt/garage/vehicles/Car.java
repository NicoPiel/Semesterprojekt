package de.team38.semesterprojekt.garage.vehicles;


public class Car {
    private int id;
    private long enterTimestamp;
    private CarCategory category;

    public Car(int id, CarCategory cat){
        this.id = id;
        enterTimestamp = System.currentTimeMillis();
        category = cat;
    }

    public CarCategory getCategory(){
        return category;
    }

    public long getEnterTimestamp(){
        return enterTimestamp;
    }

    public int getId(){
        return id;
    }


}
