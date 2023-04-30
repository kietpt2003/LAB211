/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Cong Dat
 */
public class Dealer 
{
    private String DealerID;
    private String DealerName;
    private String address;
    private String phoneNum;
    private boolean status;
    
    public Dealer() {
        
    }

    public Dealer(String DealerID, String DealerName, String address, String phoneNum, boolean status) {
        this.DealerID = DealerID;
        this.DealerName = DealerName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.status = status;
    }

    public String getDealerID() {
        return DealerID;
    }

    public String getDealerName() {
        return DealerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return   DealerID + "," + DealerName +
                "," + address + "," + phoneNum + "," + status;
    }
}
