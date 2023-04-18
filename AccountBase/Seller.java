package AccountBase;

import ProductModel.Product;
import ProductModel.ProductManage;

import java.io.File;

public class Seller extends Account  {
    File list = new File("ProductList");

    public Seller(String accountName, String password) {
        super(accountName, password);
        this.Role = "Seller";
    }
    public Seller(){};



    public void addProduct() {

    }

    public String getRole(){
        return this.Role;
    }


}
