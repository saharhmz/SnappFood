package model;

import java.io.*;

public class DeliveryFile {
    private File file;

    public int getFileSize(){
        // tedad user hara barmigardanad
        file = new File("delivery.txt");
        int size=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            while (reader.readLine() != null){
                size++;
                reader.readLine();
                reader.readLine();
                reader.readLine();
                reader.readLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return size;
    }

    public void addDelivery(Delivery delivery){
        file= new File("delivery.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            writer.newLine();
            writer.write(delivery.getName());
            writer.newLine();
            writer.write(delivery.getFamily());
            writer.newLine();
            writer.write(delivery.getUsername());
            writer.newLine();
            writer.write(delivery.getPassword());
            writer.newLine();
            writer.write(delivery.getPhoneNumber());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // name - family - username - password- phone number
    public boolean emailNotExist(String emailAddress){
        // tekrari naboodan email ra check mikonad
        int size= getFileSize();
        file= new File("delivery.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                reader.readLine();
                reader.readLine();
                if(emailAddress.equals(reader.readLine())){
                    return false;
                }
                reader.readLine();
                reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean isExist(String username){
        int size = getFileSize();
        file= new File("delivery.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                reader.readLine();
                reader.readLine();
                if(username.equals(reader.readLine())){
                    return true;
                }
                reader.readLine();
                reader.readLine();
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Delivery getDelivery(String username){
        int size = getFileSize();
        String name , family , password, phoneNumber;
        file= new File("delivery.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                name=reader.readLine();
                family=reader.readLine();
                if(username.equals(reader.readLine())){
                    password=reader.readLine();
                    phoneNumber=reader.readLine();
                    return (new Delivery(name,family,username,password,phoneNumber));
                }
                else {
                    reader.readLine();
                    reader.readLine();
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
