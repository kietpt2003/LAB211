package mng;

import data.Config;
import data.DealerList;
import data.Deliverylist;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author ACER
 */
public class Menu {
    public void boss() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        System.out.println("Hello Boss!");
        System.out.println("Please Select your choice:");
        do {
            System.out.println("1-Managing dealers");
            System.out.println("2-Managing deliveries");
            System.out.println("Others to quit.");
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    menu.dealer();
                    break;
                case 2:
                    menu.deliver();
                    break;
            }
        } while (choice >= 1 && choice <= 2);
    }

    public void dealer() {
        int choice = 0;
        MyTool tool = new MyTool();
        Config fileName = new Config();
        DealerList listDealer = new DealerList();
        Login listAccount = new Login();

        /*Files handle for object tool (line 41) (Nghĩa là list chỉ tồn tại trong object tool đó thôi
        object khác không có list)*/
        tool.listFile = fileName.getConfigInfo(); //Get File name from config.txt
        tool.listDeal = listDealer.getInfoDealer(tool.getDealerFile()); //Get Dealer information from Accounts.txt
        tool.listAcc = listAccount.accInfo(tool.getAccountFile()); //Get Account information from Accounts.txt

        Scanner sc = new Scanner(System.in);

        System.out.println("Managing dealers");
        do {
            System.out.println("1-Add new dealer");
            System.out.println("2-Search a dealer");
            System.out.println("3-Remove a dealer");
            System.out.println("4-Update a dealer");
            System.out.println("5-Print all dealers");
            System.out.println("6-Print continuing dealers");
            System.out.println("7-Print UN-continuing dealers");
            System.out.println("8-Write to file");
            System.out.println("Others to quit.");
            System.out.print("Choose [1.. 8]: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    tool.createDealer();
                    break;
                case 2:
                    tool.searchDealer();
                    break;
                case 3:
                    tool.deleteDealer();
                    break;
                case 4:
                    tool.UpdateDealerID();
                    break;
                case 5:
                    tool.printAll();
                    break;
                case 6:
                    tool.printCon();
                    break;
                case 7:
                    tool.printUnCon();
                    break;
                case 8:
                    tool.saveFile(tool.getDealerFile());
                    break;
            }
        } while (choice >= 1 && choice <= 8);
    }

    public void deliver() {
        int choice = 0;
        MyTool tool = new MyTool();
        Config fileName = new Config();
        Login listAccount = new Login();
        Deliverylist listDelive=new Deliverylist();
        tool.listFile = fileName.getConfigInfo();
        tool.listAcc = listAccount.accInfo(tool.getAccountFile()); //Get Account information from Accounts.txt
        tool.listDel = listDelive.getInfoDeliver(tool.getDeliverFile());
        Scanner sc = new Scanner(System.in);
        System.out.println("Managing deliveries");
        do {
            System.out.println("1-Add new dealer");
            System.out.println("2-Search a dealer");
            System.out.println("3-Remove a dealer");
            System.out.println("4-Update a dealer");
            System.out.println("5-Print all dealers");
            System.out.println("6-Print 'Un-Deliver' package");
            System.out.println("7-Print 'On-Going' package");
            System.out.println("8-Print 'Delivered' pakage");
            System.out.println("9-Write to file");
            System.out.println("Others to quit.");
            System.out.print("Choose [1.. 9]: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    tool.createDelivery();
                    break;
                case 2:
                    tool.searchDelivery();
                    break;
                case 3:
                    tool.deleteDelivery();
                    break;
                case 4:
                    tool.UpdateDelivery();
                    break;
                case 5:
                    tool.printAllDeliver();
                    break;
                case 6:
                    tool.printUndeliver();
                    break;
                case 7:
                    tool.printOngoing();
                    break;
                case 8:
                    tool.printDelivered();
                    break;
                case 9:
                    tool.saveFile(tool.getDeliverFile());
                    break;
            }
        } while (choice >= 1 && choice <= 9);
    }
}