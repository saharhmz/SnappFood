package model;

import java.util.ArrayList;

public class Delivery extends Person{

    private ArrayList<String> Order;

    public Delivery(String name, String family, String username, String password, String phoneNumber) {
        super(name, family, username, password, phoneNumber);
    }

    public ArrayList<String> getOrder() {
        return Order;
    }

    public void setOrder(ArrayList<String> order) {
        Order = order;
    }
}
