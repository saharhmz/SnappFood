package model;

import java.io.*;
import java.util.ArrayList;

public class CafeFile {
    private File file;
    private int restaurantId=0;

    public void addCafe(Cafe cafe){
        restaurantId = getCafeId();
        file = new File("cafe.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file , true))){
            writer.newLine();
            writer.write(String.valueOf(restaurantId));
            writer.write("-");
            writer.write(cafe.getName()+"-");
            writer.write(cafe.getAddress()+"-");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // id-restaurantName-address-categoryName1,item1/price,item2/price_categoryName2,item1/price,item2/price_....
    public void addCategory(FoodCategory foodCategory){
        file = new File("cafe.txt");
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

    public boolean isExist(String address) {
        int size = getFileSize();
        file = new File("cafe.txt");
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

    public ArrayList<Cafe> getAllCafes(){
        ArrayList<Cafe> cafes= new ArrayList<>();
        int size = getFileSize();
        file = new File("cafe.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                String[] line = reader.readLine().split("-");
                Cafe cafe = new Cafe(line[1],line[2] , null);
                cafe.setId(Integer.parseInt(line[0]));
                String[] categories = line[3].split("_");
                ArrayList<FoodCategory> foodCategories = new ArrayList<>();
                for (int j=0 ; j < categories.length ; j++ ){
                    String[] items = categories[j].split(",");
                    FoodCategory foodCategory = new FoodCategory(items[0],cafe);
                    ArrayList<Food> foods = new ArrayList<>();
                    for (int k=1 ; k < items.length ; k++){
                        String[] food=  items[k].split("/");
                        foods.add(new Food(food[0],food[1] , items[0]));
                    }
                    foodCategory.setItems(foods);
                    foodCategories.add(foodCategory);

                }
                cafe.setFoodCategories(foodCategories);
                cafes.add(cafe);
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return cafes;
    }

//    public boolean isExist(String name , String address){
//        ArrayList<Cafe> cafes = getAllCafes();
//        for (int i=0 ; i< cafes.size() ; i++){
//            if(name.equals(cafes.get(i).getName()) && address.equals(cafes.get(i).getAddress())){
//                return true;
//            }
//        }
//        return false;
//    }

    public ArrayList<Cafe> getAllCafes(String address){
        ArrayList<Cafe> allCafes = getAllCafes();
        ArrayList<Cafe> cafes = new ArrayList<>();
        for (int i=0 ; i < allCafes.size() ; i++){
            if(allCafes.get(i).getAddress().contains(address)){
                cafes.add(allCafes.get(i));
            }
        }
        return cafes;
    }

    public int getCafeId(){
        int size = getFileSize();
        int id =0 ;
        for(int i=0 ; i < size ; i++){
            id++;
        }
        return id+1;
    }

    public int getFileSize(){
        int size =0;
        file = new File("cafe.txt");
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
