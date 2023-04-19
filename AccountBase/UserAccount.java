package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ShoppingCart.*;
import eWallet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class UserAccount extends Account implements ShoppingCartManage,eWalletManage {
    List<Product> list = new ArrayList<>();
    List<Account> Accounts = new ArrayList<>();
    File ProductStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");
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

    public UserAccount(String accountName, String password) {
        super(accountName, password);
        this.cart = new ShoppingCart(super.getId());
        this.Role = "User";
        this.wallet = new eWallet();
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

    public void removeProd(Product p){
        ReadFile rf = new ReadFile();
        list = rf.readFile(ProductStorage);
        list.remove(p);
        WriteFile wf = new WriteFile();
        wf.writeFile(ProductStorage, list);
    }


   public void display(Account p, String name){
       ReadFile rf = new ReadFile();
        Accounts = rf.readFile(DataBase);
        for(Account s : Accounts){
            if(s.getAccountName().equals(p.getAccountName()) && s.getPassword().equals(p.getPassword())){
                list = ((UserAccount)s).getCart().getList();
               if(name.equals("")){
                   for(Product d : list ){
                       System.out.println(d);
                   }
               }else{
                   for(Product d : list){
                       if(d.getName().equals(name)){
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
        Accounts = rf.readFile(DataBase);
        Product p = getProd(name);
        for(Account a : Accounts){
            if(a.getAccountName().equals(acc.getAccountName()) && a.getPassword().equals(a.getPassword())){
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
    private Product getProd(String name){
        ReadFile rf = new ReadFile();
        list = rf.readFile(ProductStorage);
        for(Product t : list){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    public void Payment(Account p, String haveToPay){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        Accounts = rf.readFile(DataBase);
        for(Account a : Accounts) {
            if (p.getAccountName().equals(a.getAccountName())) {
                for (Product s : ((UserAccount) a).getCart().getList()) {
                    if (s.getName().equals(haveToPay)) {
                        double currentAmount = ((UserAccount) a).getWallet().getAmount();
                        if (s.getPrice() <= currentAmount) {
                            ((UserAccount) a).getCart().getList().remove(s);
                            double afterPay = currentAmount - s.getPrice();
                            ((UserAccount) a).getWallet().setAmount(afterPay);
                            System.out.println("The product have been paid!");
                            break;
                        } else {
                            System.out.println("The amount is not able to pay the price. Please deposit the money!");
                            break;
                        }
                    }
                }
            }
        }
        wf.writeFile(DataBase, Accounts);
    }

    @Override
    public void deposit(Account p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount you want to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        Accounts = rf.readFile(DataBase);
        for(Account t : Accounts){
            if(t.getAccountName().equals(p.getAccountName()) && t.getPassword().equals(p.getPassword())){
                double currentAmount = ((UserAccount)t).getWallet().getAmount();
                currentAmount += amount;
                ((UserAccount)t).getWallet().setAmount(currentAmount);
                break;
            }
        }
        wf.writeFile(DataBase, Accounts);
    }
    public void eWalletDisplay(Account p){
        double currentAmount = 0;
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        Accounts = rf.readFile(DataBase);
        for(Account a : Accounts){
            if(a.getAccountName().equals(p.getAccountName())){
                currentAmount = ((UserAccount)a).getWallet().getAmount();
                System.out.println(currentAmount);
                break;
            }
        }
    }
}
