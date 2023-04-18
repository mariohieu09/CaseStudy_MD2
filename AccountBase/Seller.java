package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ProductModel.ProductManage;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Seller extends Account{
    File productStorage = new File("ProductList");
    List<Product> list;
    public Seller(String accountName, String password) {
        super(accountName, password);
        this.Role = "Seller";
        this.list = new ArrayList<>();
    }
    public Seller(){};



    public void addProduct(Product p) {
        ReadFile rf = new ReadFile();
        list = rf.readFile(productStorage);
        list.add(p);
        WriteFile wf  = new WriteFile();
        wf.writeFile(productStorage, list);
    }

    public String getRole(){
        return this.Role;
    }




    public void removeProduct(Product p) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        list = rf.readFile(productStorage);
        list.remove(p);
        wf.writeFile(productStorage, list);
    }


    public void display(String name) {
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        if(name.equals(null)){
            list = rf.readFile(productStorage);
            list.forEach(System.out::println);
        }else{

        }
    }

    public void coupon(String name){
        ReadFile rf = new ReadFile();
        list = rf.readFile(productStorage);
        for(Product s : list){
            if(s.getName().equals(name)) {
                double currentPrice = s.getPrice();
                s.setPrice(currentPrice * 20/100);
                break;
            }
        }
        WriteFile wf = new WriteFile();
        wf.writeFile(productStorage, list);
    }
    public Product searchProduct(String name){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
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
}
