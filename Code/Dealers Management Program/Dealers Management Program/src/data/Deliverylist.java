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

public class Deliverylist 
{
    List<Delivery> listDel = new ArrayList<>();
    
    public List<Delivery> getInfoDeliver(String nameAcc) {
        try {
            File deliverFile = new File(nameAcc); //File dealers.txt
            Scanner reader = new Scanner(deliverFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arrData = data.split(",", 5); //co 5 phan tu nen can tach ra 5
                //Truyền nó vô list dealer bên Mytool để dùng
                listDel.add(new Delivery(arrData[0],arrData[1],arrData[2],arrData[3],arrData[4]));
            }
        } catch (Exception e) {
        }
        return listDel;
    }    
}
