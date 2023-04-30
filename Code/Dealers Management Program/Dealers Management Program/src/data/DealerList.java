/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cong Dat
 */
public class DealerList 
{
    List<Dealer> list = new ArrayList<>();
    
    public List<Dealer> getInfoDealer(String nameAcc) { //Get Dealers information from dealers.txt
        boolean status;
        try {
            File dealFile = new File(nameAcc); //File dealers.txt
            Scanner reader = new Scanner(dealFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arrData = data.split(",", 5); //co 5 phan tu nen can tach ra 5
                if (arrData[4].matches("(.*)true(.*)")) {
                    status = true;
                } else {
                    status = false;
                }
                
                //Truyền nó vô list dealer bên Mytool để dùng
                list.add(new Dealer(arrData[0], arrData[1], arrData[2], arrData[3], status));
            }
        } catch (Exception e) {
            System.out.println("File with encorect format/ File Empty");
        }
        return list;
    }
}
