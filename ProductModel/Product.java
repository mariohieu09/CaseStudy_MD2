package ProductModel;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Serializable {
    static final long serialVersionUID = -7034897190745766939L;
    private  int quantity = 0;
    private double price;
    private String name;
    private String description;
    protected String type;

    public Product(int price, String name, String description, int quantity) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(double price, String name, String description, String type) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Product() {
        this.name = "";
        this.price = 0;
        this.description = "";
        this.type = "";
    }

    public  int getQuantity() {
        return quantity;
    }

    public  void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
