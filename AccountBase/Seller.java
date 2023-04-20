package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ProductModel.ProductManage;
import SellerList.SellerList;
import eWallet.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends Account {
    static final long serialVersionUID = -7034897190745766939L;
    File productStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");
    eWallet eWallet;

    List<Product> list;

    SellerList listSeller;

    public Seller(String accountName, String password) {
        super(accountName, password);
        this.Role = "Seller";
        listSeller = new SellerList();
        this.eWallet = new eWallet();
    }

    public Seller() {
        listSeller = new SellerList();
    }

    ;

    public SellerList getListSeller() {
        return listSeller;
    }

    public void setListSeller(SellerList listSeller) {
        this.listSeller = listSeller;
    }

    public void addProduct(String sellerName, Product p) {
        ReadFile rf = new ReadFile();
        List<Product> Owner_Sell = new ArrayList<>();
        List<Account> Accounts = rf.readFile(DataBase);
        List<Product> list;
        list = rf.readFile(productStorage);
        if (checkIfTheProductExisted(p.getName())) {
            System.out.println("The product is already existed!");
        } else {
            Seller s = new Seller();
            for (Account a : Accounts) {
                if (a.getAccountName().equals(sellerName)) {
                    s = (Seller) a;
                    Owner_Sell = s.getListSeller().getList();
                    Owner_Sell.add(p);
                    list.add(p);
                    break;
                }
            }
        }
        WriteFile wf = new WriteFile();
        wf.writeFile(productStorage, list);
        wf.writeFile(DataBase, Accounts);

    }

    public String getRole() {
        return this.Role;
    }


    public void removeProduct(Product p) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        if (checkIfTheProductExisted(p.getName())) {
            list.remove(p);
        } else {
            System.out.println("The product is not exist!");
        }
        wf.writeFile(productStorage, list);
    }

    public eWallet geteWallet() {
        return eWallet;
    }

    public void seteWallet(eWallet eWallet) {
        this.eWallet = eWallet;
    }

    public void display(String sellerName) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> Owner_Sell = new ArrayList<>();
        List<Account> Accounts = rf.readFile(DataBase);
        for (Account a : Accounts) {
            if (a.getAccountName().equals(sellerName)) {
                Owner_Sell = ((Seller) a).getListSeller().getList();
                System.out.println("Here is your list of selling product: ");
                Owner_Sell.forEach(System.out::println);
                System.out.println("------------------------------------");
                break;
            }
        }
    }

    public boolean coupon(String SellerName, String productName) {
        boolean isTrue = false;
        Scanner sc = new Scanner(System.in);
        ReadFile rf = new ReadFile();
        List<Account> Accounts = rf.readFile(DataBase);
        List<Product> list;
        list = rf.readFile(productStorage);
        System.out.println("Enter the percent you want to decrease: ");
        int percent = sc.nextInt();
        sc.nextLine();
        for (Account a : Accounts) {
            if (a.getAccountName().equals(SellerName)) {
                for (Product t : ((Seller) a).getListSeller().getList()) {
                    if (t.getName().equals(productName)) {
                        double currentPrice = t.getPrice();
                        double afterCoupon = currentPrice - (currentPrice * percent / 100);
                        t.setPrice(afterCoupon);
                        for (Product n : list) {
                            if (n.getName().equals(productName)) {
                                n.setPrice(afterCoupon);
                                isTrue = true;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

        WriteFile wf = new WriteFile();
        wf.writeFile(productStorage, list);
        wf.writeFile(DataBase, Accounts);
        return isTrue;
    }

    public Product searchProduct(String name) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        Product p = null;
        for (Product s : list) {
            if (s.getName().equals(name)) {
                System.out.println(s);
                p = s;
                break;
            }
        }
        return p;
    }

    private boolean checkIfTheProductExisted(String name) {
        ReadFile rf = new ReadFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        for (Product t : list) {
            if (t.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean increasePrice(String SellerName, String productName) {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = false;
        ReadFile rf = new ReadFile();
        List<Account> Accounts = rf.readFile(DataBase);
        List<Product> list;
        list = rf.readFile(productStorage);
        System.out.println("Enter the percent you want to increase: ");
        int percent = sc.nextInt();
        sc.nextLine();
        for (Account a : Accounts) {
            if (a.getAccountName().equals(SellerName)) {
                for (Product t : ((Seller) a).getListSeller().getList()) {
                    if (t.getName().equals(productName)) {
                        double currentPrice = t.getPrice();
                        double afterCoupon = currentPrice + (currentPrice * percent / 100);
                        t.setPrice(afterCoupon);
                        for (Product n : list) {
                            if (n.getName().equals(productName)) {
                                double crP = n.getPrice();
                                double afP = crP + (crP * percent / 100);
                                n.setPrice(afP);
                                isTrue = true;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        WriteFile wf = new WriteFile();
        wf.writeFile(productStorage, list);
        wf.writeFile(DataBase, Accounts);
        return isTrue;
    }

    public void eWalletDisplay(Account p) {
        double currentAmount = 0;
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Account> Accounts = new ArrayList<>();
        Accounts = rf.readFile(DataBase);
        for (Account a : Accounts) {
            if (a.getAccountName().equals(p.getAccountName())) {
                currentAmount = ((Seller) a).geteWallet().getAmount();
                System.out.println(currentAmount);
                break;
            }
        }
    }
}
