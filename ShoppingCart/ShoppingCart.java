package ShoppingCart;

import ProductModel.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
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
}
