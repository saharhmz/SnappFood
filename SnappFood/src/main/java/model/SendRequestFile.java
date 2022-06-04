package model;

import controller.RegisterFirstPageController;
import controller.SendEmailController;

import java.io.*;

public class SendRequestFile {

    private File file;
    private RegisterFirstPageController registerFirstPageController;

    public int getFileSize(){
        file = new File("sendRequestToFriend.txt");
        int size=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            while (reader.readLine() != null){
                size++;
                reader.readLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return size;
    }

    // customer-friend
    public boolean requestIsExist(String friendUsername ){
        int size = getFileSize();
        file= new File("sendRequestToFriend.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            for(int i=0 ; i < size ; i++){
                String[] friends =reader.readLine().split("-");
                if( friendUsername.equals(friends[1])){
                    return true;
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    // customer-friend-customerCode-friendCode
    public void addRequest(String customerEmail , String friendEmail){
        registerFirstPageController=new RegisterFirstPageController();

        file = new File("sendRequestToFriend.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))){
            writer.newLine();
            String customerCode = registerFirstPageController.randomNumber();
            String friendCode =registerFirstPageController.randomNumber();
            writer.write(customerEmail+"-"+friendEmail+"-"+customerCode+"-"+friendCode);
            SendEmailController sendEmailController= new SendEmailController("you are invented to the snapp food program" +
                    "\nYour discount code is : "+friendCode,friendEmail);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sendDiscountCodeToRequestSender(String friendUsername){

        file= new File("sendRequestToFriend.txt");
        try (BufferedReader reader =  new BufferedReader(new FileReader(file))){
            reader.readLine();
            String[] line = reader.readLine().split("-");
            if(line[1].equals(friendUsername)){
                SendEmailController sendEmailController = new SendEmailController("Your discount code is: "+line[2],
                        line[0]);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
