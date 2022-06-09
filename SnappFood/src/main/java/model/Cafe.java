package model;

import java.util.ArrayList;

public class Cafe  extends Place{

    private int id;
    private ArrayList<FoodCategory> foodCategories;

    public Cafe(String name, String address , ArrayList<FoodCategory> foodCategories) {
        super(name, address, foodCategories);
    }

    public ArrayList<FoodCategory> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(ArrayList<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
}
