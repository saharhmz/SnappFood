package model;

import java.io.*;
import java.util.ArrayList;

public class CartCustomerFile {

    private File file;

    public void addOrder(Food food , Customer customer){
        int id = getFileSize();
        file = new File("cartCustomer.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){

            writer.newLine();
            writer.write(String.valueOf(id));
            writer.write("-"+customer.getUsername()+"-"+food.getItemName()+"-"+food.getPrice());

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // id-username-itemName-price

    public ArrayList<Order> getAllOrders(){
        int size = getFileSize();
        ArrayList<Order> orders = new ArrayList<>();
        file= new File("cartCustomer.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                String[] line = reader.readLine().split("-");
                orders.add(new Order(Integer.parseInt(line[0]),line[1],line[2],line[3] ,"NotDelivered"));
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> getCustomerOrders(Customer customer){
        ArrayList<Order> allOrders= getAllOrders();
        ArrayList<Order> orders= new ArrayList<>();
        for(int i=0 ; i < allOrders.size() ; i++){
            if(allOrders.get(i).getCustomerUsername().equals(customer.getUsername())){
                orders.add(allOrders.get(i));
            }
        }

        return orders;
    }

    public int getId(){
        int size = getFileSize();
        int id =0 ;
        for(int i=0 ; i < size ; i++){
            id++;
        }
        return id+1;
    }

    public int getFileSize(){
        int size =0;
        file = new File("cartCustomer.txt");
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

    public void deleteOrder(Order order){
        ArrayList<Order> allOrders= getAllOrders();
        ArrayList<Order> ordersAfterDeleting = new ArrayList<>();
        for(int i=0 ; i < allOrders.size() ; i++){
            if(order.getId() != allOrders.get(i).getId()){
                ordersAfterDeleting.add(allOrders.get(i));
            }
        }
        writeInFileAfterEditing(ordersAfterDeleting);
    }

    public void writeInFileAfterEditing(ArrayList<Order> orders){

        file= new File("cartCustomer.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            for(int i=0 ; i < orders.size() ; i++){
                writer.newLine();
                writer.write(orders.get(i).getId()+"-"+orders.get(i).getCustomerUsername()+"-"+orders.get(i).getItemName()
                +"-"+orders.get(i).getPrice());
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
