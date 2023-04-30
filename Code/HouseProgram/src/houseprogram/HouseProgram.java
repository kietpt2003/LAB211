/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houseprogram;

import DAL.FileManagement;
import DTO.CDs;
import DTO.DataTransfer;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class HouseProgram {

    private int count;

    public HouseProgram() {
    }

    public void menu(CDs[] arrCDs) {
        int choice;
        String yearInput;
        String priceInput;
        boolean checkLong = true;
        boolean checkInt = true;
        DataTransfer data = new DataTransfer(arrCDs, count);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Add CD to the catalog");
            System.out.println("2. Search CD by CD title");
            System.out.println("3. Display the catalog");
            System.out.println("4. Update CD");
            System.out.println("\t1. Update CD");
            System.out.println("\t2. Delate CD");
            System.out.println("5. Save account to file.");
            System.out.println("6. Print list CDs from file.");
            System.out.println("Others-Quit");
            System.out.print("Please input your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    CDs objCD = new CDs(); //DTO
                    if (data.transferCheckSpace(count)) { //Nếu có chỗ trống thì add

                        //ID
                        System.out.print("Enter CD ID(CDxxx): ");
                        do {
                            objCD.setID(sc.next());
                        } while (data.transferID(objCD.getID()) == false);

                        //Name
                        System.out.print("Enter CD collecttion name(game/music/movie): ");
                        do {
                            objCD.setName(sc.next());
                        } while (data.transferName(objCD.getName()) == false);

                        //Type
                        System.out.print("Enter CD type(audio/video): ");
                        do {
                            objCD.setType(sc.next());
                        } while (data.transferType(objCD.getType()) == false);

                        //Title
                        System.out.print("Enter CD title: ");
                        do {
                            objCD.setTitle(sc.next());
                        } while (data.transferTitle(objCD.getTitle()) == false);

                        //Price
                        System.out.print("Enter CD price: ");
                        do {
                            priceInput = sc.next();
                            checkLong = data.transferInputPrice(priceInput); //Nhập vô kiểu dữ liệu nào cũng dc(check empty)
                            if (checkLong) {
                                objCD.setPrice(Long.parseLong(priceInput)); //Biến String thành Long
                                checkLong = data.transferPrice(objCD.getPrice()); //Check số âm
                            }
                        } while (checkLong == false);

                        //Year
                        System.out.print("Enter CD publishing year(yyyy): ");
                        do {
                            yearInput = sc.next();
                            checkInt = data.transferInputYear(yearInput); //Nhập vô kiểu dữ liệu nào cũng dc(check empty)
                            if (checkInt) {
                                objCD.setYear(Integer.parseInt(yearInput)); //Biến String thành Int
                                checkInt = data.transferYear(objCD.getYear()); //Check số âm
                            }
                        } while (checkInt == false);
                        if (data.transferAdd(objCD)) { //Add new CD to array
                            System.out.println("Add CD Success!");
                        }
                    } else {
                        System.out.println("Unable to add CD.");
                    }
                    break;
                case 2:
                    CDs objCD2 = new CDs();
                    System.out.print("Input CD title for searching: ");
                    do {
                        objCD2.setTitle(sc.next());
                    } while (data.transferTitle(objCD2.getTitle()) == false);
                    data.transferSearch(objCD2.getTitle());
                    break;
                case 3:
                    if (data.transferPrint()) {
                        System.out.println("Print Success!");
                    } else {
                        System.out.println("The CD array is empty => Have no any CDs");
                    }
                    break;
                case 4:
                    int choose;
                    boolean continuing = false;
                    boolean checkInput = true;
                    String input;
                    do {
                        System.out.println("1. Update CD.");
                        System.out.println("2. Delate CD.");
                        System.out.println("Others - Return to main menu.");
                        System.out.print("Please enter your choice: ");
                        choose = sc.nextInt();
                        if (choose == 1) {
                            do {
                                if (data.transferUpdate()) {
                                    System.out.println("Update Success!");
                                } else {
                                    System.out.println("Update Fail.");
                                }
                                System.out.print("Continuing(Y/N)? ");
                                do {
                                    input = sc.next().toUpperCase();
                                    if (input.equals("Y")) {
                                        continuing = true;
                                        checkInput = true;
                                    } else if (input.equals("N")) {
                                        continuing = false;
                                        checkInput = true;
                                    } else {
                                        checkInput = false;
                                        System.out.println("Wrong input");
                                        System.out.print("Try again: ");
                                    }
                                } while (checkInput == false);
                            } while (continuing);

                        } else if (choose == 2) {
                            do {
                                if (data.transferRemove()) {
                                    System.out.println("Remove Successfully!");
                                } else {
                                    System.out.println("Remove Fail!");
                                }
                                System.out.print("Continuing(Y/N)? ");
                                do {
                                    input = sc.next().toUpperCase();
                                    if (input.equals("Y")) {
                                        continuing = true;
                                        checkInput = true;
                                    } else if (input.equals("N")) {
                                        continuing = false;
                                        checkInput = true;
                                    } else {
                                        checkInput = false;
                                        System.out.println("Wrong input");
                                        System.out.print("Try again: ");
                                    }
                                } while (checkInput == false);
                            } while (continuing);
                        }
                    } while (choose >= 1 && choose <= 2);
                    break;
                case 5:
                    continuing = false;
                    checkInput = true;
                    do {
                        if (data.transferSave()) {
                            System.out.println("Changes have been saved successfully!");
                        } else {
                            System.out.println("Save failed.");
                        }
                        System.out.print("Continuing(Y/N)? ");
                        do {
                            input = sc.next().toUpperCase();
                            if (input.equals("Y")) {
                                continuing = true;
                                checkInput = true;
                            } else if (input.equals("N")) {
                                continuing = false;
                                checkInput = true;
                            } else {
                                checkInput = false;
                                System.out.println("Wrong input");
                                System.out.print("Try again: ");
                            }
                        } while (checkInput == false);
                    } while (continuing);
                    break;
                case 6:
                    continuing = false;
                    checkInput = true;
                    do {
                        if (data.transferPrintSort()) {
                            System.out.println("Print Success!");
                        } else {
                            System.out.println("The CD array is empty => Have no any CDs");
                        }
                        System.out.print("Continuing(Y/N)? ");
                        do {
                            input = sc.next().toUpperCase();
                            if (input.equals("Y")) {
                                continuing = true;
                                checkInput = true;
                            } else if (input.equals("N")) {
                                continuing = false;
                                checkInput = true;
                            } else {
                                checkInput = false;
                                System.out.println("Wrong input");
                                System.out.print("Try again: ");
                            }
                        } while (checkInput == false);
                    } while (continuing);
                    break;
            }

        } while (choice >= 1 && choice <= 6);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        DataTransfer Data = new DataTransfer();
        HouseProgram progr = new HouseProgram();
        FileManagement file = new FileManagement();

        //From DAL to DTO
        Data.arrCDs = file.getFile();
        Data.count = file.count;

        //From DTO to GUI
        progr.count = Data.count; //Check free space
        progr.menu(Data.arrCDs);
    }
}
