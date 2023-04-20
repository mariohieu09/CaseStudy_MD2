package eInvoice;

import AccountBase.Account;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface eInvoiceManage {
    void eInvoiceDisplay(Account p);
    void getPaidmentcheck(Account p, String name);

    void addPaidcheck(Account p, String name);
    void sortThePaidcheck(Account p);
}
