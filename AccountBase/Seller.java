package AccountBase;

import FileIO.ReadFile;
import FileIO.WriteFile;
import ProductModel.Product;
import ProductModel.ProductManage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Seller extends Account {
    File productStorage = new File("ProductList");
    WriteFile wf = new WriteFile();
    ReadFile rf = new ReadFile();
    List<Product> list = new ArrayList<>();
    public Seller(String accountName, String password) {
        super(accountName, password);
        this.Role = "Seller";
    }
    public Seller(){};



    public void addProduct(Product p) {
        list = rf.readFile(productStorage);
        list.add(p);
        wf.writeFile(productStorage, list);
    }

    public String getRole(){
        return this.Role;
    }




    public void removeProduct(Product p) {
        list = rf.readFile(productStorage);
        list.remove(p);
        wf.writeFile(productStorage, list);
    }


    public void display(Product p) {

    }

    public void coupon(String name){
        list = rf.readFile(productStorage);
        for(Product s : list){
            if(s.getName().equals(name)) {
                double currentPrice = s.getPrice();
                s.setPrice(currentPrice * 20/100);
                break;
            }
        }
        wf.writeFile(productStorage, list);
    }
    public Product searchProduct(String name){
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
