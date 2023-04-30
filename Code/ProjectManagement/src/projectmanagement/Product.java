package projectmanagement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ACER
 */
public class Product {

    private String ProductID;
    private String ProductName;
    private long price;
    private long Quantity;
    private String Status;

    public Product() {
    }

    public Product(String ProductID, String ProductName, long price, long Quantity, String Status) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.price = price;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return ProductID + " - " + ProductName + " - " + price + " - " + Quantity + " - " + Status;
    }

}
