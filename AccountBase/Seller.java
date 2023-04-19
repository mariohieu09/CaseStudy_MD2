package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ProductModel.ProductManage;
import SellerList.SellerList;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Seller extends Account{
    File productStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");
//    List<Product> list;
    SellerList listSeller;
    public Seller(String accountName, String password) {
        super(accountName, password);
        this.Role = "Seller";
        listSeller = new SellerList();
    }
    public Seller(){
        listSeller = new SellerList();
    };

    public SellerList getListSeller() {
        return listSeller;
    }

    public void setListSeller(SellerList listSeller) {
        this.listSeller = listSeller;
    }

    public void addProduct(String sellerName , Product p) {
        ReadFile rf = new ReadFile();
        List<Product> Owner_Sell = new ArrayList<>();
        List<Account> Accounts = rf.readFile(DataBase);
        List<Product> list;
        list = rf.readFile(productStorage);
        if(checkIfTheProductExisted(p.getName())){
            System.out.println("The product is already existed!");
        }else{
             Seller s = new Seller();
            for(Account a : Accounts){
                if(a.getAccountName().equals(sellerName)){
                    s = (Seller)a;
                    Owner_Sell = s.getListSeller().getList();
                    Owner_Sell.add(p);
                    list.add(p);
                    break;
                }
            }
        }
        WriteFile wf  = new WriteFile();
        wf.writeFile(productStorage, list);
        wf.writeFile(DataBase, Accounts);

    }

    public String getRole(){
        return this.Role;
    }




    public void removeProduct(Product p) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        if(checkIfTheProductExisted(p.getName())) {
            list.remove(p);
        }else{
            System.out.println("The product is not exist!");
        }
        wf.writeFile(productStorage, list);
    }


    public void display(String sellerName) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> Owner_Sell = new ArrayList<>();
        List<Account> Accounts = rf.readFile(DataBase);
        for(Account a : Accounts){
            if(a.getAccountName().equals(sellerName)){
                Owner_Sell = ((Seller)a).getListSeller().getList();
                System.out.println(Owner_Sell);
                break;
            }
        }
    }

    public void coupon(String SellerName,String name){
        ReadFile rf = new ReadFile();
        List<Account> Accounts = rf.readFile(DataBase);
        List<Product> list;
        list = rf.readFile(productStorage);
        for(Account a : Accounts){
            if(a.getAccountName().equals(SellerName)){
                for(Product t : ((Seller)a).getListSeller().getList()){
                    if(t.getName().equals(name)){
                        double currentPrice = t.getPrice();
                        double afterCoupon = currentPrice - (currentPrice * 20/100);
                        t.setPrice(afterCoupon);
                        System.out.println("Success!");
                        break;
                    }
                }
            }
        }
        for(Product t : list){
            if(t.getName().equals(name)){
                double currentPrice = t.getPrice();
                double afterCoupon = currentPrice - (currentPrice * 20/100);
                t.setPrice(afterCoupon);
                System.out.println("Success!");
                break;
            }
        }
        WriteFile wf = new WriteFile();
        wf.writeFile(productStorage, list);
        wf.writeFile(DataBase, Accounts);
    }
    public Product searchProduct(String name){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        Product p = null;
        for(Product s : list){
            if(s.getName().equals(name)){
                System.out.println(s);
                p = s;
                break;
            }
        }
        return p;
    }
    private boolean checkIfTheProductExisted(String name){
        ReadFile rf = new ReadFile();
        List<Product> list;
        list = rf.readFile(productStorage);
        for(Product t : list){
            if(t.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
