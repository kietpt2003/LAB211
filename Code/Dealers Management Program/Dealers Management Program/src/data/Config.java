package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Config {

    public String acc;
    public String dealer;
    public String deliver;
    List<Config> list = new ArrayList<Config>();
    
    public List<Config> getConfigInfo() { //Get File name from config.txt
        try {
            File file = new File("DealerData/config.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.matches("(.*)File of accounts:(.*)")) { //Cú pháp (.*)str(.*) tham khảo trên mạng thì mới check dc str đó
                    acc = data.substring(18); //Vì "File of accounts: " 17 kí tự
                } else if (data.matches("(.*)File of dealers:(.*)")) {
                    dealer = data.substring(17); //Vì "File of dealers: " 16 kí tự
                } else if (data.matches("(.*)File of delivery notes:(.*)")) {
                    deliver = data.substring(24); //Vì "File of delivery notes: " 23 kí tự
                }
            }
            list.add(new Config(acc, dealer, deliver));
        } catch (Exception e) {
            System.out.println("File with encorect format/ File Empty");
        }
        return list;
    }
    
    public Config() {
        
    }
    
    public Config(String acc, String dealer, String deliver) {
        this.acc = acc;
        this.dealer = dealer;
        this.deliver = deliver;
    }
}