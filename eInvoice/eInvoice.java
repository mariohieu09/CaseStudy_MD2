package eInvoice;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class eInvoice implements Serializable {
    static final long serialVersionUID = -7034897190745766939L;
    LocalDateTime paidDate;
    String ProductName;
    String UserName;
    public eInvoice(String userName, String productName){
        this.paidDate = LocalDateTime.now();
        this.UserName = userName;
        this.ProductName = productName;
    }
    public eInvoice(){};



    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    private void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "Paid Check{" +
                "Date=" + paidDate +
                ", Product Name='" + ProductName + '\'' +
                ", User Name='" + UserName + '\'' +
                '}';
    }
}
