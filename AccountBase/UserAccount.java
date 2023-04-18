package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ShoppingCart.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account implements ShoppingCartManage {
    List<Product> list = new ArrayList<>();
    List<Account> Accounts = new ArrayList<>();
    File ProductStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");
    public ShoppingCart cart;

    public UserAccount() {
    }

    public UserAccount(String accountName, String password) {
        super(accountName, password);
        this.cart = new ShoppingCart(super.getId());
        this.Role = "User";
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
    public void display(Product p){
        ReadFile rf = new ReadFile();
        List<UserAccount> list = rf.readFile(DataBase);
        if(p.equals(null)){
            for(UserAccount s : list){
                System.out.println(s.getCart().getList());
            }
        }else{
            for(UserAccount s : list){
                for(Product t : s.getCart().getList()){
                    if(t.equals(p)){
                        System.out.println(p);
                        break;
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
}
