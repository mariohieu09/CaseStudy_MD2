package ShoppingCart;

import ProductModel.Product;
import eInvoice.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {
    List<Product> list;
    private int id;


    public ShoppingCart(int id) {
        this.id = id;
        this.list = new ArrayList<>();
    }

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

}
