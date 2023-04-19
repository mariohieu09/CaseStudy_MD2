package eWallet;

import java.io.Serializable;

public class eWallet implements Serializable {
    static final long serialVersionUID = -7034897190745766939L;
    private double amount;

    public eWallet() {
        this.amount = 0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
