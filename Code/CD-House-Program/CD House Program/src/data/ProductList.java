/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import utils.Validation;

/**
 *
 * @author QUANG KIET
 */
public class ProductList {
    
    private ArrayList<Product> list = new ArrayList();
    private ArrayList<Product> listDataFile = new ArrayList();
    
    public void addDataFromFileToList() {
        listDataFile.clear();
        try {
            FileReader fr = new FileReader("CD.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String name = stk.nextToken();
                String type = stk.nextToken();
                String title = stk.nextToken();
                double unitprice = Double.parseDouble(stk.nextToken());
                String id = stk.nextToken();
                int Publishingyear = Integer.parseInt(stk.nextToken());
                listDataFile.add(new Product(name, type, title, unitprice, id, Publishingyear));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void readDataFromFile() {
        try {
            FileReader fr = new FileReader("CD.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String name = stk.nextToken();
                String type = stk.nextToken();
                String title = stk.nextToken();
                double unitprice = Double.parseDouble(stk.nextToken());
                String id = stk.nextToken();
                int Publishingyear = Integer.parseInt(stk.nextToken());
                list.add(new Product(name, type, title, unitprice, id, Publishingyear));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void AddCD() {
        String name, type, title, id;
        
        int index;
        if (list.size() >= 10) {
            System.out.println("Unable to add CD");
        }
        do {
            id = Validation.regexString("Enter ID's CD(CDxx): ", "Wrong format.Input again please", "[C|c][D|d]\\d{2}$");
            index = checkID(id);
            if (index >= 0) {
                System.out.println("CD's ID has been already in list.Input again please.");
            }
        } while (index >= 0);
        int choose = Validation.getAnInteger1("Enter CD collection name(1.Game, 2.Movie, 3.Music):", "Just 1, 2 or 3", 1, 2, 3);
        if (choose == 1) {
            name = "Game";
        } else if (choose == 2) {
            name = "Movie";
        } else {
            name = "Music";
        }
        int choice = Validation.getAnInteger("Enter CD Type(1.Audio or 2.Video): ", "Just 1 or 2 ", 1, 2);
        if (choice == 1) {
            type = "Audio";
        } else {
            type = "Video";
        }
        title = Validation.getString("Enter title:", "Wrong!! Input again please");
        
        double unitprice = Validation.getADouble("Enter Price CD[0..10000]: ", "Just from 0 to 10000", 0, 10000);
        int Publishingyear = Validation.getAnInteger("Enter year[2000..2022]:", "Just from 2000 to 2022", 2000, 2022);
        list.add(new Product(name, type, title, unitprice, id, Publishingyear));
        
        System.out.println("The CD's information has been added..");
    }
    
    public int checkID(String id) {
        if (list.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public void searchTitle() {
        ArrayList<Product> listSP = new ArrayList();
        boolean flag = false;
        String name = Validation.getString("Enter Name CD's title: ", "Not blank or empty.");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().toLowerCase().contains(name.toLowerCase())) {
                listSP.add(list.get(i));
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("Have not found.");
            return;
        }
        Collections.sort(listSP, (o1, o2) -> {
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
        });
        System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
        System.out.println("|     NAME      |      TYPE     |        TITLE       |   PRICE  |    ID    |   PUBLISHING YEAR  |");
        System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
        for (int i = 0; i < listSP.size(); i++) {
            listSP.get(i).showResult();
        }
        System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
    }
    
    public void Display() {
        if (list.isEmpty()) {
            System.out.println("List is empty.Nothing to print.");
        } else {
            System.out.println("HERE IS INFORMATION OF LIST CD:");
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
            System.out.println("|     NAME      |      TYPE     |        TITLE       |   PRICE  |    ID    |   PUBLISHING YEAR  |");
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).showResult();
            }
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
        }
    }
    
    public void updateCD() {
        int choice = Validation.getAnInteger("Enter your choice(1.Update CD OR 2.Delete CD): ", "Just 1 or 2.", 1, 2);
        if (choice == 1) {
            String id = Validation.regexString("Enter ID's CD(CDxx): ", "Wrong format.Input again", "^[C|c][D|d]\\d{2}$");
            int index = checkID(id);
            if (index >= 0) {
                boolean check;
                String newName;
                do {
                    check = true;
                    newName = Validation.updateString("Enter New Name CD(Game/Music/Movie): ", list.get(index).getName());
                    if (newName.isEmpty()) {
                        System.out.println("Input New Name please.");
                        check = false;
                    }
                } while (check == false);
                String newTitle = Validation.updateString("Enter New Title:", list.get(index).getTitle());
                double newUnitprice = Validation.updateADouble("Enter New Price CD: ", 0, 10000, list.get(index).getUnitprice());
                int newPublisingYear = Validation.updateAnInteger("Enter New Quantity Product: ", 2000, 2022, list.get(index).getPublishingyear());
                list.get(index).setName(newName);
                list.get(index).setTitle(newTitle);
                list.get(index).setUnitprice(newUnitprice);
                list.get(index).setPublishingyear(newPublisingYear);              
                System.out.println("The CD's information has been updated..Successfully.");
            } else {
                System.out.println("CD name does not exist.");
            }
        } else {
            String id = Validation.getString("Enter ID's CD: ", "Not blank or empty.");
            int index = checkID(id);
            if (index >= 0) {
                list.remove(index);
                System.out.println("The CD's information has been deleted..Successfully.");
            } else {
                System.out.println("CD name does not exist.");
            }
        }
    }
    
    public void saveToFile() {
        try {
            File f = new File("CD.dat");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Save to file Successfully.");
    }
    
    public void printAllCDFromFile() {
        addDataFromFileToList();
        if (listDataFile.isEmpty()) {
            System.out.println("List is empty.Nothing to print.");
        } else {
            System.out.println("HERE IS INFORMATION OF LIST CD FROM FILE:");
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
            System.out.println("|     NAME      |      TYPE     |        TITLE       |   PRICE  |    ID    |   PUBLISHING YEAR  |");
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
            for (int i = 0; i < listDataFile.size(); i++) {
                listDataFile.get(i).showResult();
            }
            System.out.println("+---------------+---------------+--------------------+----------+----------+--------------------+");
        }
    }
}
