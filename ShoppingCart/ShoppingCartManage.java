package ShoppingCart;
import AccountBase.Account;
import AccountBase.UserAccount;
import FileIO.*;
import ProductModel.Product;
import ProductModel.ProductManage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartManage {
    List<Product> list = new ArrayList<>();
    File ProductStorage = new File("ProductList");
    File DataBase = new File("DataBase.txt");


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

    public List<Product> addToCart(Product p) {
        ReadFile rf = new ReadFile();
        list = rf.readFile(ProductStorage);
        list.add(p);
        return list;
    }
}
