/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectmanagement;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Menu {

    public void Menu() {
        int choose;
        Scanner sc = new Scanner(System.in);
        File f = new File("products.txt");
        ProductList list = new ProductList();
        boolean change = true; //to check save file
        list.addFileToList(); //Lay thong tin tu File sang List(Avoid goi lai nhieu lan o cac ham)
        do {
            System.out.println("1. Create a Product.");
            System.out.println("  1.1 Add new Product to the first File.");
            System.out.println("  1.2 Add new Product to the end File.");
            System.out.println("  1.3 Replace Old Product with New Product.");
            System.out.println("  1.4 Replace all Product in File which new One.");
            System.out.println("2. Check exist Product.");
            System.out.println("3. Search Product Information by Name.");
            System.out.println("4. Update Product:");
            System.out.println("  4.1. Update Product.");
            System.out.println("  4.2. Delete Product.");
            System.out.println("5. Save Products to file.");
            System.out.println("6. Print list Products from the file.");
            System.out.println("Others - Quit.");
            System.out.print("Please enter your choice: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    int choice;
                    do {
                        System.out.println("1. Add new Product to the first File.");
                        System.out.println("2. Add new Product to the end File.");
                        System.out.println("3 Replace Old Product with New Product.");
                        System.out.println("4 Replace all Product in File which new One.");
                        System.out.println("Others - Return to main menu.");
                        System.out.print("Please enter your choice: ");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                list.bonus1();
                                break;
                            case 2:
                                list.bonus2();
                                break;
                            case 3:
                                list.bonus3();
                                break;
                            case 4:
                                list.bonus4();
                                break;
                        }
                    } while (choice >= 1 && choice <= 4);
                    break;
                case 2:
                    list.existProduct();
                    break;
                case 3:
                    list.searchProduct();
                    break;
                case 4:
                    int choices;
                    do {
                        System.out.println("1. Update Product.");
                        System.out.println("2. Delete Product.");
                        System.out.println("Others - Return to main menu.");
                        System.out.print("Please enter your choice: ");
                        choices = sc.nextInt();
                        if (choices == 1) {
                            list.UpdateProductID();
                        } else if (choices == 2) {
                            list.deleteProduct();
                        }
                    } while (choices >= 1 && choices <= 2);
                    break;
                case 5:
                    list.saveFile();
                    change = false;
                    break;
                case 6:
                    list.printAll();
                    break;
                default:
                    if (change == true) {
                        System.out.print("Save file?(Y/N): ");
                        String ans = "";
                        boolean check = true;
                        Scanner scan = new Scanner(System.in);
                        do {
                            ans = scan.nextLine().toUpperCase();
                            if (ans.equals("Y")) {
                                list.saveFile();
                                System.out.print("Save Successfully!");
                            } else if (ans.equals("N")) {
                                System.out.print("Save Fail. Good Bye!");
                            } else {
                                check = false;
                            }
                        } while (check == false);
                    } else {
                        System.out.println("Good Bye!");
                    }
            }
        } while (choose >= 1 && choose <= 6);
    }
}
