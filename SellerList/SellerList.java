package SellerList;

import ProductModel.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SellerList implements Serializable {
    List<Product> list;

    public SellerList() {
        list = new ArrayList<>();
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

}
