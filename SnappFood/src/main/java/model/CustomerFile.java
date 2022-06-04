package model;

import java.io.*;
import java.util.ArrayList;

public class CustomerFile {

    private File file;

    public int getFileSize(){
        file = new File("customer.txt");
        int size=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            while (reader.readLine() != null){
                size++;
                reader.readLine();
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

    public void addCustomer(Customer customer){
        file= new File("customer.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            writer.newLine();
            writer.write(customer.getName());
            writer.newLine();
            writer.write(customer.getFamily());
            writer.newLine();
            writer.write(customer.getUsername());
            writer.newLine();
            writer.write(customer.getPassword());
            writer.newLine();
            writer.write(customer.getPhoneNumber());
            writer.newLine();
            writer.write("0");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // name - family - username - password- phone number
    public boolean emailNotExist(String emailAddress){
        // tekrari naboodan email ra check mikonad
        int size= getFileSize();
        file= new File("customer.txt");
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
                reader.readLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean isExist(String username){
        int size = getFileSize();
        file= new File("customer.txt");
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
                reader.readLine();
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Customer getCustomer(String username){
        int size = getFileSize();
        String name , family , password, phoneNumber;
        file= new File("customer.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                name=reader.readLine();
                family=reader.readLine();
                if(username.equals(reader.readLine())){
                    password=reader.readLine();
                    phoneNumber=reader.readLine();
                    Customer customer =new Customer(name,family,username,password,phoneNumber);
                    customer.setWallet(reader.readLine());
                    return (customer);
                }
                else {
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void increaseWallet(Customer customer ,String amount){
        int size = getFileSize();
        file = new File("customer.txt");
        int wallet=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                reader.readLine();
                reader.readLine();
                if(reader.readLine().equals(customer.getUsername())){
                    reader.readLine();
                    reader.readLine();
                    wallet = Integer.parseInt(reader.readLine());
                    wallet += Integer.parseInt(amount);
                }
                else {
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                }
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        writeInFileAfterEditing(customer,wallet);
    }

    public ArrayList<Customer> getAllCustomers(){
        int size = getFileSize();
        file = new File("customer.txt");
        ArrayList<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                Customer customer = new Customer(reader.readLine(),reader.readLine(),reader.readLine(),reader.readLine(),reader.readLine());
                customer.setWallet(reader.readLine());
                customers.add(customer);
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }


    public void writeInFileAfterEditing(Customer customer , int wallet){
        ArrayList<Customer> customers = new ArrayList<>();
        customers.addAll(getAllCustomers());
        file= new File("customer.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            for(int i=0 ; i < customers.size() ; i++){
                writer.newLine();
                writer.write(customers.get(i).getName());
                writer.newLine();
                writer.write(customers.get(i).getFamily());
                writer.newLine();
                if(customers.get(i).getUsername().equals(customer.getUsername())){
                    writer.write(customers.get(i).getUsername());
                    writer.newLine();
                    writer.write(customers.get(i).getPassword());
                    writer.newLine();
                    writer.write(customers.get(i).getPhoneNumber());
                    writer.newLine();
                    writer.write(String.valueOf(wallet));
                }
                else {
                    writer.write(customers.get(i).getUsername());
                    writer.newLine();
                    writer.write(customers.get(i).getPassword());
                    writer.newLine();
                    writer.write(customers.get(i).getPhoneNumber());
                    writer.newLine();
                    writer.write(customers.get(i).getWallet());
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
