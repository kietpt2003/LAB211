/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import BLL.check;
import BLL.mytool;
import DAL.FileManagement;

/**
 *
 * @author ACER
 */
public class DataTransfer {

    public CDs[] arrCDs;
    public int count;
    check check = new check();
    mytool tool = new mytool();
    FileManagement file = new FileManagement();

    public DataTransfer() {
    }

    public DataTransfer(CDs[] arr, int count) {
        this.count = count;
        arrCDs = arr;
    }
    
    public boolean transferCheckSpace(int count) {
        check.arrCDs = arrCDs;
        return check.checkFreeSpace(count);
    }

    public boolean transferID(String ID) {
        check.arrCDs = arrCDs;
        return check.checkID(ID);
    }
    
    public boolean transferName(String name) {
        check.arrCDs = arrCDs;
        return check.checkName(name);
    }
    
    public boolean transferType(String type) {
        check.arrCDs = arrCDs;
        return check.checkType(type);
    }
    
    public boolean transferTitle(String title) {
        check.arrCDs = arrCDs;
        return check.checkTitle(title);
    }
    
    public boolean transferInputPrice(String priceInput) {
        check.arrCDs = arrCDs;
        return check.checkLongVar(priceInput);
    }
    
    public boolean transferPrice(long price) {
        check.arrCDs = arrCDs;
        return check.checkPrice(price);
    }
    
    public boolean transferInputYear(String yearInput) {
        check.arrCDs = arrCDs;
        return check.checkYearInput(yearInput);
    }
    
    public boolean transferYear(int year) {
        check.arrCDs = arrCDs;
        return check.checkYear(year);
    }
    
    public boolean transferAdd(CDs objCD) {
        boolean checkk = false;
        if (count < 100) {
            arrCDs[count] = objCD;
            check.arrCDs = arrCDs;
            count++;
            checkk = true;
        }
        return checkk;
    }
    
    public boolean transferSearch(String title) {
        tool.arrCDs = arrCDs;
        return tool.searchCD(title);
    }
    
    public boolean transferPrint() {
        tool.arrCDs = arrCDs;
        return tool.printAll();
    }
    
    public boolean transferUpdate() {
        tool.arrCDs = arrCDs;
        return tool.UpdateCDID();
    }
    
    public boolean transferRemove() {
        tool.arrCDs = arrCDs;
        return tool.deleteCD();
    }
    
    public boolean transferSave() {
        return file.saveFile(arrCDs);
    }
    
    public boolean transferPrintSort() {
        tool.arrCDs = arrCDs;
        return tool.printSortName();
    }
}
