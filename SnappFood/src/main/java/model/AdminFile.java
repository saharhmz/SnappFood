package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminFile {

    private File file;

    public Admin getAdmin(){
        file = new File("admin.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            return (new Admin(reader.readLine(),reader.readLine(),reader.readLine(),reader.readLine(),reader.readLine()));

        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
