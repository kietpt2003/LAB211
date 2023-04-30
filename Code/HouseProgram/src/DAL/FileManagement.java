/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.io.File;
import java.util.Scanner;
import DTO.CDs;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author ACER
 */
public class FileManagement {

    final int max = 100;
    CDs[] arrCDs = new CDs[max];

    public int count = 0;

    public void FileManagement() {
        arrCDs = new CDs[max];
    }

    public CDs[] getFile() {
        String name;
        String type;
        String title;
        long price;
        String ID;
        int year;
        boolean checkFull = false;
        try {
            File file = new File("CDProgram.txt");
            if (!file.exists()) { //If file do not exist then create new file
                file.createNewFile();
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine() && checkFull == false) {
                String data = reader.nextLine();
                String[] arrData = data.split(" - ", 6);
                ID = arrData[0];
                name = arrData[1];
                type = arrData[2];
                title = arrData[3];
                price = Long.parseLong(arrData[4]);
                year = Integer.parseInt(arrData[5]);

                arrCDs[count] = new CDs(ID, name, type, title, price, year);
                count++;
                if (count == 99) { //0 -> 99
                    checkFull = true;
                }
            }
            if (checkFull) {
                System.out.println("CD array is full.");
            }

        } catch (Exception e) {
            System.out.println("There aren't any Files");
        }
        return arrCDs;
    }

    public boolean saveFile(CDs[] arrData) {
        boolean check = false;
        try {
            File file = new File("CDProgram.txt");
            if (!file.exists()) { // if file doesnt exists, then check = false
                check = false;
            } else {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < arrData.length && arrData[i] != null; i++) {
                    bw.write(arrData[i].toString() + "\n");
                }
                check = true;
                bw.close();
            }
        } catch (Exception e) {
            check = false;
            System.out.println("The File is empty.");
        }
        return check;
    }
}
