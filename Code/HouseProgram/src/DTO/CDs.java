/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ACER
 */
public class CDs {
    private String name;
    private String type;
    private String title;
    private long price;
    private String ID;
    private int year;

    public CDs(String ID, String name, String type, String title, long price, int year) {
        this.name = name;
        this.type = type;
        this.title = title;
        this.price = price;
        this.ID = ID;
        this.year = year;
    }

    public CDs() {
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return ID + " - " + name + " - " + type + " - " + title + " - " + price + " - " + year;
    }

}
