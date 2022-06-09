package model;

import java.util.ArrayList;

public class Restaurant extends Place{

    private int id;
    private ArrayList<FoodCategory> foodCategories;
    public Restaurant(String name, String address , ArrayList<FoodCategory> foodCategories) {
        super(name, address , foodCategories);
    }

    public int getId() {
        return id;
    }
    public ArrayList<FoodCategory> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(ArrayList<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
    }

    public void setId(int id) {
        this.id = id;
    }
}
