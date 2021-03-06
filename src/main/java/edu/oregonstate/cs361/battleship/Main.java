package edu.oregonstate.cs361.battleship;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {
        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function should return a new model
    private static String newModel() {

        GameModel model = new GameModel();
        Gson gson = new Gson();

        System.out.println(gson.toJson(model));
        return gson.toJson(model);
    }

    //This function should accept an HTTP request and deseralize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req){

        Gson gson = new Gson();

        //Populate a  BattleshipModel object using JSON data
        return gson.fromJson(req.body(),BattleshipModel.class);

    }

    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.
    private static String placeShip(Request req) {
        return null;
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {
        Gson gson = new Gson();
        GameModel model = gson.fromJson(req.body(),GameModel.class);
        Coordinate fire = new Coordinate(Integer.parseInt(req.params(":row")), Integer.parseInt(req.params(":col")));

        System.out.print(fire.Across);
        System.out.print(fire.Down);

        return gson.toJson(model);
    }

}