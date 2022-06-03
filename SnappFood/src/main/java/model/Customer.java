package model;

import java.util.ArrayList;

public class Customer extends Person{

    private String discountCode;
    private String discountAmount;
    private ArrayList<String> Cart;

    public Customer(String name, String family, String username, String password, String phoneNumber) {
        super(name, family, username, password, phoneNumber);
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public ArrayList<String> getCart() {
        return Cart;
    }

    public void setCart(ArrayList<String> cart) {
        Cart = cart;
    }
}
