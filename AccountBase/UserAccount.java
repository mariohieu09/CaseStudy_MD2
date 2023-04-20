package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ShoppingCart.*;
import eInvoice.*;
import eWallet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class UserAccount extends Account implements ShoppingCartManage,eWalletManage, eInvoiceManage {
    List<Product> list = new ArrayList<>();
    File ProductStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");
    List<eInvoice> invoiceList;
    public ShoppingCart cart;
    public eWallet wallet;
    static final long serialVersionUID = -7034897190745766939L;

    public UserAccount() {
    }

    public eWallet getWallet() {
        return wallet;
    }

    public void setWallet(eWallet wallet) {
        this.wallet = wallet;
    }

    public List<eInvoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<eInvoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public UserAccount(String accountName, String password) {
        super(accountName, password);
        this.cart = new ShoppingCart(super.getId());
        this.Role = "User";
        this.wallet = new eWallet();
        this.invoiceList = new ArrayList<eInvoice>();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        this.cart.setId(id);
    }

    public void removeProd(Product p) {
        ReadFile rf = new ReadFile();
        list = rf.readFile(ProductStorage);
        list.remove(p);
        WriteFile wf = new WriteFile();
        wf.writeFile(ProductStorage, list);
    }


    public void display(Account p, String name) {
        ReadFile rf = new ReadFile();
        List<Account> Accounts = new ArrayList<>();
        Accounts = rf.readFile(DataBase);
        for (Account s : Accounts) {
            if (s.getAccountName().equals(p.getAccountName()) && s.getPassword().equals(p.getPassword())) {
                list = ((UserAccount) s).getCart().getList();
                if (name.equals("")) {
                    for (Product d : list) {
                        System.out.println(d);
                    }
                } else {
                    for (Product d : list) {
                        if (d.getName().equals(name)) {
                            System.out.println(d);
                            break;
                        }
                    }
                }

            }
        }

    }


    public void addToCart(String name, UserAccount acc) {
        ReadFile rf = new ReadFile();
        List<Account> Accounts = new ArrayList<>();
        Accounts = rf.readFile(DataBase);
        Product p = getProd(name);
        for (Account a : Accounts) {
            if (a.getAccountName().equals(acc.getAccountName()) && a.getPassword().equals(a.getPassword())) {
                acc = (UserAccount) a;
                list = acc.getCart().getList();
                list.add(p);
                ((UserAccount) a).getCart().setList(list);
                break;
            }
        }
        WriteFile wf = new WriteFile();
        wf.writeFile(DataBase, Accounts);
    }

    private Product getProd(String name) {
        ReadFile rf = new ReadFile();
        list = rf.readFile(ProductStorage);
        for (Product t : list) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public boolean Payment(Account p, String haveToPay) {
        boolean beenPaid = false;
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Account> Accounts = new ArrayList<>();
        Accounts = rf.readFile(DataBase);
        for (Account a : Accounts) {
            if (p.getAccountName().equals(a.getAccountName())) {
                for (Product s : ((UserAccount) a).getCart().getList()) {
                    if (s.getName().equals(haveToPay)) {
                        double currentAmount = ((UserAccount) a).getWallet().getAmount();
                        if (s.getPrice() <= currentAmount) {
                            ((UserAccount) a).getCart().getList().remove(s);
                            double afterPay = currentAmount - s.getPrice();
                            ((UserAccount) a).getWallet().setAmount(afterPay);
                            System.out.println("The product have been paid!");
                            wf.writeFile(DataBase, Accounts);
                            beenPaid = true;
                            return beenPaid;
                        } else {
                            System.out.println("The amount is not able to pay the price. Please deposit the money!");
                            beenPaid = false;
                            wf.writeFile(DataBase, Accounts);
                            return beenPaid;
                        }
                    }
                }
            }
        }
        return beenPaid;
    }

    @Override
    public void deposit(Account p) {
        List<Account> Accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount you want to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        Accounts = rf.readFile(DataBase);
        for (Account t : Accounts) {
            if (t.getAccountName().equals(p.getAccountName()) && t.getPassword().equals(p.getPassword())) {
                double currentAmount = ((UserAccount) t).getWallet().getAmount();
                currentAmount += amount;
                ((UserAccount) t).getWallet().setAmount(currentAmount);
                System.out.println("Your transaction is success! Your current amount: " + currentAmount);
                break;
            }
        }
        wf.writeFile(DataBase, Accounts);
    }

    public void eWalletDisplay(Account p) {
        List<Account> Accounts = new ArrayList<>();
        double currentAmount = 0;
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        Accounts = rf.readFile(DataBase);
        for (Account a : Accounts) {
            if (a.getAccountName().equals(p.getAccountName())) {
                currentAmount = ((UserAccount) a).getWallet().getAmount();
                System.out.println(currentAmount);
                break;
            }
        }
    }

    public boolean exchange(Account p, String name) {
        List<Account> Accounts = new ArrayList<>();
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        boolean confirm = false;
        Accounts = rf.readFile(DataBase);
        AccountManage am = new AccountManage();
        boolean hasBeenPaid = Payment(p, name);
        if (hasBeenPaid) {
            for (Account a : Accounts) {
                if (am.checkRole(a) == 1) {
                    for (Product prod : ((Seller) a).getListSeller().getList()) {
                        if (prod.getName().equals(name)) {
                            double currentAmount = ((Seller) a).geteWallet().getAmount();
                            currentAmount += prod.getPrice();
                            ((Seller) a).geteWallet().setAmount(currentAmount);
                            System.out.println("Success!");
                            confirm = true;
                            break;
                        }
                    }
                }
            }
        }
        wf.writeFile(DataBase, Accounts);
        return confirm;
    }

    public void reduceQuantity(String haveTopay) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        boolean canReduce = false;
        list = rf.readFile(ProductStorage);
        if(checkTheQuantity(haveTopay)){
            for(Product product : list){
                if(product.getName().equals(haveTopay)){
                    int current = product.getQuantity();
                    product.setQuantity(current - 1);
                    canReduce = true;
                    break;
                }
            }
        }
        wf.writeFile(ProductStorage, list);
    }

    public boolean checkTheQuantity(String name) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        boolean yesItCan = false;
        list = rf.readFile(ProductStorage);
        for (Product product : list) {
            if (product.getName().equals(name)) {
                if (product.getQuantity() > 0) {
                    yesItCan = true;
                    break;
                }
            }
        }
        return yesItCan;
    }

    @Override
    public void eInvoiceDisplay(Account p) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Account> Accounts;
        Accounts = rf.readFile(DataBase);
        for(Account account : Accounts){
            if(account.getAccountName().equals(p.getAccountName())){
                invoiceList = ((UserAccount)account).getInvoiceList();
                invoiceList.forEach(System.out::println);
                break;
            }
        }
    }
    public void getPaidmentcheck(Account p, String name){
        System.out.println(new eInvoice(p.getAccountName(), name));
    }

    @Override
    public void addPaidcheck(Account p, String name) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Account> Accounts;
        Accounts = rf.readFile(DataBase);
        for(Account account : Accounts){
            if(p.getAccountName().equals(account.getAccountName())){
                invoiceList = ((UserAccount)account).getInvoiceList();
                invoiceList.add(new eInvoice(p.getAccountName(), name));
                ((UserAccount)account).setInvoiceList(invoiceList);
                break;
            }
        }
        wf.writeFile(DataBase, Accounts);
    }

    @Override
    public void sortThePaidcheck(Account p) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Account> Accounts;
        Accounts = rf.readFile(DataBase);

    }
}
