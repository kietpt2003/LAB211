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
public class mytool {

    public CDs[] arrCDs;
    check check = new check();

    public mytool() {
    }

    public mytool(CDs[] arr) {
        this.arrCDs = arr;
    }

    //Search CD by title
    public boolean searchCD(String inputTitle) {
        boolean checkTitle = false;
        int countItem = 0;
        if (arrCDs[0] != null) {
            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                if (arrCDs[i].getTitle().contains(inputTitle)) {
                    checkTitle = true;
                    countItem++;
                }
            }
            if (checkTitle == false) { //Truong hop co array nhung ko co Name thoa dieu kien
                System.out.println("Have no any CD with that Title");
            } else {
                System.out.println("Found " + countItem + "!");
                for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                    if (arrCDs[i].getTitle().contains(inputTitle)) {
                        System.out.println(arrCDs[i]);
                    }
                }
            }
        } else { //Truong hop array empty => khong co Title thoa dieu kien
            System.out.println("The CD array is empty => Have no any Product");
        }
        return checkTitle;
    }

    //Print all CDs catalog
    public boolean printAll() {
        boolean check;
        if (arrCDs[0] != null) {
            check = true;
            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                System.out.println(arrCDs[i]);
            }
        } else { //Truong hop array empty => khong co Title thoa dieu kien
            check = false;
        }
        return check;
    }

    public boolean UpdateCDID() {
        String ID = "";
        String Name = "";
        String type = "";
        String title = "";
        String priceInput = "";
        String yearInput = "";
        long price = 0;
        int year = 0;
        int index = 0;
        boolean checkUpdate = true;
        boolean checkID = false;
        boolean checkName = false;
        boolean checkType = false;
        boolean checkTitle = false;
        boolean checkPrice = true;
        boolean checkYear = true;
        Scanner sc = new Scanner(System.in);
        check.arrCDs = arrCDs;
        if (arrCDs[0] == null) { //Check file empty <=> arr[0] không có
            checkUpdate = false;
        } else {
            System.out.print("Enter CD ID that you want to update: ");
            ID = sc.nextLine();
            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) { //check if the product name exist or not
                if (arrCDs[i].getID().compareTo(ID) == 0) { //Neu so sanh ra giong nhau thì == 0 (Hoi nguoc doi ti)
                    checkID = true;
                    index = i; //save the location for next use
                }
            }
        }
        if (checkID == true) {

            //New ID
            System.out.print("Enter new ID: ");
            do {
                try {
                    ID = sc.nextLine();
                    if (ID.equals("")) {
                        ID = arrCDs[index].getID();
                        checkID = true;
                        System.out.println("Keep old ID.");
                    } else {
                        checkID = check.checkID(ID);
                    }
                } catch (Exception e) {
                }
            } while (checkID == false);

            //New Name
            System.out.print("Enter new name: ");
            do {
                try {
                    Name = sc.nextLine();
                    if (Name.equals("")) {
                        Name = arrCDs[index].getID();
                        checkName = true;
                        System.out.println("Keep old Name.");
                    } else {
                        checkName = check.checkName(Name);
                    }
                } catch (Exception e) {
                }
            } while (checkName == false);

            //New type
            System.out.print("Enter new type: ");
            do {
                try {
                    type = sc.nextLine();
                    if (type.equals("")) {
                        type = arrCDs[index].getID();
                        checkType = true;
                        System.out.println("Keep old type.");
                    } else {
                        checkType = check.checkType(type);
                    }
                } catch (Exception e) {
                }
            } while (checkType == false);

            //New title
            System.out.print("Enter new title: ");
            do {
                try {
                    title = sc.nextLine();
                    if (title.equals("")) {
                        title = arrCDs[index].getTitle();
                        checkTitle = true;
                        System.out.println("Keep old title.");
                    } else {
                        checkTitle = check.checkTitle(title);
                    }
                } catch (Exception e) {
                }
            } while (checkTitle == false);

            //New price
            System.out.print("Enter new price: ");
            do {
                try {
                    priceInput = sc.nextLine();
                    if (priceInput.equals("")) {
                        price = arrCDs[index].getPrice();
                        checkPrice = true;
                        System.out.println("Keep old Price.");
                    } else {
                        checkPrice = check.checkLongVar(priceInput);
                        if (checkPrice) {
                            price = Long.parseLong(priceInput);
                            checkPrice = check.checkPrice(price);
                        }
                    }
                } catch (Exception e) {
                }
            } while (checkPrice == false);

            //New year
            System.out.print("Enter new year: ");
            do {
                try {
                    yearInput = sc.nextLine();
                    if (priceInput.equals("")) {
                        year = arrCDs[index].getYear();
                        checkPrice = true;
                        System.out.println("Keep old Year.");
                    } else {
                        checkYear = check.checkYearInput(yearInput);
                        if (checkYear) {
                            year = Integer.parseInt(yearInput);
                            checkYear = check.checkYear(year);
                        }
                    }
                } catch (Exception e) {
                }
            } while (checkYear == false);
            arrCDs[index] = new CDs(ID, Name, type, title, price, year);
            checkUpdate = true;
        } else {
            checkUpdate = false;
        }
        return checkUpdate;
    }

    public boolean deleteCD() {
        boolean check = false;
        int index = -1;
        int index2 = -1;
        String ID = "";
        Scanner sc = new Scanner(System.in);
        if (arrCDs[0] == null) {
            check = false;
        } else {
            System.out.print("Enter CD ID that you want to delete: ");
            ID = sc.nextLine();
            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) { //check if the CD ID exist or not
                if (arrCDs[i].getID().compareTo(ID) == 0) { //Neu so sanh ra giong nhau thì == 0 (Hoi nguoc doi ti)
                    index = i;
                    check = true;
                }
            }
            for (int i = index; i < arrCDs.length - 1; i++) { //check if the CD ID exist or not
                arrCDs[i] = arrCDs[i + 1];
            }
        }
        return check;
    }

    //Print CDs sort by name
    public boolean printSortName() {
        boolean check;
        if (arrCDs[0] != null) {
            check = true;
            //Print theo thứ tự game -> movie -> music
            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                if (arrCDs[i].getName().equals("game")) {
                    System.out.println(arrCDs[i]);
                }
            }

            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                if (arrCDs[i].getName().equals("movie")) {
                    System.out.println(arrCDs[i]);
                }
            }

            for (int i = 0; i < arrCDs.length && arrCDs[i] != null; i++) {
                if (arrCDs[i].getName().equals("music")) {
                    System.out.println(arrCDs[i]);
                }
            }
        } else { //Truong hop array empty => khong co Title thoa dieu kien
            check = false;
        }
        return check;
    }
}
