package ProductModel;

import AccountBase.Account;
import FileIO.ReadFile;
import FileIO.WriteFile;

import java.io.File;
import java.util.*;

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
            System.out.println("------------------------------------");
        }
    }
    public static void SortByName(){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        File productStorage = new File("ProductList");
        List<Product> list = rf.readFile(productStorage);
        Collections.sort(list, Comparator.comparing(Product::getName));
        list.forEach(System.out::println);
    }
    public static void SortByPrice(){
        ReadFile rf = new ReadFile();
        WriteFile wf = new WriteFile();
        File productStorage = new File("ProductList");
        List<Product> list = rf.readFile(productStorage);
        Collections.sort(list, Comparator.comparing(Product::getPrice));
        list.forEach(System.out::println);
    }
}
