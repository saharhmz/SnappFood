package model;

public class Order {

    private int id;
    private String customerUsername;
    private String itemName;
    private String price;
    private String status;
    private String address;

    public Order(int id, String customerUsername, String itemName, String price , String status) {
        this.id = id;
        this.customerUsername = customerUsername;
        this.itemName = itemName;
        this.price = price;
        this.status=status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
