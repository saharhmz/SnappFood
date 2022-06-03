package model;

import java.util.regex.Pattern;

public class Regex {

    public boolean emailRegex(String email){
        return Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                        "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]" +
                        "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
                        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                ,email);
    }
    public boolean passwordRegex(String password){
        return (password.length()>= 8 && (Pattern.matches("[0-9a-zA-Z]+",password)) );
    }

    public boolean nameRegex(String name){
        return (Pattern.matches("^[a-zA-Z]+[a-zA-Z ]+", name));
    }

    public boolean phoneNumberRegex(String phoneNumber){
        // bayad avale shomare mobail 09 bashad
        return (Pattern.matches("09([0-9]{9})",phoneNumber));
    }
    public boolean addressRegex(String address){
        return (Pattern.matches("[a-zA-Z0-9-]+" , address));
    }

    public boolean priceRegex(String price){
        return (Pattern.matches("[0-9]+",price));
    }
}
