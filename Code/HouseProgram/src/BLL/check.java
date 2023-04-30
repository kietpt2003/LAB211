/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.CDs;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class check {
    final int currentYear = 2022;
    public CDs[] arrCDs;
    
    public check() {
    }

    public check(CDs[] arrCDs) {
        this.arrCDs = arrCDs;
    }
    
    public boolean checkFreeSpace(int count) {
        boolean check = true;
        if (count == 100) {
            check = false;
        }
        return check;
    }

    public boolean checkID(String inputID) {
        boolean check = true;
        boolean checkDup = false;
        for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
            if (inputID.compareTo(arrCDs[i].getID()) == 0) {
                checkDup = true; //Check duplicated
            }
        }
        if (checkDup) {
            check = false;
            checkDup = false; //Reset checDup = false for checking again
            System.out.println("The CD ID is duplicate!");
            System.out.print("Try again: ");
        } else if (inputID.equals("")) {
            check = false;
            System.out.println("The CD ID cannot be empty!");
            System.out.print("Try again: ");
        } else if (inputID.matches("CD\\d{3}") == false) {
            check = false;
            System.out.println("The CD ID must be CDxxx format!");
            System.out.print("Try again: ");
        } else {
            check = true;
        }
        return check;
    }

    public boolean checkName(String nameInput) {
        boolean check = true;
        if (nameInput.equals("")) {
            check = false;
            System.out.println("The CD collection name cannot be empty!");
            System.out.print("Try again: ");
        } else if (nameInput.compareTo("game") == 0 || nameInput.compareTo("music") == 0 || nameInput.compareTo("movie") == 0) {
            check = true;
        } else {
            System.out.println("The CD collection name must be game/music/movie");
            System.out.print("Try again: ");
            check = false;
        }
        return check;
    }

    public boolean checkType(String typeInput) {
        boolean check = true;
        if (typeInput.equals("")) {
            check = false;
            System.out.println("The CD type cannot be empty!");
            System.out.print("Try again: ");
        } else if (typeInput.compareTo("audio") == 0 || typeInput.compareTo("video") == 0) {
            check = true;
        } else {
            System.out.println("The CD type must be audio/video");
            System.out.print("Try again: ");
            check = false;
        }
        return check;
    }

    public boolean checkTitle(String titleInput) {
        boolean check = true;
        if (titleInput.equals("")) {
            check = false;
            System.out.println("The CD title cannot be empty!");
            System.out.print("Try again: ");
        }
        return check;
    }
    
    public boolean checkLongVar(String priceInput) { //For check Wrong Input Price
        boolean check = true;
        Scanner scanner = new Scanner(priceInput);
        if (scanner.hasNextLong() == false) {
            check = false;
            System.out.println("CD price must be an Integer!");
            System.out.print("Try again: ");
        } else if (priceInput.equals("")) {
            check = false;
            System.out.println("CD price cannot be empty!");
            System.out.print("Try again: ");
        }
        return check;
    }

    public boolean checkPrice(long priceInput) {
        boolean check = true;
        if (priceInput < 0) {
            check = false;
            System.out.println("Price cannot be negative!");
            System.out.print("Try again: ");
        }
        return check;
    }

    public boolean checkYearInput(String yearInput) {
        boolean check = true;
        if (yearInput.equals("")) {
            check = false;
            System.out.println("The CD publishing year cannot be empty!");
            System.out.print("Try again: ");
        } else if (yearInput.matches("\\d{4}") == false) {
            check = false;
            System.out.println("The CD publishing year must be a (yyyy) format!");
            System.out.print("Try again: ");
        }
        return check;
    }
    
    public boolean checkYear(int yearInput) {
        boolean check = true;
        if (yearInput < 0) {
            check = false;
            System.out.println("Year cannot be negative!");
            System.out.print("Try again: ");
        } else if(yearInput > currentYear) {
            check = false;
            System.out.println("Year cannot be over the current year!");
            System.out.print("Try again: ");
        }
        return check;
    }
}
