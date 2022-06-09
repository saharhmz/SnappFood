package model;

import java.util.ArrayList;

public class FoodCategory {

    private String name;
    private int id;
    private ArrayList<Food> items;
    private Place place;

    public FoodCategory(String name , Place place) {
        this.name = name;
        this.place= place;
        items= new ArrayList<>();
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getItems() {
        return items;
    }

    public void setItems(ArrayList<Food> items) {
        this.items = items;
    }
    public void addItem(Food item){items.add(item);}
}
