/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import data.Account;
import data.AccountChecker;
import data.Config;
import data.Dealer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class MyTool {

    private int pos = -1;
    public List<Config> listFile = new ArrayList<Config>(); //List of File Name (accounts.txt, dealers.txt, deliveries.txt)
    public List<Account> listAcc = new ArrayList<Account>(); //List of Account information(name, pass, role) in accounts.txt
    public List<Dealer> listDeal = new ArrayList<Dealer>(); //List of Dealers in dealers.txt
    AccountChecker check = new AccountChecker();
    Scanner sc = new Scanner(System.in);

    //Check account name
    public void checkName() { 
        boolean checkAcc = true;
        String nameInput = "";
        do {
            System.out.print("Your accounts name: ");
            nameInput = sc.nextLine();
            for (int i = 0; i < listAcc.size(); i++) { //Chạy for check Acc có tồn tại không
                checkAcc = check.checkAcc(listAcc.get(i).getName(), nameInput);
                if (checkAcc) { //Nếu có thì lưu lại vị trí xong thoát ra
                    pos = i;
                    break;
                }
            }
            if (checkAcc == false) { //Chạy hết for nếu không có thì báo ko có
                System.out.println("Invalid Account name. Try again.");
            }
        } while (checkAcc == false); //Không có thì bắt chạy lại
    }

    //Check the password
    public void checkPass() {
        boolean checkPass = true;
        do {
            System.out.print("Your password: ");
            checkPass = check.checkPass(listAcc.get(pos).getPass());
        } while (checkPass == false);
    }

    //Get the Role of the account that access
    public String myRole() {
        return listAcc.get(pos).getRole();
    }

    //Get file name (accounts.txt)
    public String getAccountFile() { //Get file accounts.txt
        return "DealerData/".concat(listFile.get(0).acc); //return DealerData/accounts.txt
    }

    //Get file name (dealers.txt)
    public String getDealerFile() { //Get file dealers.txt
        return "DealerData/".concat(listFile.get(0).dealer); //return DealerData/dealers.txt
    }

    //Get file name (deliveries.txt)
    public String getDeliverFile() { //Get file deliveries.txt
        return "DealerData/".concat(listFile.get(0).deliver); //return DealerData/deliveries.txt
    }

    //Adding new dealer to the dealer list
    public boolean addDealer(Dealer dealer) {
        if (dealer == null) {
            return false;
        }
        listDeal.add(dealer);
        System.out.println("Add new dealer success!");
        return true;
    }

    //Create object Dealer but not adding to the list
    public void createDealer() {
        String DealerID;
        String DealerName;
        String address;
        String phone;
        String statusInput;
        boolean status = false;
        boolean checkID;
        boolean checkDup = false;
        boolean checkName = true;
        boolean checkAdd = true;
        boolean checkPhone = true;
        boolean checkSta = true;

        //DealerID
        System.out.print("Enter Dealer ID(Dxxx): ");
        do {
            DealerID = sc.nextLine();
            for (int i = 0; i < listDeal.size(); i++) {
                if (listDeal.get(i).getDealerID().compareTo(DealerID) == 0) {
                    checkDup = true; //check if it duplicate
                }
            }
            if (checkDup) {
                checkID = false;
                checkDup = false; //Reset checDup = false for checking again
                System.out.println("The Dealer ID is duplicate!");
                System.out.print("Try again: ");
            } else if (DealerID.equals("")) {
                checkID = false;
                System.out.println("The Dealer ID cannot be empty!");
                System.out.print("Try again: ");
            } else if (DealerID.matches("D\\d{3}") == false) {
                checkID = false;
                System.out.println("The Dealer ID must be Dxxx format!");
                System.out.print("Try again: ");
            } else {
                checkID = true;
            }
        } while (checkID == false);

        //Deaeler name
        System.out.print("Enter Dealer name: ");
        do {
            DealerName = sc.nextLine();
            if (DealerName.equals("")) {
                checkName = false;
                System.out.println("The Dealer Name cannot be empty!");
                System.out.print("Try again: ");
            } else {
                checkName = true;
            }
        } while (checkName == false);

        //Address
        System.out.print("Enter Dealer address: ");
        do {
            address = sc.nextLine();
            if (address.equals("")) {
                checkAdd = false;
                System.out.println("The Dealer address cannot be empty!");
                System.out.print("Try again: ");
            } else {
                checkAdd = true;
            }
        } while (checkAdd == false);

        //Phone number
        System.out.print("Enter Dealer phone number: ");
        do {
            phone = sc.nextLine();
            if (phone.equals("")) {
                checkPhone = false;
                System.out.println("The Phone number cannot be empty!");
                System.out.print("Try again: ");
            } else if (phone.matches("\\d{9}|\\d{11}") == false) {
                checkPhone = false;
                System.out.println("The Phone number must be 9 or 11 digits!");
                System.out.print("Try again: ");
            } else {
                checkPhone = true;
            }
        } while (checkPhone == false);

        //Status
        System.out.print("Dealer continuing?(Y/N): ");
        do {
            statusInput = sc.nextLine();
            if (statusInput.toUpperCase().equals("Y")) {
                checkSta = true;
                status = true;
            } else if (statusInput.toUpperCase().equals("N")){
                checkSta = true;
                status = false;
            } else {
                System.out.println("Status cannot be empty!");
                System.out.print("Try again: ");
                checkSta = false;
            }
        } while (checkSta == false);

        addDealer(new Dealer(DealerID, DealerName, address, phone, status));
    }

    //Search dealer list
    public void searchDealer() {
        String searchName;
        boolean checkName = false;
        System.out.print("Please input Name that you want to find:");
        searchName = sc.nextLine();
        if (listDeal.size() > 0) {
            for (int i = 0; i < listDeal.size(); i++) {
                if (listDeal.get(i).getDealerName().contains(searchName)) {
                    checkName = true;
                    System.out.println("Found!");
                    System.out.println(listDeal.get(i).toString());
                }
            }
            if (checkName == false) { //Truong hop co list nhung ko co Name thoa dieu kien
                System.out.println("Have no any Product");
            }
        } else { //Truong hop list empty => khong co Name thoa dieu kien
            System.out.println("The list is empty => Have no any Product");
        }
    }

    //Delete a dealer from the list
    public void deleteDealer() {
        String DealerID;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Dealer ID that you want to delete: ");
        DealerID = sc.nextLine();
        for (int i = 0; i < listDeal.size(); i++) { //check if the product name exist or not
            if (listDeal.get(i).getDealerID().compareTo(DealerID) == 0) { //Nếu so sánh ra giống nhau thì trả về 0 chứ không phải 1
                listDeal.remove(i);
                System.out.println("Remove Successfully!");
            }
        }
    }

    //Update dealer by ID
    public void UpdateDealerID() {
        String DealerID = "";
        String DealerName = "";
        String address = "";
        String phone = "";
        boolean status = false;
        int index = 0;
        boolean checkID = false;
        boolean checkName = true;
        boolean checkAdd = true;
        boolean checkPhone = true;

        System.out.print("Enter Dealer ID that you want to update: ");
        DealerID = sc.nextLine();
        for (int i = 0; i < listDeal.size(); i++) { //check if the dealer name exist or not
            if (listDeal.get(i).getDealerID().compareTo(DealerID) == 0) { //Nếu so sánh ra giống nhau thì trả về 0 chứ không phải 1
                checkID = true;
                index = i; //save the location for next use
            }
        }
        System.out.println("Only name, addr and phone can be changed");
        //Nếu ID tồn tại thì update Dealer đó
        if (checkID == true) {

            //Dealer ID Giữ nguyên không đổi do yêu cầu đề bài)
            DealerID = listDeal.get(index).getDealerID();

            //New deaeler name
            System.out.print("Enter new Dealer name: ");
            do {
                try { //Try catch để không bị lỗi Enter, thử bỏ try catch sẽ hiểu, nó không cho nhập mà qua cái mới
                    DealerName = sc.nextLine();
                    if (DealerName.equals("")) { //Nếu User nhập ENTER thì catch và giữ nguyên Name cũ
                        DealerName = listDeal.get(index).getDealerName();
                        checkName = true;
                        System.out.println("Keep the old Dealer name.");
                    }
                    checkName = true;
                } catch (Exception e) {
                }
            } while (checkName == false);

            //New address
            System.out.print("Enter new Dealer address: ");
            do {
                try {
                    address = sc.nextLine();
                    if (address.equals("")) {
                        checkAdd = false;
                        System.out.println("The Dealer address cannot be empty!");
                        System.out.print("Try again: ");
                    } else {
                        checkAdd = true;
                    }
                } catch (Exception e) {
                    address = listDeal.get(index).getAddress();
                    checkAdd = true;
                }
            } while (checkAdd == false);

            //New phone number
            System.out.print("Enter new Dealer phone number: ");
            do {
                try {
                    phone = sc.nextLine();
                    if (phone.matches("\\d{9}|\\d{11}") == false) {
                        checkPhone = false;
                        System.out.println("The Phone number must be 9 or 11 digits!");
                        System.out.print("Try again: ");
                    } else {
                        checkPhone = true;
                    }
                } catch (Exception e) {
                    phone = listDeal.get(index).getPhoneNum();
                    checkPhone = true;
                }
            } while (checkPhone == false);

            //Status (Giữ nguyên không đổi do yêu cầu đề bài)
            status = listDeal.get(index).isStatus();
        } else {
            System.out.println("Dealer ID does not exist.");
        }

        System.out.println("New dealer has been added.");
        listDeal.set(index, new Dealer(DealerID, DealerName, address, phone, status));
    }

    //Print all dealer list
    public void printAll() {
        if (listDeal.size() == 0) {
            System.out.println("The File is empty");
        } else {
            System.out.println("The Dealers list:");
            for (int i = 0; i < listDeal.size(); i++) {
                System.out.println(listDeal.get(i));
            }
        }
    }

    //Print all continuing dealers
    public void printCon() {
        if (listDeal.size() == 0) {
            System.out.println("The File is empty");
        } else {
            for (int i = 0; i < listDeal.size(); i++) {
                if (listDeal.get(i).isStatus()) {
                    System.out.println(listDeal.get(i));
                }
            }
        }
    }

    //Print all un-continuing dealers
    public void printUnCon() {
        if (listDeal.size() == 0) {
            System.out.println("The File is empty");
        } else {
            for (int i = 0; i < listDeal.size(); i++) {
                if (listDeal.get(i).isStatus() != true) {
                    System.out.println(listDeal.get(i));
                }
            }
        }
    }

    //Write to File accounts.txt / dealers.txt
    public void saveFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) { // if file doesnt exists, then create it
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            if (fileName.matches("(.*)dealers.txt(.*)")) {
                for (int i = 0; i < listDeal.size(); i++) {
                    bw.write(listDeal.get(i).toString() + "\n");
                }
            } else if (fileName.matches("(.*)accounts.txt(.*)")) {
                for (int i = 0; i < listAcc.size(); i++) {
                    bw.write(listAcc.get(i).toString() + "\n");
                }
            }
            System.out.println("Changes have been saved successfully!");
            bw.close();
        } catch (Exception e) {
            System.out.println("The File is empty.");
        }
    }
}
