/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectmanagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 *
 * @author ACER
 */
public class ProductList {

    Scanner sc = new Scanner(System.in);
    String ProductID;
    String ProductName;
    long price;
    long Quantity;
    String Status;
    List<Product> list = new ArrayList<Product>();
    List<Product> fileList = new ArrayList<Product>();
    Product product = new Product();

    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }
        System.out.println(product);
        list.add(product);
        return true;
    }

    public void createProduct() {
        String ProductID;
        String ProductName;
        long price = 0;
        long Quantity = 0;
        String Status;
        boolean checkName = true;
        boolean checkPrice = true;
        boolean checkQuantity = true;
        boolean checkStatus = true;
        ProductList checkDup = new ProductList();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        do {
            ProductID = sc.nextLine();
            if (ProductID.equals("")) {
                System.out.println("The Product ID cannot be empty at the first time input!");
                System.out.print("Try again: ");
            }
        } while (ProductID.equals(""));

        System.out.print("Enter Product Name: ");
        do {
            ProductName = sc.nextLine();
            if (ProductName.length() < 5 || ProductName.contains(" ")) {
                System.out.println("The Product Name must be at least 5 characters and no spaces!");
                System.out.print("Try again: ");
                checkName = false;
            } else if (checkDup.checkDuplicate(ProductName)) {
                System.out.println("The Product Name is duplicate!");
                System.out.print("Try again: ");
                checkName = false;
            } else if (ProductName.equals("")) {
                System.out.println("The Product Name cannot be empty at the first time input!");
                System.out.print("Try again: ");
                checkName = false;
            } else {
                checkName = true;
            }
        } while (checkName == false);

        System.out.print("Enter Product Price: ");
        do {
            try {
                price = Long.parseLong(sc.nextLine());
                if (price < 0 || price > 10000) {
                    System.out.println("The price must be in ranges 0 to 10000!");
                    System.out.print("Try again: ");
                    checkPrice = false;
                } else {
                    checkPrice = true;
                }
            } catch (Exception e) {
                System.out.println("The price cannot be empty at the first time input!");
                System.out.print("Try again: ");
            }
        } while (checkPrice == false);

        System.out.print("Enter Product Quantity: ");
        do {
            try {
                Quantity = Long.parseLong(sc.nextLine());
                if (Quantity < 0 || Quantity > 1000) {
                    System.out.println("The Quantity must be in ranges 0 to 1000!");
                    System.out.print("Try again: ");
                    checkQuantity = false;
                } else {
                    checkQuantity = true;
                }
            } catch (Exception e) {
                System.out.println("The Quantity cannot be empty at the first time input!");
                System.out.print("Try again: ");
            }
        } while (checkQuantity == false);

        System.out.print("Enter Product Status: ");
        do {
            Status = sc.nextLine();
            if (Status.equals("Available") == true && Status.equals("Not Available") == false) {
                checkStatus = true;
            } else if (Status.equals("Available") == false && Status.equals("Not Available") == true) {
                checkStatus = true;
            } else if (Status.equals("")) {
                System.out.println("The Status cannot be empty at the first time input!");
                System.out.print("Try again: ");
                checkStatus = false;
            } else {
                System.out.println("The Status must be write in this form: Available or Not Available");
                System.out.print("Try again: ");
                checkStatus = false;
            }
        } while (checkStatus == false);

        addProduct(new Product(ProductID, ProductName, price, Quantity, Status));
    }

    public boolean checkDuplicate(String ProductName) {
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductName().equals(ProductName)) {
                check = true; //check if it duplicate
            } else {
                check = false; //check if it not duplicate
            }
        }
        return check;
    }

    public void existProduct() {
        String checkName;
        boolean check = false;
        System.out.println("Please input the Product Name that you want to check:");
        checkName = sc.nextLine();
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).getProductName().equals(checkName)) {
                check = true;
                break;
            } else {
                check = false;
            }
        }
        if (check) {
            System.out.println("Exist Product");
        } else {
            System.out.println("No Product Found!");
        }
    }

    public void addFileToList() {
        String productID = "";
        String productName = "";
        long price = 0;
        long Quantity = 0;
        String Status = "";
        try {
            File file = new File("products.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arrData = data.split(" - ", 5); //co 5 phan tu nen can tach ra 5
                productID = arrData[0];
                productName = arrData[1];
                price = Long.parseLong(arrData[2]);
                Quantity = Long.parseLong(arrData[3]);
                Status = arrData[4];
                fileList.add(new Product(productID, productName, price, Quantity, Status));

            }
        } catch (Exception e) {
        }
    }

    public void searchProduct() {
        String searchName;
        boolean checkName = false;
        List<Product> newList = new ArrayList<Product>(); //get ready for adding string name
        System.out.println("Please input a string of Name that you want to find:");
        searchName = sc.nextLine();
        if (fileList.size() > 0) {
            for (int i = 0; i < fileList.size(); i++) {
                if (fileList.get(i).getProductName().contains(searchName)) {
                    newList.add(fileList.get(i));
                    checkName = true;
                }
            }
            if (checkName == false) { //Truong hop co list nhung ko co Name thoa dieu kien
                System.out.println("Have no any Product");
            }
            for (int i = 0; i < newList.size() - 1; i++) { //sort list after getting the new list by string name
                if (newList.get(i).getProductName().compareTo(newList.get(i + 1).getProductName()) > 0) { //So sanh neu cai dau lon hon cai sau thi swap
                    Collections.swap(newList, i, i + 1);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                System.out.println(newList.get(i));
            }
        } else { //Truong hop list empty => khong co Name thoa dieu kien
            System.out.println("The list is empty => Have no any Product");
        }
    }

    public void UpdateProductID() {
        String ProductID = "";
        String ProductName = "";
        long price = 0;
        long Quantity = 0;
        String Status = "";
        int index = 0;
        boolean checkID = false;
        boolean checkPrice = true;
        boolean checkQuantity = true;
        boolean checkStatus = true;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product ID that you want to update: ");
        ProductID = sc.nextLine();
        for (int i = 0; i < fileList.size(); i++) { //check if the product name exist or not
            if (fileList.get(i).getProductID().compareTo(ProductID) == 0) { //Neu so sanh ra giong nhau thì == 0 (Hoi nguoc doi ti)
                checkID = true;
                index = i; //save the location for next use
            }
        }
        if (checkID == true) {
            ProductName = fileList.get(index).getProductName(); //ko getID vi ID da nhap roi con Name ko doi nen lay cai cu luon

            System.out.print("Enter new price: ");
            do {
                try {
                    price = Long.parseLong(sc.nextLine());
                    if (price < 0 || price > 10000) {
                        System.out.println("The price must be in ranges 0 to 10000!");
                        System.out.print("Try again: ");
                        checkPrice = false;
                    } else {
                        checkPrice = true;
                    }
                } catch (Exception e) {
                    price = fileList.get(index).getPrice();
                    checkPrice = true;
                }
            } while (checkPrice == false);

            System.out.print("Enter new Quantity: ");
            do {
                try {
                    Quantity = Long.parseLong(sc.nextLine());
                    if (Quantity < 0 || Quantity > 1000) {
                        System.out.println("The Quantity must be in ranges 0 to 1000!");
                        System.out.print("Try again: ");
                        checkQuantity = false;
                    }
                } catch (Exception e) {
                    Quantity = fileList.get(index).getQuantity();
                    checkQuantity = true;
                }
            } while (checkQuantity == false);

            System.out.print("Enter new Status: ");
            do {
                try {
                    Status = sc.nextLine();
                    if (Status.equals("Available") == true && Status.equals("Not Available") == false) {
                        checkStatus = true;
                    } else if (Status.equals("Available") == false && Status.equals("Not Available") == true) {
                        checkStatus = true;
                    } else if (Status.equals("")) {
                        Status = fileList.get(index).getStatus();
                        checkStatus = true;
                    } else {
                        System.out.println("The Status must be write in this form: Available or Not Available");
                        System.out.print("Try again: ");
                        checkStatus = false;
                    }
                } catch (Exception e) {
                    Status = fileList.get(index).getStatus();
                    checkStatus = true;
                }
            } while (checkStatus == false);
        } else {
            System.out.println("Product Name does not exist.");
        }

        fileList.set(index, new Product(ProductID, ProductName, price, Quantity, Status));
    }

    public void deleteProduct() {
        String ProductID = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product ID that you want to delete: ");
        ProductID = sc.nextLine();
        for (int i = 0; i < fileList.size(); i++) { //check if the product name exist or not
            if (fileList.get(i).getProductID().compareTo(ProductID) == 0) { //Neu so sanh ra giong nhau thì == 0 (Hoi nguoc doi ti)
                fileList.remove(i);
                System.out.println("Remove Successfully!");
            }
        }
    }

    public void saveFile() {
        try {
            File file = new File("products.txt");
            if (!file.exists()) { // if file doesnt exists, then create it
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < fileList.size(); i++) {
                bw.write(fileList.get(i).toString() + "\n");
            }
            System.out.println("Changes have been saved successfully!");
            bw.close();
        } catch (Exception e) {
            System.out.println("The File is empty.");
        }
    }

    public void printAll() {
        String productID = "";
        String productName = "";
        long price = 0;
        long Quantity = 0;
        String Status = "";
        try {
            if (fileList.size() == 0) {
                System.out.println("The File is empty");
            } else {
                for (int i = 0; i < fileList.size() - 1; i++) {
                    for (int j = i + 1; j < fileList.size(); j++) {
                        if (fileList.get(i).getQuantity() < fileList.get(j).getQuantity()) { //i < j (descending Quantity)
                            Product tmp = fileList.get(i);
                            fileList.set(i, fileList.get(j));
                            fileList.set(j, tmp);
                        }
                    }
                }
                for (int i = 0; i < fileList.size() - 1; i++) { //i = j (Quantity equals)
                    for (int j = i + 1; j < fileList.size(); j++) {
                        if (fileList.get(i).getQuantity() == fileList.get(j).getQuantity()) {
                            if (fileList.get(i).getPrice() > fileList.get(j).getPrice()) { //i > j (ascending price)
                                Product tmp = fileList.get(i);
                                fileList.set(i, fileList.get(j));
                                fileList.set(j, tmp);
                            }
                        }
                    }
                }
                System.out.println("The Data from the File:");
                for (int i = 0; i < fileList.size(); i++) {
                    System.out.println(fileList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("The file is empty");
        }
    }

    public void bonus1() {
        createProduct();
        for (int i = 0; i < list.size(); i++) {
            fileList.add(0, list.get(i));
        }
        System.out.println("Add Successfully!");
    }

    public void bonus2() {
        createProduct();
        for (int i = 0; i < list.size(); i++) {
            fileList.add(list.get(i));
        }
        System.out.println("Add Successfully!");
    }

    public void bonus3() {
        createProduct();
        for (int i = 0; i < fileList.size(); i++) {
            fileList.remove(i);
        }
        for (int i = 0; i < fileList.size(); i++) {
            fileList.add(i, list.get(i));
        }
        System.out.println("Replace Successfully!");
    }

    public void bonus4() {
        int getFID = 0;
        int getLID = 0;
        String inputF;
        String inputL;
        createProduct();
        System.out.println("Input the Product ID from the File that you want to replcae.");
        System.out.print("Your input: ");
        inputF = sc.nextLine();
        System.out.println("Input the Product ID from the List that you want to save.");
        System.out.print("Your input: ");
        inputL = sc.nextLine();
        for (int i = 0; i < fileList.size(); i++) { //Get the index of the product in file
            if (fileList.get(i).getProductID().equals(inputF)) {
                getFID = i;
            }
        }
        for (int i = 0; i < list.size(); i++) { //Get the index of the product in list
            if (list.get(i).getProductID().equals(inputL)) {
                getLID = i;
            }
        }
        fileList.set(getFID, list.get(getLID)); //Replace
        System.out.println("Replace Successfully!");
    }
}
