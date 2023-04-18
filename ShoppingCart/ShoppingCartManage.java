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

public interface ShoppingCartManage {



    public void removeProd(Product p);
    public void display(Product p);

    public void addToCart(String name, UserAccount acc);
}