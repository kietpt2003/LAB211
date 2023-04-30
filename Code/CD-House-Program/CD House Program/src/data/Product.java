/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author QUANG KIET
 */
public class Product {

    private String name;
    private String type;
    private String title;
    private double unitprice;
    private String id;
    private int Publishingyear;

    public Product(String name, String type, String title, double unitprice, String id, int Publishingyear) {
        this.name = name;
        this.type = type;
        this.title = title;
        this.unitprice = unitprice;
        this.id = id;
        this.Publishingyear = Publishingyear;
    }

   
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPublishingyear() {
        return Publishingyear;
    }

    public void setPublishingyear(int Publishingyear) {
        this.Publishingyear = Publishingyear;
    }

    

    @Override
    public String toString() {
        return name + "," + type + "," + title + "," + unitprice + "," + id + "," + Publishingyear;
    }
 public void showResult(){
        System.out.printf("|%-15s|%-15s|%-20s|%-10.2f|%-10s|%-20s|\n",name,type,title,unitprice,id,Publishingyear);
    }
}
