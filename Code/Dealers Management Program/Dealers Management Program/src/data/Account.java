package data;

/**
 *
 * @author ACER
 */
public class Account {
    private String name;
    private String pass;
    private String role;
    
    public Account() {
    }
    
    public Account(String name, String pass, String role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }
    
}