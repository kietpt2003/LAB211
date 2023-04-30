package mng;

import data.Account;
import data.Config;
import data.DealerList;
import data.Deliverylist;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author ACER
 */
public class Login {

    public List<Account> accInfo(String accInfo) { //Get account information from Accounts.txt
        List<Account> list = new ArrayList<Account>();
        try {
            File accFile = new File(accInfo); //File accounts.txt
            Scanner reader = new Scanner(accFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arrData = data.split(",", 3); //co 3 phan tu nen can tach ra 3
                list.add(new Account(arrData[0], arrData[1], arrData[2]));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public String log() {
        MyTool tool = new MyTool();
        Config fileName = new Config();
        DealerList listDealer = new DealerList();
        Login listAccount = new Login();
        Deliverylist listDelive = new Deliverylist();

        /*Files handle for onject tool (line 37) (Nghĩa là list chỉ tồn tại trong object tool đó thôi
        object khác không có list)*/
        tool.listFile = fileName.getConfigInfo(); //Get File name from config.txt
        tool.listDeal = listDealer.getInfoDealer(tool.getDealerFile()); //Get Dealer information from Accounts.txt
        tool.listAcc = listAccount.accInfo(tool.getAccountFile()); //Get Account information from Accounts.txt
        tool.listDel = listDelive.getInfoDeliver(tool.getDeliverFile());

        System.out.println("Please Login to System.");
        tool.checkName();
        tool.checkPass();
        System.out.println("Your role: " + tool.myRole());
        return tool.myRole();
    }

    public static void main(String[] args) {
        int choice;
        boolean checkSign = true;
        do {
            String role;
            Menu menu = new Menu();
            Login login = new Login();
            Scanner sc = new Scanner(System.in);
            role = login.log(); //Get the role of the Employee

            //Menu
            if (role.compareTo("BOSS") == 0) {
                menu.boss();
            } else if (role.compareTo("ACC-1") == 0) {
                menu.dealer();
            } else {
                menu.deliver();
            }
            System.out.println("(1) Sign Out."); //Đăng xuất khỏi tài khoản để qua tài khoản khác
            System.out.println("(2) Exit."); //Thoát khỏi chương trình
            System.out.print("Please choose your choice: ");
            do {
                choice = sc.nextInt();
                if (choice == 1) {
                    checkSign = true;
                } else if (choice == 2) {
                    checkSign = false;
                } else {
                    System.out.println("Choose correct choice 1/2.");
                    System.out.print("Try again: ");
                }
            } while (choice <= 0 || choice >= 3);
        } while (checkSign);
    }
}
