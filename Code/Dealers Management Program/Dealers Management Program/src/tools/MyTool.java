/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import data.Account;
import data.AccountChecker;
import data.Config;
import data.Dealer;
import data.Delivery;
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
    public List<Delivery> listDel = new ArrayList<Delivery>();
    AccountChecker check = new AccountChecker();
    Scanner sc = new Scanner(System.in);

    //Check account name
    public void checkName() {
        boolean checkAcc = true;
        String nameCheck = "";
        do {
            System.out.print("Your accounts name: ");
            nameCheck = sc.nextLine();
            for (int i = 0; i < listAcc.size(); i++) { //Chạy for check Acc có tồn tại không
                checkAcc = check.checkAcc(listAcc.get(i).getName(), nameCheck);
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
            } else if (statusInput.toUpperCase().equals("N")) {
                checkSta = true;
                status = false;
            } else if (statusInput.equals("")) {
                System.out.println("Status cannot be empty!");
                System.out.print("Try again: ");
                checkSta = false;
            } else {
                System.out.println("Wrong input!");
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
        System.out.print("Please input Name that you want to find: ");
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
                System.out.println("Have no any Dealer with that Name");
            }
        } else { //Truong hop list empty => khong co Name thoa dieu kien
            System.out.println("The list is empty => Have no any Product");
        }
    }

    //Delete a dealer from the list
    public void deleteDealer() {
        String DealerID;
        boolean checkID = false;
        do {
            System.out.print("Enter Dealer ID that you want to delete: ");
            do {
                DealerID = sc.nextLine();
                if (DealerID.equals("")) {
                    System.out.println("Can not input empty.");
                    System.out.print("Try again: ");
                }
            } while (DealerID.equals(""));

            for (int i = 0; i < listDeal.size(); i++) { //check if the product name exist or not
                if (listDeal.get(i).getDealerID().compareTo(DealerID) == 0) { //Nếu so sánh ra giống nhau thì trả về 0 chứ không phải 1
                    listDeal.remove(i);
                    checkID = true;
                    System.out.println("Remove Successfully!");
                }
            }
            if (checkID == false) {
                System.out.println("Dealer ID not valid, try again.");
            }
        } while (checkID == false);
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
        do {
            DealerID = sc.nextLine();
            if (DealerID.equals("")) {
                System.out.println("Can not input empty.");
                System.out.print("Try again: ");
            }
        } while (DealerID.equals(""));

        for (int i = 0; i < listDeal.size(); i++) { //check if the dealer name exist or not
            if (listDeal.get(i).getDealerID().compareTo(DealerID) == 0) { //Nếu so sánh ra giống nhau thì trả về 0 chứ không phải 1
                checkID = true;
                index = i; //save the location for next use
            }
        }

        //Nếu ID tồn tại thì update Dealer đó
        if (checkID == true) {
            System.out.println("Only name, addr and phone can be changed");
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
                }
            } while (checkPhone == false);

            //Status (Giữ nguyên không đổi do yêu cầu đề bài)
            status = listDeal.get(index).isStatus();
            System.out.println("New dealer has been added.");
            listDeal.set(index, new Dealer(DealerID, DealerName, address, phone, status));
        } else {
            System.out.println("Dealer ID does not exist.");
        }

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
        boolean isExist = false;
        if (listDeal.size() == 0) {
            System.out.println("The File is empty");
        } else {
            for (int i = 0; i < listDeal.size(); i++) {
                if (listDeal.get(i).isStatus()) {
                    System.out.println(listDeal.get(i));
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("There aren't any continuing dealers.");
            }
        }
    }

    //Print all un-continuing dealers
    public void printUnCon() {
        boolean isExist = false;
        if (listDeal.size() == 0) {
            System.out.println("The File is empty");
        } else {
            for (int i = 0; i < listDeal.size(); i++) {
                if (!listDeal.get(i).isStatus()) {
                    System.out.println(listDeal.get(i));
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println("There aren't any un-continuing dealers.");
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
            } else if (fileName.matches("(.*)deliveries.txt(.*)")) {
                for (int i = 0; i < listDel.size(); i++) {
                    bw.write(listDel.get(i).toString() + "\n");
                }
            }
            System.out.println("Changes have been saved successfully!");
            bw.close();
        } catch (Exception e) {
            System.out.println("The File is empty.");
        }
    }

    public boolean addDelivery(Delivery deliver) {
        if (deliver == null) {
            return false;
        }
        listDel.add(deliver);
        System.out.println("Add new delivery success!");
        return true;
    }

    public void createDelivery() {
        String ProductID;
        String CustomerName;
        String CusPhoneNumber;
        String CusAdd;
        String PackageStatus;
        boolean chDup = false;
        boolean check = true;

        // Add productID        
        System.out.print("Enter the product ID(Pxxx): ");
        do {
            ProductID = sc.nextLine();
            for (int i = 0; i < listDel.size(); i++) {
                if (listDel.get(i).getProductID().compareTo(ProductID) == 0) {
                    chDup = true;
                }
            }
            if (chDup) {
                check = true;
                chDup = false;
                System.out.println("The Product ID is duplicate!");
                System.out.print("Try again: ");
            } else if (ProductID.equals("")) {
                check = true;
                System.out.println("The Product ID cannot be empty!");
                System.out.print("Try again: ");
            } else if (ProductID.matches("P\\d{3}") == false) {
                check = true;
                System.out.println("The Product Id must be Pxxx format!");
                System.out.print("Try again: ");
            } else {
                check = false;
            }
        } while (check);

        //Add the customer name        
        System.out.print("Enter the customer name: ");
        do {
            CustomerName = sc.nextLine();
            if (CustomerName.equals("")) {
                check = true;
                System.out.println("The Customer Name cannot be empty!");
                System.out.print("Try again: ");
            }
        } while (check);

        //Add the customer address
        System.out.print("Enter the customer address: ");
        do {
            CusAdd = sc.nextLine();
            if (CusAdd.equals("")) {
                check = true;
                System.out.println("The Customer Address cannot be empty!");
                System.out.print("Try again: ");
            } else {
                check = false;
            }
        } while (check);

        //Add the customer phone number        
        System.out.print("Enter the customer phone number: ");
        do {
            CusPhoneNumber = sc.nextLine();
            if (CusPhoneNumber.equals("")) {
                check = true;
                System.out.println("The Phone number cannot be empty!");
                System.out.print("Try again: ");
            } else if (CusPhoneNumber.matches("\\d{9}|\\d{11}") == false) {
                check = true;
                System.out.println("The Phone number must be 9 or 11 digits!");
                System.out.print("Try again: ");
            } else {
                check = false;
            }
        } while (check);

        //Add the package status        
        System.out.print("Enter the package status: ");
        do {
            PackageStatus = sc.nextLine();
            if (PackageStatus.matches("Un-Deliver")) {
                check = false;
            } else if (PackageStatus.matches("On-Going")) {
                check = false;
            } else if (PackageStatus.matches("Delivered")) {
                check = false;
            } else if (PackageStatus.matches("")) {
                check = true;
                System.out.println("The Package Status cannot be empty!");
                System.out.print("Try again: ");
            } else {
                check = true;
                System.out.println("Wrong Input");
                System.out.print("Try again: ");
            }
        } while (check);
        addDelivery(new Delivery(ProductID, CustomerName, CusAdd, CusPhoneNumber, PackageStatus));
    }

    //Search Delivery
    public void searchDelivery() {
        boolean hasValue = false;
        System.out.print("Enter the customer name that you want to find: ");
        String s;
        do {
            s = sc.nextLine();
            if (s.equals("")) {
                System.out.println("Customer name can not empty.");
                System.out.print("Try again: ");
            }
        } while (s.equals(""));
        for (int i = 0; i < listDel.size(); i++) {
            if (listDel.get(i).getCustomerName().contains(s)) {
                System.out.println(listDel.get(i));
                hasValue = true;
            }
        }
        if (!hasValue) {
            System.out.println("Can not found");
        }

    }

    //Delete Delivery
    public void deleteDelivery() {
        System.out.print("Enter the ProductID that you want to delete: ");
        String dele;
        boolean checkID = false;
        do {
            dele = sc.nextLine();
            for (int i = 0; i < listDel.size(); i++) {
                if (listDel.get(i).getProductID().equals(dele)) {
                    checkID = true;
                    listDel.remove(i);
                    System.out.println("Remove Successfully!");
                }
            }
            if (checkID == false) {
                System.out.println("Product ID not valid, try again.");
            }
        } while (checkID == false);
    }

    //Update Delivery
    public void UpdateDelivery() {
        String ProductID;
        String CustomerName = null;
        String CusPhoneNumber = null;
        String CusAdd = null;
        String PackageStatus = null;
        boolean checkAdd = true;
        boolean checkName = true;
        boolean checkID = false;
        boolean checkNum = true;
        System.out.print("Enter the ProductID that you want to update: ");
        do {
            ProductID = sc.nextLine();
            if (ProductID.equals("")) {
                System.out.println("ProductID can not empty.");
                System.out.print("Try again: ");
            }
        } while (ProductID.equals(""));

        int index = 0;

        for (int i = 0; i < listDel.size(); i++) { //check if the product name exist or not
            if (listDel.get(i).getProductID().compareTo(ProductID) == 0) { //Neu so sanh ra giong nhau thì == 0
                checkID = true;
                index = i; //save the location for next use
            }
        }

        if (checkID == true) {

            ProductID = listDel.get(index).getProductID();

            //Add the customer name        
            System.out.print("Enter new customer name: ");
            do {
                try {
                    CustomerName = sc.nextLine();
                    if (CustomerName.equals("")) {
                        CustomerName = listDel.get(index).getCustomerName();
                        checkName = false;
                        System.out.println("Keep the old customer name.");
                    }
                    checkName = false;
                } catch (Exception e) {
                }
            } while (checkName);

            //Add the customer address
            System.out.print("Enter new customer address: ");
            do {
                try {
                    CusAdd = sc.nextLine();
                    if (CusAdd.equals("")) {
                        CusAdd = listDel.get(index).getCusAdd();
                        checkAdd = false;
                        System.out.println("Keep the old address.");
                    }
                    checkAdd = false;
                } catch (Exception e) {
                }
            } while (checkAdd);

            //Add the customer phone number        
            System.out.print("Enter new customer phone number: ");
            do {
                try {
                    CusPhoneNumber = sc.nextLine();
                    checkNum = false;

                    if (CusPhoneNumber.matches("\\d{9}|\\d{11}") == false) {
                        checkNum = true;
                        System.out.println("The Phone number must be 9 or 11 digits!");
                        System.out.print("Try again: ");
                    }
                } catch (NumberFormatException e) {
                    CusPhoneNumber = listDel.get(index).getCusPhoneNumber();
                    checkNum = false;
                }

            } while (checkNum);

            //Add the package status 
            PackageStatus = listDel.get(index).getPackageStatus();
            System.out.println("Update Success!");
            listDel.set(index, new Delivery(ProductID, CustomerName, CusAdd, CusPhoneNumber, PackageStatus));
        } else {
            System.out.println("ProductID does not exist.");
        }
    }

    //Print all Delivery list
    public void printAllDeliver() {
        if (listDel.isEmpty()) {
            System.out.println("The File is empty");
        } else {
            System.out.println("The Dealers list:");
            for (int i = 0; i < listDel.size(); i++) {
                System.out.println(listDel.get(i));
            }
        }
    }

    //Print out the "Un-Deliver" package
    public void printUndeliver() {
        boolean isExist = false;
        for (int i = 0; i < listDel.size(); i++) {
            if (listDel.get(i).getPackageStatus().contains("Un-Deliver")) {
                System.out.println(listDel.get(i));
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("There aren't any Un-Delivers's packages.");
        }
    }

    //Print out the "On-Going" package
    public void printOngoing() {
        boolean isExist = false;
        for (int i = 0; i < listDel.size(); i++) {
            if (listDel.get(i).getPackageStatus().contains("On-Going")) {
                System.out.println(listDel.get(i));
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("There aren't any On-Deliver's packages.");
        }
    }

    //Print out the "Delivered" package
    public void printDelivered() {
        boolean isExist = false;
        for (int i = 0; i < listDel.size(); i++) {
            if (listDel.get(i).getPackageStatus().contains("Delivered")) {
                System.out.println(listDel.get(i));
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("There aren't any Delivered's packages.");
        }
    }
}
