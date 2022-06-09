package model;

import java.io.*;
import java.util.ArrayList;

public class OrderTrackingFile {

    private File file;
    // id-customerUsername-address-itemName-status
    public void addOrder(Order order , String address){
        int id = getId();
        file = new File("orderTracking.txt");
        try (BufferedWriter writer = new BufferedWriter( new FileWriter(file,true))){
            writer.newLine();
            writer.write(String.valueOf(id));
            writer.write("-"+order.getCustomerUsername()+"-"+address+"-"+order.getItemName()+"-"+"NotDelivered");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
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
        file = new File("orderTracking.txt");
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

    public ArrayList<Order> getAllOrders(){
        int size = getFileSize();
        ArrayList<Order> allOrders= new ArrayList<>();
        file = new File("orderTracking.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                String[] line = reader.readLine().split("-");
                Order order =new Order(Integer.parseInt(line[0]),line[1],line[3] ,"0", line[4]);
                order.setAddress(line[2]);
                allOrders.add(order);
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return allOrders;
    }

    public ArrayList<Order> getAllOrders(Customer customer){
        ArrayList<Order> allOrders = getAllOrders();
        ArrayList<Order> orders = new ArrayList<>();
        for(int i=0 ; i < allOrders.size() ; i++){
            if(allOrders.get(i).getCustomerUsername().equals(customer.getUsername())){
                orders.add(allOrders.get(i));
            }
        }

        return orders;
    }

    public void deliverOrder(Order selectedOrder){
        ArrayList<Order> allOrders = getAllOrders();
        ArrayList<Order> editedOrder = new ArrayList<>();
        for(int i=0 ; i < allOrders.size() ; i++){
            if(allOrders.get(i).getCustomerUsername().equals(selectedOrder.getCustomerUsername()) && allOrders.get(i).
                    getItemName().equals(selectedOrder.getItemName()) && allOrders.get(i).getId() == selectedOrder.getId()){
                Order order = new Order(allOrders.get(i).getId() ,allOrders.get(i).getCustomerUsername() ,
                        allOrders.get(i).getItemName() ,allOrders.get(i).getPrice() ,"Delivered");
                editedOrder.add(order);
            }
            else {
                editedOrder.add(allOrders.get(i));
            }
        }
        writeInFileAfterEditing(editedOrder);
    }

    public void writeInFileAfterEditing(ArrayList<Order> editedOrders){
        file= new File("orderTracking.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for (int i=0 ; i < editedOrders.size() ; i++){
                writer.newLine();
                writer.write(String.valueOf(editedOrders.get(i).getId()));
                writer.write("-"+editedOrders.get(i).getCustomerUsername()+"-"+editedOrders.get(i).getAddress()+"-"+
                        editedOrders.get(i).getItemName()+"-"+ editedOrders.get(i).getStatus());
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
