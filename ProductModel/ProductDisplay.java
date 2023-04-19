package ProductModel;

import FileIO.ReadFile;
import FileIO.WriteFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductDisplay {
    public static void display() {
        File productList = new File("ProductList");
        ReadFile rf = new ReadFile();
        List<Product> list = rf.readFile(productList);
        list.forEach(System.out::println);
    }
    public static void searchProduct(String name){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        List<Product> list = new ArrayList<>();
        File productStorage = new File("ProductList");
        Product temp = new Product();
        list = rf.readFile(productStorage);
        for(Product s : list){
            if(s.getName().equals(name)){
                temp = s;
                break;
            }
        }
        if(temp.getName().equals("")){
            System.out.println("Can't not find the product");
        }else{
            System.out.println(temp);
        }
    }
}
