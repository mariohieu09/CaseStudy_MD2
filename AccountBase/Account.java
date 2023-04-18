package AccountBase;

import java.io.Serializable;
import ShoppingCart.ShoppingCart;
public abstract class Account implements Serializable {
    protected String AccountName;
    protected String Password;



    public Account(String accountName, String password) {
        AccountName = accountName;
        Password = password;
    }

    public Account() {
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
