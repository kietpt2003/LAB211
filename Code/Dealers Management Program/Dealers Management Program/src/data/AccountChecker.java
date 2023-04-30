
package data;

import java.util.Scanner;
/**
 *
 * @author ACER
 */
public class AccountChecker {
    public boolean checkAcc(String nameCheck, String nameInput) {
        if (nameInput.compareTo(nameCheck) != 0) {
            return false;
        }
        return true;
    }

    public boolean checkPass(String passCheck) {
        Scanner sc = new Scanner(System.in);
        String passInput = sc.nextLine();
        if (passInput.compareTo(passCheck) != 0) {
            System.out.println("Invalid Password. Try again.");
            return false;
        }
        return true;
    }
}
