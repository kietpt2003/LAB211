/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Cong Dat
 */
public class Delivery {

    private String ProductID;
    private String CustomerName;
    private String CusAdd;
    private String CusPhoneNumber;
    private String PackageStatus;

    public Delivery() {
    }

    public Delivery(String ProductID, String CustomerName, String CusAdd, String CusPhoneNumber, String PackageStatus) {
        this.ProductID = ProductID;
        this.CustomerName = CustomerName;
        this.CusAdd = CusAdd;
        this.CusPhoneNumber = CusPhoneNumber;
        this.PackageStatus = PackageStatus;
    }

    public String getProductID() {
        return ProductID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCusAdd() {
        return CusAdd;
    }

    public String getCusPhoneNumber() {
        return CusPhoneNumber;
    }

    public String getPackageStatus() {
        return PackageStatus;
    }

    @Override
    public String toString() {
        return ProductID + "," + CustomerName + "," + CusAdd + "," + CusPhoneNumber + "," + PackageStatus;
    }

}
