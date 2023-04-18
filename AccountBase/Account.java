package AccountBase;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import ShoppingCart.ShoppingCart;
public abstract class Account implements Serializable {

    protected String AccountName;
    protected String Password;
    protected int id;
    Random rd = new Random();
    protected String Role = "default";

    public Account(String accountName, String password) {
        this.AccountName = accountName;
        this.Password = password;
        this.id = rd.nextInt(1000);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
