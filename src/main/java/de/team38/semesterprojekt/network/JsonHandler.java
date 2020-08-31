package de.team38.semesterprojekt.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.team38.semesterprojekt.garage.Garage;
import de.team38.semesterprojekt.garage.vehicles.Car;
import de.team38.semesterprojekt.garage.vehicles.CarCategory;

import java.util.Optional;

public class JsonHandler {

    /**
     * Handles POST requests
     * @param request the JsonObject received by the servlet
     * @return a Json response to send back to the client
     */
    public static JsonObject handlePost(JsonObject request){
        if(request.has("action")) {
            return handleAction(request);
        }else if(request.has("data")){
            return handleRequest(request);
        }
        JsonObject response = new JsonObject();
        response.addProperty("result", "error");
        response.addProperty("message", "Unknown JSON structure");
        return response;
    }

    public static JsonObject handleAction(JsonObject request){
        String action = request.get("action").getAsString();
        String result, message;
        Garage garage = Garage.getInstance();
        int id;
        switch(action){
            case "enter":
                id = request.get("id").getAsInt();
                String category = request.get("category").getAsString();
                CarCategory cat = CarCategory.DEFAULT;
                try {
                    cat = CarCategory.valueOf(category);
                }catch (IllegalArgumentException e){
                    result = "error";
                    message = "Category does not exist";
                    break;
                }
                if(garage.findById(id).isPresent()){
                    result = "error";
                    message = "Id already exists";
                    break;
                }
                if(!garage.addCar(new Car(id, cat))){
                    result = "error";
                    message = "No available spots for this category";
                    break;
                }
                result= "success";
                message = "";
                break;
            case "leave":
                id = request.get("id").getAsInt();
                Optional<Car> oCar = garage.findById(id);
                if(!oCar.isPresent()){
                    result = "error";
                    message = "No car found with this id";
                    break;
                }
                garage.removeCar(oCar.get());
                result = "success";
                message = "";
                break;
            case "simulation_start":
                garage.getSimulation().startSimulation();
                result = "success";
                message = "";
                break;
            case "simulation_stop":
                garage.getSimulation().stopSimulation();
                result = "success";
                message = "";
                break;
            default:
                result = "error";
                message = "Unknown action";
        }
        JsonObject response = new JsonObject();
        response.addProperty("result", result);
        response.addProperty("message", message);
        return response;
    }

    /**
     * Handles GET requests
     * @param request the JsonObject received by the servlet
     * @return a Json response to send back to the client
     */
    public static JsonObject handleRequest(JsonObject request){
        String requestedData = request.get("data").getAsString();
        String result, message;
        Garage garage = Garage.getInstance();
        JsonObject data = new JsonObject();
        switch (requestedData){
            case "meta":
                data.addProperty("price", garage.getPricePerHour());
                data.addProperty("capacity", garage.getTotalCapacity());
                data.addProperty("occupied", garage.getTotalOccupation());
                JsonArray categories = new JsonArray();
                for(CarCategory cat : CarCategory.values()){
                    JsonObject category = new JsonObject();
                    category.addProperty("identifier", cat.toString());
                    category.addProperty("capacity", garage.getCapacity(cat));
                    category.addProperty("occupied", garage.getOccupation(cat));
                    categories.add(category);
                }
                data.add("categories", categories);
                result = "success";
                message = "";
                break;
            case "cars":
                JsonArray cars = new JsonArray();
                for(Car car : garage.getCars()){
                    JsonObject carObj = new JsonObject();
                    carObj.addProperty("id", car.getId());
                    carObj.addProperty("category", car.getCategory().toString());
                    carObj.addProperty("enterTimestamp", car.getEnterTimestamp());
                    cars.add(carObj);
                }
                data.add("cars", cars);
                result = "success";
                message = "";
                break;
            default:
                result = "error";
                message = "Unknown data request";
        }
        JsonObject response = new JsonObject();
        response.addProperty("result", result);
        response.addProperty("message", message);
        response.add("data", data);
        return response;
    }

}
