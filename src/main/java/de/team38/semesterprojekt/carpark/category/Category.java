package de.team38.semesterprojekt.carpark.category;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.util.Locale;

public class Category implements CategoryIF{
    private String name;
    private float price;

    public Category(String name, float price) {
        this.name = name;
        this.price = price;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean setName(String name) {
        if (name==null) {
            return false;
        }
        //TODO Already in use check

        this.name = name;
        return true;
    }

    @Override
    public float getPricePerHour() {
        return price;
    }

    @Override
    public boolean setPricePerHour(float price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category){
            Category other = (Category) obj;
            if(other.getName().equals(this.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
