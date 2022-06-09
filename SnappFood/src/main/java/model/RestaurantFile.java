package model;

import java.io.*;
import java.util.ArrayList;

public class RestaurantFile {

    private File file;
    private int restaurantId=0;

    public void addRestaurant(Restaurant restaurant ){
        file = new File("restaurant.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file , true))){
            writer.newLine();
            restaurantId = getRestaurantId();
            writer.write(String.valueOf(restaurantId));
            writer.write("-");
            writer.write(restaurant.getName()+"-");
            writer.write(restaurant.getAddress()+"-");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // id-restaurantName-address-categoryName_item/price_...-....
    public void addCategory(FoodCategory foodCategory){
        file = new File("restaurant.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file , true))){
            writer.write(foodCategory.getName());
            for (int i=0 ; i < foodCategory.getItems().size() ; i++){
                if(i == foodCategory.getItems().size()-1){
                    writer.write(","+foodCategory.getItems().get(i).getItemName()+"/"+
                            foodCategory.getItems().get(i).getPrice()+"_");
                }
                else
                    writer.write(","+foodCategory.getItems().get(i).getItemName()+"/"+
                            foodCategory.getItems().get(i).getPrice());
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public int getRestaurantId(){
        int size = getFileSize();
        int id =0 ;
        for(int i=0 ; i < size ; i++){
            id++;
        }
        return id+1;
    }

    public boolean isExist(String address) {
        int size = getFileSize();
        file = new File("restaurant.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for (int i=0 ; i < size ; i++){
                String[] lines= reader.readLine().split("-");
                if(lines[2].contains(address)){
                    return true;
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        ArrayList<Restaurant> cafes= new ArrayList<>();
        int size = getFileSize();
        file = new File("restaurant.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                String[] line = reader.readLine().split("-");
                Restaurant restaurant = new Restaurant(line[1],line[2] , null);
                restaurant.setId(Integer.parseInt(line[0]));
                String[] categories = line[3].split("_");
                ArrayList<FoodCategory> foodCategories = new ArrayList<>();
                for (int j=0 ; j < categories.length ; j++ ){
                    String[] items = categories[j].split(",");
                    FoodCategory foodCategory = new FoodCategory(items[0],restaurant);
                    ArrayList<Food> foods = new ArrayList<>();
                    for (int k=1 ; k < items.length ; k++){
                        String[] food=  items[k].split("/");
                        foods.add(new Food(food[0],food[1] , items[0]));
                    }
                    foodCategory.setItems(foods);
                    foodCategories.add(foodCategory);

                }
                restaurant.setFoodCategories(foodCategories);
                cafes.add(restaurant);
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return cafes;
    }

    public boolean isExist(String name , String address){
        ArrayList<Restaurant> restaurants = getAllRestaurants();
        for (int i=0 ; i< restaurants.size() ; i++){
            if(name.equals(restaurants.get(i).getName()) && address.equals(restaurants.get(i).getAddress())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Restaurant> getAllRestaurants(String address){
        ArrayList<Restaurant> allRestaurants = getAllRestaurants();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (int i = 0; i < allRestaurants.size() ; i++){
            if(allRestaurants.get(i).getAddress().contains(address)){
                restaurants.add(allRestaurants.get(i));
            }
        }
        return restaurants;
    }

    public int getFileSize(){
        int size =0;
        file = new File("restaurant.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            while (reader.readLine() != null){
                size++;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return size;
    }
}
