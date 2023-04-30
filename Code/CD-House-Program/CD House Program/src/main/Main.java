/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.ProductList;
import ui.Menu;

/**
 *
 * @author QUANG KIET
 */
public class Main {

    public static void main(String[] args) {
        ProductList pList = new ProductList();
        pList.readDataFromFile();
        Menu menu = new Menu("CD House Program-------");
        menu.addItem("1-Add CD to the catalog.");
        menu.addItem("2-Search CD by title.");
        menu.addItem("3-Display the catalog.");
        menu.addItem("4-Update CD.");
        menu.addItem("5-Save account to file.");
        menu.addItem("6-Print list CDs from file");
        menu.addItem("7-Exit..");
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    pList.AddCD();
                    break;
                case 2:
                    pList.searchTitle();
                    break;
                case 3:
                    pList.Display();
                    break;
                case 4:
                    pList.updateCD();
                    break;
                case 5:
                    pList.saveToFile();
                    break;
                case 6:
                    pList.printAllCDFromFile();
                    break;

                case 7:
                    System.out.println("Thanks for using our service.See you next time.");
                    break;
            }
        } while (choice != 7);
    }
}
