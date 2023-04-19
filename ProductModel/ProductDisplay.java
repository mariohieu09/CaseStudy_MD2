package ProductModel;

import FileIO.ReadFile;
import FileIO.WriteFile;

import java.io.File;
import java.util.List;

public class ProductDisplay {
    public static void display() {
        File productList = new File("ProductList");
        ReadFile rf = new ReadFile();
        List<Product> list = rf.readFile(productList);
        list.forEach(System.out::println);
    }
}
